package app.service;

import app.validation.PhoneNumberValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PhoneNumberValidatorTest {
    @Test
    public void containsCountryCode_ShouldReturnTrueIfContainsCountryCode() {
        Assertions.assertTrue(PhoneNumberValidator.containsCountryCode("(237) 695539786"));
    }

    @Test
    public void containsCountryCode_ShouldReturnFalseIfNotContainsCountryCode() {
        Assertions.assertFalse(PhoneNumberValidator.containsCountryCode("695539786"));
    }

    @Test
    public void isValidPhoneNumber1Param_ShouldReturnTrueIfPhoneNumberIsValid() {
        Assertions.assertTrue(PhoneNumberValidator.isValidPhoneNumber("(237) 695539786"));
    }

    @Test
    public void isValidPhoneNumber1Param_ShouldReturnFalseIfPhoneNumberIsNotValid() {
        Assertions.assertFalse(PhoneNumberValidator.isValidPhoneNumber("(237) 6780009592"));
        Assertions.assertFalse(PhoneNumberValidator.isValidPhoneNumber("(251) 695539786"));
    }

    @Test
    public void isValidPhoneNumber2Params_ShouldReturnTrueIfPhoneNumberIsValid() {
        Assertions.assertTrue(PhoneNumberValidator.isValidPhoneNumber("(237) 695539786", "237"));
    }

    @Test
    public void isValidPhoneNumber2Params_ShouldReturnFalseIfPhoneNumberIsNotValid() {
        Assertions.assertFalse(PhoneNumberValidator.isValidPhoneNumber("(237) 6780009592", "237"));
        Assertions.assertFalse(PhoneNumberValidator.isValidPhoneNumber("(237) 695539786", "251"));
    }
}
