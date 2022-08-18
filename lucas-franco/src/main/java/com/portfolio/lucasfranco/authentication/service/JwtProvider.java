package com.portfolio.lucasfranco.authentication.service;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.portfolio.lucasfranco.authentication.dto.JwtDTO;
import com.portfolio.lucasfranco.authentication.entity.UserPrincipal;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    private final static Logger logger= LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        List<String> roles = userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }
    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e){
            logger.error("Token malformed");
        }catch (UnsupportedJwtException e){
            logger.error("Token unsupported");
        }catch (ExpiredJwtException e){
            logger.error("Token expired");
        }catch (IllegalAccessError e){
            logger.error("Token empty");
        }catch (SignatureException e){
            logger.error("Fail in the signature");
        }
        return false;
    }

    public String refreshToken(JwtDTO jwtDTO) throws ParseException {
        try {
            Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(jwtDTO.getToken());
        }catch (ExpiredJwtException e){
            JWT jwt = JWTParser.parse(jwtDTO.getToken());
            JWTClaimsSet claimsSet = jwt.getJWTClaimsSet();
            String nameUser = claimsSet.getSubject();
            List<String> roles = (List<String>) claimsSet.getClaim("roles");
            return Jwts.builder()
                    .setSubject(nameUser)
                    .claim("roles", roles)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + expiration))
                    .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                    .compact();
        }
        return null;

    }

}
