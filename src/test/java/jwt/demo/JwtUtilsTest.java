package jwt.demo;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JwtUtilsTest {

    @Test
    void getKey() {
        System.out.println(JwtUtils.getKey());
    }

    @DisplayName("토큰 생성 및 유효성 검사")
    @Test
    void getAccessToken_and_check_verification() {
        var token = JwtUtils.generateToken("test@test.com");
        System.out.println("token: " + token);
        var claims = JwtUtils.getClaims(token);
        System.out.println("claims: " + claims);
    }
}