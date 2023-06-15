package com.example.planner.config;

import com.example.planner.model.Role;
import com.example.planner.repository.UserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
@Slf4j
public class JwtService {
    private final Environment environment;
    private final UserRepo userRepo;
    public Claims extractClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }
    public Key getSignInKey(){
        byte[] keyBytes= Decoders.BASE64URL.decode(environment.getProperty("JWT_SECRET"));
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims=extractClaims(token);
        return claimsResolver.apply(claims);
    }
    public String extractUsername(String token){
        return extractClaim(token,Claims::getSubject);
    }
    public String generateToken(UserDetails userDetails){
        log.info("trying to generate token");
        return generateToken(new HashMap<>(),userDetails);
    }
    public String generateToken(Map<String,Object> extraClaims,UserDetails userDetails){
        log.info("username {}",userDetails.getUsername());
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+1000*24*60)).signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }
    public boolean isTokenValid(String token,UserDetails userDetails){
        return extractUsername(token).equals(userDetails.getUsername())&& !isTokenExpired(token);
    }
    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }
    public Role extractRoles(String token){
        String username=extractUsername(token);
        return userRepo.findUserByEmail(username).getRole();
    }

}
