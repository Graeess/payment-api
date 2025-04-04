package javadaily.paymentapi;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.Base64;

public class PasswordEncoderTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode("password");
        System.out.println("Зашифрованный пароль: " + encodedPassword);
    }




    public static class SecretKeyGenerator {
        public static void main(String[] args) {
            SecureRandom random = new SecureRandom();
            byte[] key = new byte[64];
            random.nextBytes(key);
            String encodedKey = Base64.getEncoder().encodeToString(key);
            System.out.println("Сгенерированный ключ: " + encodedKey);
        }
    }
}