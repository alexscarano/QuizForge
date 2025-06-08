package ia;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.json.JSONArray;
import org.json.JSONObject;
import util.credentials.APICredential;

public class Gemini {
    
    private static final int numQuestions = 5;
    
    public static String getCompletion(String userTopic) throws Exception {
       String prompt = "Você é um gerador de quizzes em formato JSON. Sua única função é gerar um array JSON de questões. "
                 + "Gere **" + numQuestions + "** questões de múltipla escolha sobre **" + userTopic + "**. "
                 + "É **ABSOLUTAMENTE CRÍTICO** que você gere **EXATAMENTE " + numQuestions + " questões**, sem exceções ou desvios."
                 + "Cada questão deve ter os seguintes campos e formatos rigorosos: "
                 + "1. **\"pergunta\"** (string): A pergunta do quiz."
                 + "2. **\"opcoes\"** (array de strings): Uma lista de 4 opções de resposta, cada uma formatada como 'A) Opção', 'B) Opção', etc."
                 + "3. **\"respostaCorreta\"** (string): A letra da opção correta (ex: 'A', 'B', 'C', 'D')."
                 + "---"
                 + "**FORMATO DA SAÍDA:**"
                 + "A sua resposta deve ser um **array JSON PURO e VÁLIDO**. "
                 + "NÃO inclua nenhum texto introdutório, explicações, saudações, conclusões, cabeçalhos, ou blocos de código Markdown (```json) ANTES OU DEPOIS do JSON."
                 + "Comece sua resposta diretamente com `[` e termine com `]`. "
                 + "Assegure-se que o JSON seja UTF-8 compatível e formatado corretamente."
                 + "---"
                 + "**INSTRUÇÃO CRÍTICA PARA CONTEÚDO (HTML/XML/CSS/CÓDIGO):**"
                 + "Se o tópico ou o conteúdo da pergunta/opção envolver tags HTML, XML, CSS, ou qualquer código que use os caracteres **'<'**, **'>'**, ou **'&'**, **VOCÊ DEVE DESCREVÊ-LOS TEXTUALMENTE** ou usar seus nomes, **NUNCA INCLUIR OS CARACTERES LITERAIS '<' ou '>'**. "
                 + "Por exemplo:"
                 + "  - Em vez de: `Qual é a tag <body>?` USE: `Qual é a tag 'body'?` ou `Qual tag representa o corpo de um documento HTML?`"
                 + "  - Em vez de: `O que significa &#x20AC;?` USE: `O que significa a entidade HTML para o símbolo do Euro?`"
                 + "  - Em vez de: `if (a < b)` USE: `se 'a' é menor que 'b'`"
                 + "**Para o caractere '&', utilize-o LITERALMENTE** (ex: `HTML & CSS`), **NÃO utilize a entidade HTML `&amp;`**. "
                 + "Retorne APENAS o JSON que adere a todas estas regras.";

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
        
        TrustManager[] trustAllCerts = selfSignedSSL();
        
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

        String responseAI = parts.getJSONObject(0).getString("text").trim();
        
        String sanitizedResponse = sanitizeResponse(responseAI);
       
        try {
            new JSONArray(sanitizedResponse); // tenta fazer o parse
        } catch (Exception e) {
            throw new RuntimeException("Resposta da IA não está em formato JSON esperado: " + responseAI);
        }
        
        return sanitizedResponse;
    }
    
   private static TrustManager[] selfSignedSSL() throws NoSuchAlgorithmException{
       TrustManager[] trustAllCerts = new TrustManager[]{
        new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {}
            public void checkServerTrusted(X509Certificate[] certs, String authType) {}
        }
    }; 
        return trustAllCerts;
    }

   private static String sanitizeResponse(String responseAI){

        if (responseAI.startsWith("```json")) {
            responseAI = responseAI.substring(responseAI.indexOf("```json") + "```json".length()).trim();
        }
        if (responseAI.endsWith("```")) {
            responseAI = responseAI.substring(0, responseAI.lastIndexOf("```")).trim();
        }

        responseAI = responseAI.replaceAll("\\u0000", "");
        responseAI = responseAI.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");

        return responseAI;
   }

}