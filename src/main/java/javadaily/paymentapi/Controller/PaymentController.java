package javadaily.paymentapi.Controller;
import javadaily.paymentapi.Dto.PaymentResponse;
import javadaily.paymentapi.Service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> processPayment() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        PaymentResponse response = paymentService.processPayment(username);
        return ResponseEntity.ok(response);
    }
}