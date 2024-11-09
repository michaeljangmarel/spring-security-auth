package org.example.springsecurityrest.service.jwtImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.springsecurityrest.service.JwtService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtIMPL  implements JwtService {
//    1
    public String generateToken(UserDetails userDetails) {
      return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
              .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
              .signWith(getSignKey() , SignatureAlgorithm.HS256)
              .compact();
    }
//3
    private  <T> T extractClaims (String tk , Function<Claims , T> claimsTFunction) {
        final  Claims claims = extractAllClaims(tk);
        return claimsTFunction.apply(claims);
    }

//    2
    private Key getSignKey() {
        byte[] key = Decoders.BASE64.decode("OGljYmQyNzM4ZmFkNzY5NTJiY2VmNzAzMjQ5M2E3Nzc3NGFlZmFiOA==");
        return Keys.hmacShaKeyFor(key);
    }

//    5
    public  String extractUsername(String token) {
        return  extractClaims(token, Claims::getSubject);
    }
//    4
    private  Claims extractAllClaims(String tk) {
        return Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(tk).getBody();
    }

//    6
    public boolean isTokenValid(String token , UserDetails userDetails) {
        final  String username  = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

//    7
    private  boolean isTokenExpired(String token) {
        return extractClaims(token , Claims::getExpiration).before(new Date());
    }
// 8
    public  String refreshToken(Map<String,Object> claims , UserDetails userDetails) {
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignKey() , SignatureAlgorithm.HS256)
                .compact();
    }
}
