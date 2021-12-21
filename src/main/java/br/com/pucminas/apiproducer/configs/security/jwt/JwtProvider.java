package br.com.pucminas.apiproducer.configs.security.jwt;

import static io.jsonwebtoken.Jwts.parser;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;
import javax.annotation.PostConstruct;
import br.com.pucminas.apiproducer.exceptions.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import br.com.pucminas.apiproducer.configs.security.KeyStoreConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtProvider {

    private KeyStore keyStore;
    private final KeyStoreConfig keyStoreConfig;

    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(keyStoreConfig.getPath());
            keyStore.load(resourceAsStream, keyStoreConfig.getSecret().toCharArray());
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            log.error("Error: {}",e.getMessage());
            throw new AppException("Um erro ao aconteceu ao processar o KeyStore");
        }
    }

    public String generateToken(Authentication authentication) {
        org.springframework.security.core.userdetails.User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(keyStoreConfig.getJwtExpirationInMillis())))
                .compact();
    }

    public String generateTokenWithUserName(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(keyStoreConfig.getJwtExpirationInMillis())))
                .compact();
    }

    private PrivateKey getPrivateKey() {
        try {
            PrivateKey result = (PrivateKey) keyStore.getKey(keyStoreConfig.getKey(),
                    keyStoreConfig.getSecret().toCharArray());

            if (result == null) {
                throw new AppException("Chave privada nao encontrada");
            }
            return result;
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new AppException("Um erro aconteceu ao recuperar a chave privada da Keystore");
        }
    }

    public boolean validateToken(String jwt) {
        try {
            parser().setSigningKey(getPublickey()).parseClaimsJws(jwt);
            return true;
        } catch (Exception e){
            log.error("Error ao receber o token: {}", e);
            return false;
        }
    }

    private PublicKey getPublickey() {
        try {
            return keyStore.getCertificate(keyStoreConfig.getKey()).getPublicKey();
        } catch (KeyStoreException e) {
            throw new AppException("Um erro aconteceu ao recuperar a chave publica da Keystore");
        }
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = parser().setSigningKey(getPublickey()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public Long getJwtExpirationInMillis() {
        return keyStoreConfig.getJwtExpirationInMillis();
    }

}
