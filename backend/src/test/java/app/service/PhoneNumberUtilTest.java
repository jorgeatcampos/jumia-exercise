package app.service;

import app.model.PhoneNumberEnum;
import app.util.PhoneNumberUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PhoneNumberUtilTest {
    private static final String ETHIOPIA_COUNTRY_CODE = PhoneNumberEnum.ETHIOPIA.getCountryCode();

    @Test
    public void getCountryCodeFromPhoneNumber_ShouldReturnCountryCode() {
        Assertions.assertEquals(ETHIOPIA_COUNTRY_CODE, PhoneNumberUtil.getCountryCodeFromPhoneNumber("(251) 12-345-6789"));
        Assertions.assertNotEquals(ETHIOPIA_COUNTRY_CODE, PhoneNumberUtil.getCountryCodeFromPhoneNumber("(258) 12-345-6789"));
    }

    @Test
    public void getCountryCodeFromPhoneNumber_ThrowsExceptionIfPhoneNumberIsInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> PhoneNumberUtil.getCountryCodeFromPhoneNumber("12-345-6789"));
    }

    @Test
    public void getNumberWithoutCountryCode_ShouldReturnPhoneNumberWithoutCountryCode() {
        Assertions.assertEquals("12-345-6789", PhoneNumberUtil.getNumberWithoutCountryCode("(251) 12-345-6789"));
    }

    @Test
    public void getNumberWithoutCountryCode_ThrowsExceptionIfPhoneNumberIsInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> PhoneNumberUtil.getNumberWithoutCountryCode("12-345-6789"));
    }
}
