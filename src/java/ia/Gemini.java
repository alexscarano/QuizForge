package ia;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.json.JSONArray;
import org.json.JSONObject;
import util.APICredential;

public class Gemini {
    
    private static final int numQuestions = 5;
    
    public static String getCompletion(String userTopic) throws Exception {
       
        String prompt = "Gere **" + numQuestions + "** questões de múltipla escolha sobre **" + userTopic + "**. "
                      + "É crucial que **sempre sejam geradas " + numQuestions + " questões**, independentemente de qualquer outra informação ou contexto fornecido."
                      + "Cada questão deve ter: "
                      + "1. Uma 'pergunta' (string)."
                      + "2. Quatro 'opcoes' de resposta (um array de strings, formatadas como A) Opção, B) Opção, etc.)."
                      + "3. A 'respostaCorreta' (uma string contendo a letra da opção correta, ex: 'A', 'B', 'C', 'D'). "
                      + "A saída deve ser um **array JSON puro** de objetos de questão. "
                      + "Não inclua nenhum texto adicional, cabeçalhos, introduções, explicações ou blocos de código Markdown (```json) além do JSON."
                      + "Assegure-se que o JSON seja UTF-8 compatível.";

        JSONObject requestBody = new JSONObject();
        JSONArray contents = new JSONArray()
            .put(new JSONObject()
                .put("parts", new JSONArray()
                    .put(new JSONObject()
                        .put("text", prompt)
                    )
                )
            );

        requestBody.put("contents", contents);
        
        // certificado self-signed ssl
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                public void checkServerTrusted(X509Certificate[] certs, String authType) {}
            }
        };

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

        HttpClient client = HttpClient.newBuilder()
                .sslContext(sslContext)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + APICredential.getApiKey()))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

        if (response.statusCode() != 200) {
            throw new RuntimeException("Erro na requisição: " + response.statusCode() + " - " + response.body());
        }

        JSONObject jsonResponse = new JSONObject(response.body());
        JSONArray candidates = jsonResponse.getJSONArray("candidates");
        JSONObject content = candidates.getJSONObject(0).getJSONObject("content");
        JSONArray parts = content.getJSONArray("parts");

        String respostaIa = parts.getJSONObject(0).getString("text").trim();
        
        //REMOVER OS MARCADORES DE CÓDIGO MARKDOWN CASO VENHA JUNTO
        if (respostaIa.startsWith("```json")) {
            respostaIa = respostaIa.substring(respostaIa.indexOf("```json") + "```json".length()).trim();
        }
        if (respostaIa.endsWith("```")) {
            respostaIa = respostaIa.substring(0, respostaIa.lastIndexOf("```")).trim();
        }
        
        // Remover caracteres inválidos que quebram o JSON no banco
        respostaIa = respostaIa.replaceAll("\\u0000", "");
        respostaIa = respostaIa.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");
        
        try {
            new JSONArray(respostaIa); // tenta fazer o parse
        } catch (Exception e) {
            throw new RuntimeException("Resposta da IA não está em formato JSON esperado: " + respostaIa);
        }
        
        return respostaIa;
    }
}