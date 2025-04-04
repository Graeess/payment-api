package javadaily.paymentapi.Repository;
import javadaily.paymentapi.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}