package javadaily.paymentapi.Entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Access(AccessType.PROPERTY)
@Entity
@NoArgsConstructor
@Table(name = "users")
@NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username")
public class User {

    private Long id;
    private String username;
    private String password;
    private BigDecimal balance;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    @Column(nullable = false, precision = 10, scale = 2)
    public BigDecimal getBalance() {
        return balance;
    }

    @PrePersist
    public void prePersist() {
        if (balance == null) {
            balance = new BigDecimal("8.00");
        }
    }
}
