package util;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.EncodingException;

public class InputSanitization {

    
    /*
    Sanitiza uma string para uso seguro em HTML, prevenindo ataques XSS.
     Converte caracteres especiais em entidades HTML.
    */
    public static String sanitizeHtml(String input) {
        if (input == null || input.trim().isEmpty()) {
            return ""; // Retorna string vazia para inputs nulos ou vazios
        }
        // O ESAPI converte < para &lt;, > para &gt;, " para &quot;, ' para &#x27; e & para &amp;
        return ESAPI.encoder().encodeForHTML(input);
    }
    
    // Sanitiza uma string para uso seguro em URLs, como parâmetros de query.
    public static String sanitizeUrl(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "";
        }
        try {
            // Encode para URL, tratando espaços, caracteres especiais, etc.
            return ESAPI.encoder().encodeForURL(input);
        } catch (EncodingException e) {
            return ""; // Ou input, dependendo da sua política de segurança
        }
    }
    
    /* Remove tags HTML e potencialmente scripts de uma string. 
        Ideal para campos onde HTML não é esperado, mas pode ser inserido. */
    public static String removeHtmlTags(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "";
        }
        // Regex simples para remover tags HTML. Pode não ser 100% robusto para todos os casos,
        // mas é um bom começo. Para sanitização mais complexa de HTML, considere bibliotecas como Jsoup.
        return input.replaceAll("<[^>]*>", "");
    }
    
    

}