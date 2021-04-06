import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * BCryptPasswordEncoder Test
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
public class BCryptPasswordEncoderTest {
    @Test
    public void testEncrypt(){
        String encodePassword = new BCryptPasswordEncoder().encode("123456");
        System.out.println(encodePassword);
    }
}
