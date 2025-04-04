package javadaily.paymentapi.Entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Entity
@Table(name = "payments")

@NoArgsConstructor
public class Payment {

    private Long id;
    private User user;
    private BigDecimal amount;
    private LocalDateTime timestamp;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    @Column(nullable = false, precision = 10, scale = 2)
    public BigDecimal getAmount() {
        return amount;
    }

    @Column(nullable = false)
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

}
