package javadaily.paymentapi.Service;

import javadaily.paymentapi.Dto.LoginRequest;
import javadaily.paymentapi.Entity.User;
import javadaily.paymentapi.Repository.UserRepository;
import javadaily.paymentapi.Security.JwtTokenBlacklist;
import javadaily.paymentapi.Security.JwtTokenProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenBlacklist jwtTokenBlacklist;
    private final BruteForceProtectionService bruteForceProtectionService;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder,
                       JwtTokenProvider jwtTokenProvider, JwtTokenBlacklist jwtTokenBlacklist,
                       BruteForceProtectionService bruteForceProtectionService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtTokenBlacklist = jwtTokenBlacklist;
        this.bruteForceProtectionService = bruteForceProtectionService;
    }

    public String login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();


        if (bruteForceProtectionService.isLocked(username)) {
            throw new RuntimeException("Account is locked due to multiple failed login attempts. Try again later.");
        }

        Optional<User> userOptional = userRepository.findByUsername(username);


        if (userOptional.isEmpty() || !passwordEncoder.matches(loginRequest.getPassword(), userOptional.get().getPassword())) {
            bruteForceProtectionService.registerFailedAttempt(username);
            throw new RuntimeException("Invalid username or password");
        }

        bruteForceProtectionService.resetAttempts(username);

        return jwtTokenProvider.generateToken(username);
    }

    public void logout(String token) {
        jwtTokenBlacklist.blacklistToken(token);
    }
}
