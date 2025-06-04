package util;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.kernel.colors.ColorConstants; 
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

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
                    List optionsList = new List();
                    optionsList.setListSymbol("   "); // Indentação
                    for (int j = 0; j < opcoesArray.length(); j++) {
                        String optionText = opcoesArray.getString(j);
                        String optionLetter = String.valueOf((char) ('A' + j));
                        optionsList.add(new ListItem(optionLetter + ") " + optionText));
                    }
                    document.add(optionsList.setMarginLeft(20).setMarginBottom(10));

                    // Resposta Correta (opcional)
                    if (includeCorrectAnswers && question.has("respostaCorreta")) {
                        String respostaCorreta = question.getString("respostaCorreta");
                        String displayedCorrectAnswer = "";

                        if (respostaCorreta.length() == 1 && Character.isLetter(respostaCorreta.charAt(0))) {
                            int correctIndex = respostaCorreta.charAt(0) - 'A';
                            if (correctIndex >= 0 && correctIndex < opcoesArray.length()) {
                                displayedCorrectAnswer = respostaCorreta + " - " + opcoesArray.getString(correctIndex);
                            } else {
                                displayedCorrectAnswer = respostaCorreta + " (Opção inválida)";
                            }
                        } else {
                            displayedCorrectAnswer = respostaCorreta;
                        }

                        document.add(new Paragraph("Resposta Correta: " + displayedCorrectAnswer)
                                .setFontSize(12)
                                .setBold()
                                .setFontColor(ColorConstants.DARK_GRAY) // Cor para a resposta
                                .setMarginTop(5)
                                .setMarginBottom(20)
                                .setHorizontalAlignment(HorizontalAlignment.LEFT)); // Alinhamento da resposta
                    }
                }
            }
        } catch (Exception e) {
            // Em caso de erro no parse do JSON ou na estrutura
            document.add(new Paragraph("Erro ao carregar questões do quiz. Conteúdo JSON inválido.")
                    .setFontSize(12)
                    .setFontColor(ColorConstants.RED));
            System.err.println("Erro ao parsear JSON no QuizPdfGenerator: " + e.getMessage());
            e.printStackTrace();
        }

        document.close();
        return baos.toByteArray();
    }
}
