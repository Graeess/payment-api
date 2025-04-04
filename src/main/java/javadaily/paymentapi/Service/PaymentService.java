package javadaily.paymentapi.Service;
import javadaily.paymentapi.Entity.User;
import org.springframework.transaction.annotation.Transactional;
import javadaily.paymentapi.Dto.PaymentResponse;
import javadaily.paymentapi.Entity.Payment;
import javadaily.paymentapi.Repository.PaymentRepository;
import javadaily.paymentapi.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Service
public class PaymentService {

    private static final BigDecimal PAYMENT_AMOUNT = new BigDecimal("1.10");

    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;

    public PaymentService(UserRepository userRepository, PaymentRepository paymentRepository) {
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
    }

    @Transactional

    public PaymentResponse processPayment(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));


        if (user.getBalance().compareTo(PAYMENT_AMOUNT) < 0) {
            throw new RuntimeException("Insufficient balance");
        }


        user.setBalance(user.getBalance().subtract(PAYMENT_AMOUNT));
        userRepository.save(user);


        Payment payment = new Payment();
        payment.setUser(user);
        payment.setAmount(PAYMENT_AMOUNT);
        payment.setTimestamp(LocalDateTime.now());
        paymentRepository.save(payment);

        return new PaymentResponse("Payment successful", user.getBalance());
    }
}