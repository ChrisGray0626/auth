package pers.ruizhi.auth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class AuthApplicationTests {

    @Test
    public void PasswordEncoderTest() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "123456";
        String encode = encoder.encode(rawPassword);
        System.out.println(encode);
        boolean isMatched = encoder.matches(rawPassword, encode);
        System.out.println(isMatched);
    }

}
