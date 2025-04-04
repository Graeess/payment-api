package javadaily.paymentapi.Config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    private String secret;
    private long expiration;

    public JwtConfig() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JwtConfig jwtConfig = (JwtConfig) o;
        return expiration == jwtConfig.expiration &&
                Objects.equals(secret, jwtConfig.secret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(secret, expiration);
    }

    @Override
    public String toString() {
        return "JwtConfig{" +
                "secret='" + secret + '\'' +
                ", expiration=" + expiration +
                '}';
    }
}

