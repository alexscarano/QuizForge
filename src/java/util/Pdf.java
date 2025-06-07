package util;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Pdf {

    public static byte[] generateQuizPdf(String quizTopic, String quizQuestionsJson, boolean includeCorrectAnswers) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Título do Quiz
        document.add(new Paragraph("QuizForge - Seu Quiz Gerado")
                .setFontSize(24)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20));

        document.add(new Paragraph("Tópico: " + quizTopic)
                .setFontSize(18)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(30));

        try {
            JSONArray questions = new JSONArray(quizQuestionsJson);

            if (questions.length() == 0) {
                document.add(new Paragraph("Nenhuma questão disponível para este quiz.")
                        .setFontSize(14)
                        .setTextAlignment(TextAlignment.CENTER));
            } else {
                for (int i = 0; i < questions.length(); i++) {
                    JSONObject question = questions.getJSONObject(i);
                    String pergunta = question.getString("pergunta");
                    JSONArray opcoesArray = question.getJSONArray("opcoes");

                    // Número da Pergunta e Texto
                    document.add(new Paragraph((i + 1) + ". " + pergunta)
                            .setFontSize(14)
                            .setBold()
                            .setMarginBottom(5));

                    // Lista de Opções
                    List optionsList = new List()
                            .setListSymbol("   ") // Indentação
                            .setMarginLeft(20)
                            .setMarginBottom(10);

                    for (int j = 0; j < opcoesArray.length(); j++) {
                        String optionText = opcoesArray.getString(j);
                        optionsList.add(new ListItem(optionText));
                    }

                    document.add(optionsList);

                    // Resposta Correta (se incluída)
                    if (includeCorrectAnswers && question.has("respostaCorreta")) {
                        String respostaCorreta = question.getString("respostaCorreta");
                        String displayedCorrectAnswer;

                        if (respostaCorreta.length() == 1 && Character.isLetter(respostaCorreta.charAt(0))) {
                            int correctIndex = respostaCorreta.charAt(0) - 'A';
                            if (correctIndex >= 0 && correctIndex < opcoesArray.length()) {
                                displayedCorrectAnswer = opcoesArray.getString(correctIndex);
                            } else {
                                displayedCorrectAnswer = "(Opção inválida)";
                            }
                        } else {
                            displayedCorrectAnswer = respostaCorreta;
                        }

                        document.add(new Paragraph("Resposta Correta: " + displayedCorrectAnswer)
                                .setFontSize(12)
                                .setBold()
                                .setFontColor(ColorConstants.DARK_GRAY)
                                .setMarginTop(5)
                                .setMarginBottom(20)
                                .setHorizontalAlignment(HorizontalAlignment.LEFT));
                    }
                }
            }
        } catch (Exception e) {
            // Erro no JSON ou estrutura inválida
            document.add(new Paragraph("Erro ao carregar questões do quiz. Conteúdo JSON inválido.")
                    .setFontSize(12)
                    .setFontColor(ColorConstants.RED));
        }

        document.close();
        return baos.toByteArray();
    }
}
