import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static com.google.i18n.phonenumbers.Phonenumber.PhoneNumber.CountryCodeSource.UNSPECIFIED;
import static org.microjservice.cas.VarietyIdAuthenticationHandler.PHONE_PATTERN;

/**
 * PhoneNumberUtil Test
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
public class PhoneNumberUtilTest {
    @SneakyThrows
    @Test
    public void testPhoneNumber(){
        String phoneNumberString = "+86 12344485698854";
        System.out.println(phoneNumberString.matches(PHONE_PATTERN));
        Phonenumber.PhoneNumber phoneNumber = PhoneNumberUtil.getInstance().parse(phoneNumberString, UNSPECIFIED.name());
        System.out.println(phoneNumber);
    }

}
