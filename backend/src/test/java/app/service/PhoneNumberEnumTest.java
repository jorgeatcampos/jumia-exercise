package app.service;

import app.model.PhoneNumberEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PhoneNumberEnumTest {
    private static final String CAMEROON_COUNTRY = PhoneNumberEnum.CAMEROON.getCountry();
    private static final String CAMEROON_COUNTRY_CODE = PhoneNumberEnum.CAMEROON.getCountryCode();
    private static final String CAMEROON_REGEX = PhoneNumberEnum.CAMEROON.getRegex();

    private static final String UGANDA_COUNTRY = PhoneNumberEnum.UGANDA.getCountry();
    private static final String UGANDA_COUNTRY_CODE = PhoneNumberEnum.UGANDA.getCountryCode();

    @Test
    public void getCountryByCountryCode_ShouldReturnCorrectCountry() {
        Assertions.assertEquals(CAMEROON_COUNTRY, PhoneNumberEnum.getCountryByCountryCode(CAMEROON_COUNTRY_CODE));
        Assertions.assertNotEquals(CAMEROON_COUNTRY, PhoneNumberEnum.getCountryByCountryCode(UGANDA_COUNTRY_CODE));
    }

    @Test
    public void getCountryByCountryCode_ThrowsExceptionIfCountryCodeIsInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> PhoneNumberEnum.getCountryByCountryCode("Portugal"));
    }

    @Test
    public void getRegexByCountryCode_ShouldReturnCorrectRegex() {
        Assertions.assertEquals(CAMEROON_REGEX, PhoneNumberEnum.getRegexByCountryCode(CAMEROON_COUNTRY_CODE));
        Assertions.assertNotEquals(CAMEROON_REGEX, PhoneNumberEnum.getRegexByCountryCode(UGANDA_COUNTRY_CODE));
    }

    @Test
    public void getRegexByCountryCode_ThrowsExceptionIfCountryCodeIsInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> PhoneNumberEnum.getRegexByCountryCode("Portugal"));
    }

    @Test
    public void getCountryCodeByCountry_ShouldReturnCorrectCountryCode() {
        Assertions.assertEquals(CAMEROON_COUNTRY_CODE, PhoneNumberEnum.getCountryCodeByCountry(CAMEROON_COUNTRY));
        Assertions.assertNotEquals(CAMEROON_COUNTRY_CODE, PhoneNumberEnum.getCountryCodeByCountry(UGANDA_COUNTRY));
    }

    @Test
    public void getCountryCodeByCountry_ThrowsExceptionIfCountryIsInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> PhoneNumberEnum.getCountryCodeByCountry("Portugal"));
    }
}
