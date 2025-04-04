package javadaily.paymentapi.Service;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class BruteForceProtectionService {

    private static final int MAX_ATTEMPTS = 5;
    private static final long LOCKOUT_DURATION = 30; // 30 минут

    private final RedisTemplate<String, Integer> redisTemplate;

    public BruteForceProtectionService(RedisTemplate<String, Integer> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void registerFailedAttempt(String username) {
        String key = "login_attempts:" + username;
        Integer attempts = redisTemplate.opsForValue().get(key);
        if (attempts == null) {
            attempts = 0;
        }
        attempts++;
        redisTemplate.opsForValue().set(key, attempts, LOCKOUT_DURATION, TimeUnit.MINUTES);
    }

    public boolean isLocked(String username) {
        String key = "login_attempts:" + username;
        Integer attempts = redisTemplate.opsForValue().get(key);
        return attempts != null && attempts >= MAX_ATTEMPTS;
    }

    public void resetAttempts(String username) {
        String key = "login_attempts:" + username;
        redisTemplate.delete(key);
    }
}