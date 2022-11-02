package jwt.demo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.apache.commons.lang3.RandomStringUtils;

public class JwtUtils {
    private JwtUtils() {};
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//    private static final Key key2 = Keys.hmacShaKeyFor(getEncoded256Value().getBytes());

    public static String generateToken(String userEmail) {
        HashMap<String, String> claims = new LinkedHashMap<>();
        claims.put("userEmail", userEmail);
        return Jwts.builder()
            .setIssuedAt(new Date())
            .setClaims(claims)
            .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 7))
            .signWith(key)
            .compact();
    }

    public static Claims getClaims(String token) {
        if (token == null || token.equals("")) throw new IllegalArgumentException("Token Is Not Blank");
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public static String getEncoded256Value() {
        return RandomStringUtils.randomAlphanumeric(256);
    }

    public static Key getKey() {
        return key;
    }
}
