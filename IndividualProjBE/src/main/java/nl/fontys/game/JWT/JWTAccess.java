package nl.fontys.game.JWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import nl.fontys.game.Object.AuthorizationCredentials;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTAccess {

    private final String SECRET_KEY = "sosecret";
    private static final int LONGTITUDE=6000;//0

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(AuthorizationCredentials authorizationCredentials) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, authorizationCredentials.getLogin());
    }
    public String generateToken(String login) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, login);
    }
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + LONGTITUDE))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, String login) {
        final String username = extractUsername(token);
        return (username.equals(login) && !isTokenExpired(token));
    }
    public JWTAccess()
    {}
}
