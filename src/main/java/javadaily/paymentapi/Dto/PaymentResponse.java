package javadaily.paymentapi.Dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PaymentResponse {
    private final BigDecimal balance;
    private String message;
    private BigDecimal newBalance;

    public PaymentResponse(String message, BigDecimal balance) {
        this.message = message;
        this.balance = balance;
    }


}