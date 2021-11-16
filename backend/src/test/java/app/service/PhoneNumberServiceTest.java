package app.service;

import app.model.PhoneNumberEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PhoneNumberServiceTest {
    @Autowired
    private PhoneNumberService service;

    @Test
    public void getAllCountries_ListShouldNotBeEmpty() {
        Assertions.assertFalse(service.getAllCountries().isEmpty());
    }

    @Test
    public void getAllCountries_ListShouldHave5Countries() {
        Assertions.assertEquals(5, service.getAllCountries().size());
    }

    @Test
    public void getAllCountries_ListShouldHaveAllTheExpectedCountries() {
        List<String> countryList = service.getAllCountries();

        Assertions.assertTrue(countryList.contains(PhoneNumberEnum.CAMEROON.getCountry()));
        Assertions.assertTrue(countryList.contains(PhoneNumberEnum.ETHIOPIA.getCountry()));
        Assertions.assertTrue(countryList.contains(PhoneNumberEnum.MOROCCO.getCountry()));
        Assertions.assertTrue(countryList.contains(PhoneNumberEnum.MOZAMBIQUE.getCountry()));
        Assertions.assertTrue(countryList.contains(PhoneNumberEnum.UGANDA.getCountry()));
    }
}