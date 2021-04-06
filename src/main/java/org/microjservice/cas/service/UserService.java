package org.microjservice.cas.service;

import com.google.i18n.phonenumbers.Phonenumber;
import lombok.RequiredArgsConstructor;
import org.microjservice.cas.dao.UserDao;
import org.microjservice.cas.entity.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;


/**
 * User Service
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final   UserDao userDao;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private Boolean validatePassword(String originalPassword, String encryptedPassword) {
        return bCryptPasswordEncoder.matches(originalPassword, encryptedPassword);
    }

    public Boolean validateByEmail(String email, String originalPassword) throws AccountNotFoundException {
        return validatePassword(
                originalPassword,
                userDao.findByEmail(email)
                        .orElseThrow(AccountNotFoundException::new)
                        .getPassword()
        );
    }

    public Boolean validateByPhoneNumber(Phonenumber.PhoneNumber phoneNumber, String originalPassword) throws AccountNotFoundException {
        return validatePassword(
                originalPassword,
                userDao.findByCountryCodeAndPhone(phoneNumber.getCountryCode(),phoneNumber.getNationalNumber())
                        .orElseThrow(AccountNotFoundException::new)
                        .getPassword()
        );
    }

    public Boolean validateByUserName(String userName, String originalPassword) throws AccountNotFoundException {
        return validatePassword(
                originalPassword,
                userDao.findByUserName(userName)
                        .orElseThrow(AccountNotFoundException::new)
                        .getPassword()
        );
    }
}
