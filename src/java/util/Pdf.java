package util;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.kernel.colors.ColorConstants; 
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.borders.SolidBorder; 
import com.itextpdf.layout.element.Div;        
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
        PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLDOBLIQUE);
        PdfFont italicFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_OBLIQUE);

        // Título do Quiz
        document.add(new Paragraph("QuizForge - Seu Quiz Gerado")
                .setFont(boldFont)
                .setFontSize(24)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20));

        document.add(new Paragraph("Tópico: " + quizTopic)
                .setFont(italicFont)
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

                    // Criando um Div para cada pergunta e aplicando borda 
                    Div questionBlock = new Div()
                            .setBorder(new SolidBorder(ColorConstants.LIGHT_GRAY, 1)) // Borda sólida cinza claro de 1pt
                            .setPadding(10) // Adiciona um padding interno de 10 pontos
                            .setMarginBottom(15); // Espaço entre as perguntas

                    // Número da Pergunta e Texto
                    questionBlock.add(new Paragraph((i + 1) + ". " + pergunta)
                            .setFontSize(14)
                            .setBold()
                            .setMarginBottom(5));

                    // Lista de Opções
                    List optionsList = new List();
                    optionsList.setListSymbol("  "); 
                    for (int j = 0; j < opcoesArray.length(); j++) {
                        String optionText = opcoesArray.getString(j);
                        optionsList.add((ListItem) new ListItem(optionText)
                                .setFontSize(12)
                                .setFontColor(ColorConstants.BLACK)); 
                    }
                    questionBlock.add(optionsList.setMarginLeft(20).setMarginBottom(10));

                    // Resposta Correta 
                    if (includeCorrectAnswers && question.has("respostaCorreta")) {
                        String respostaCorreta = question.getString("respostaCorreta");
                        String displayedCorrectAnswer = "";

                        if (respostaCorreta.length() == 1 && Character.isLetter(respostaCorreta.charAt(0))) {
                            int correctIndex = Character.toUpperCase(respostaCorreta.charAt(0)) - 'A';
                            if (correctIndex >= 0 && correctIndex < opcoesArray.length()) {
                                displayedCorrectAnswer = opcoesArray.getString(correctIndex);
                            } else {
                                displayedCorrectAnswer = "(Opção inválida)";
                            }
                        } else {
                            displayedCorrectAnswer = respostaCorreta;
                        }

                        questionBlock.add(new Paragraph("Resposta Correta: " + displayedCorrectAnswer)
                                .setFont(boldFont)
                                .setFontSize(12)
                                .setFontColor(ColorConstants.BLACK) // Cor verde para a resposta
                                .setMarginTop(5)
                                .setMarginBottom(5) // Ajuste para menor espaçamento
                                .setHorizontalAlignment(HorizontalAlignment.LEFT));
                    }
                    document.add(questionBlock); 
                }
            }
        } catch (Exception e) {
            // Em caso de erro no parse do JSON ou na estrutura
            document.add(new Paragraph("Erro ao carregar questões do quiz. Conteúdo JSON inválido.")
                    .setFontSize(12)
                    .setFontColor(ColorConstants.RED));
        }

        document.close();
        return baos.toByteArray();
    }
}