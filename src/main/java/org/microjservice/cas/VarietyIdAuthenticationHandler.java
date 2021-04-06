package org.microjservice.cas;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apereo.cas.authentication.AuthenticationHandlerExecutionResult;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.credential.UsernamePasswordCredential;
import org.apereo.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.microjservice.cas.service.UserService;

import javax.security.auth.login.FailedLoginException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import static com.google.i18n.phonenumbers.Phonenumber.PhoneNumber.CountryCodeSource.UNSPECIFIED;

/**
 * Support email, user name and phone number login.
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
public class VarietyIdAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler {

    public static final String EMAIL_PATTERN = "^(.+)@(.+)$";
    public static final String PHONE_PATTERN = "^\\+(?:[0-9] ?){6,14}[0-9]$";
    public static final String USER_NAME_PATTERN = "[A-Za-z0-9_]+";

    private final UserService userService;

    public VarietyIdAuthenticationHandler(String name,
                                          ServicesManager servicesManager,
                                          PrincipalFactory principalFactory,
                                          Integer order,
                                          UserService userService) {
        super(name, servicesManager, principalFactory, order);
        this.userService = userService;
    }

    @SneakyThrows
    @Override
    protected AuthenticationHandlerExecutionResult authenticateUsernamePasswordInternal(UsernamePasswordCredential credential, String originalPassword) throws GeneralSecurityException, PreventedException {

        String id = credential.getUsername();

        if (id.matches(EMAIL_PATTERN) && userService.validateByEmail(id, originalPassword)
                || id.matches(PHONE_PATTERN) && userService.validateByPhoneNumber(getPhoneNumber(id), originalPassword)
                || id.matches(USER_NAME_PATTERN) && userService.validateByUserName(id, originalPassword)
        ) {
            return createHandlerResult(
                    credential,
                    principalFactory.createPrincipal(id),
                    Collections.emptyList()
            );
        }

        throw new FailedLoginException();
    }

    private Phonenumber.PhoneNumber getPhoneNumber(String id) throws NumberParseException {
        return PhoneNumberUtil.getInstance().parse(id, UNSPECIFIED.name());
    }
}
