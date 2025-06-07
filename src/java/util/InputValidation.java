package util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class InputValidation {

    // Regex para login de usuário: pelo menos 3 caracteres e no máximo 35, sendo permitido 
    private static final Pattern USERNAME_PATTERN = 
    Pattern.compile("^[a-zA-Z0-9\\\\W]{3,35}$");

    // Regex para e-mail
    private static final Pattern EMAIL_PATTERN = 
    Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:.[a-zA-Z0-9-]+)*.[a-zA-Z]{2,7}$");

    // Regex para senha: mínimo 8 caracteres, pelo menos uma letra maiúscula,
    // uma minúscula, um número e um caractere especial (incluindo @$!%*?&)
    private static final Pattern PASSWORD_PATTERN = 
    Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*[^A-Za-z0-9]).{8,}$");

    // --- Métodos de Validação de Dados de Entrada ---

    public static boolean isNotNullOrEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean isValidLength(String input, int minLength, int maxLength) {
        if (input == null) {
            return false;
        }
        int length = input.length();
        return length >= minLength && length <= maxLength;
    }

    public static boolean isValidEmail(String email) {
        if (!isNotNullOrEmpty(email)) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }
      
    public static boolean isValidUsername(String username) {
        if (!isNotNullOrEmpty(username)) {
            return false;
        }
        return USERNAME_PATTERN.matcher(username).matches();
    }

    public static boolean isValidPassword(String password) {
        if (!isNotNullOrEmpty(password)) {
            return false;
        }
        return PASSWORD_PATTERN.matcher(password).matches();
    }
    
    public static boolean isValidQuizTopic(String topic) {
        return isNotNullOrEmpty(topic) && isValidLength(topic, 3, 69);
    }
    
   
}