package app.service;

import app.dto.PhoneNumberRequest;
import app.model.PhoneNumberEnum;
import app.repository.PhoneNumberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PhoneNumberRepositoryTest {
    @Autowired
    private PhoneNumberRepository repository;

    private PhoneNumberRequest request;

    @Test
    public void selectPhoneNumbers_ListShouldNotBeEmpty() {
        request = new PhoneNumberRequest();
        Assertions.assertFalse(repository.selectPhoneNumbers(request).getPhoneNumberDTOList().isEmpty());
    }

    @Test
    public void selectPhoneNumbers_FullListShouldHave41PhoneNumbers() {
        request = new PhoneNumberRequest(50, 0);
        Assertions.assertEquals(41, repository.selectPhoneNumbers(request).getPhoneNumberDTOList().size());
    }

    @Test
    public void selectPhoneNumbers_ListShouldHave9PhoneNumbersFromEthiopia() {
        request = new PhoneNumberRequest(PhoneNumberEnum.ETHIOPIA.getCountry());
        Assertions.assertEquals(9, repository.selectPhoneNumbers(request).getPhoneNumberDTOList().size());
    }

    //@Test
    public void selectPhoneNumbers_ListShouldHave7ValidPhoneNumbersFromEthiopia() {
        request = new PhoneNumberRequest(PhoneNumberEnum.ETHIOPIA.getCountry(), true);
        Assertions.assertEquals(7, repository.selectPhoneNumbers(request).getPhoneNumberDTOList().size());
    }

    //@Test
    public void selectPhoneNumbers_ListShouldHave2InvalidPhoneNumbersFromEthiopia() {
        request = new PhoneNumberRequest(PhoneNumberEnum.ETHIOPIA.getCountry(), false);
        Assertions.assertEquals(2, repository.selectPhoneNumbers(request).getPhoneNumberDTOList().size());
    }

    @Test
    public void selectPhoneNumbers_PaginatedListShouldHave5PhoneNumbersFromCameroonOnFirstPage() {
        request = new PhoneNumberRequest(PhoneNumberEnum.CAMEROON.getCountry(), null, 5, 0);
        Assertions.assertEquals(5, repository.selectPhoneNumbers(request).getPhoneNumberDTOList().size());
    }

    @Test
    public void selectPhoneNumbers_PaginatedListShouldHave4PhoneNumbersFromCameroonOnSecondPage() {
        request = new PhoneNumberRequest(PhoneNumberEnum.CAMEROON.getCountry(), null, 6, 1);
        Assertions.assertEquals(4, repository.selectPhoneNumbers(request).getPhoneNumberDTOList().size());
    }

    //@Test
    public void selectPhoneNumbers_PaginatedListShouldHave6ValidPhoneNumbersFromEthiopiaOnFirstPage() {
        request = new PhoneNumberRequest(PhoneNumberEnum.ETHIOPIA.getCountry(), true, 6, 0);
        Assertions.assertEquals(6, repository.selectPhoneNumbers(request).getPhoneNumberDTOList().size());
    }

    //@Test
    public void selectPhoneNumbers_PaginatedListShouldHave10TotalAmountOfRecordsFromCameroon() {
        request = new PhoneNumberRequest(PhoneNumberEnum.CAMEROON.getCountry(), true, 5, 0);
        Assertions.assertEquals(10, repository.selectPhoneNumbers(request).getTotalRecords());
    }

    @Test
    public void selectDistinctCountryCodes_ListShouldNotBeEmpty() {
        Assertions.assertFalse(repository.selectDistinctCountryCodes().isEmpty());
    }

    @Test
    public void selectDistinctCountryCodes_ListShouldHave5CountryCodes() {
        Assertions.assertEquals(5, repository.selectDistinctCountryCodes().size());
    }

    @Test
    public void selectDistinctCountryCodes_ListShouldHaveAllTheExpectedCountryCodes() {
        List<String> countryCodeList = repository.selectDistinctCountryCodes();

        Assertions.assertTrue(countryCodeList.contains(PhoneNumberEnum.CAMEROON.getCountryCode()));
        Assertions.assertTrue(countryCodeList.contains(PhoneNumberEnum.ETHIOPIA.getCountryCode()));
        Assertions.assertTrue(countryCodeList.contains(PhoneNumberEnum.MOROCCO.getCountryCode()));
        Assertions.assertTrue(countryCodeList.contains(PhoneNumberEnum.MOZAMBIQUE.getCountryCode()));
        Assertions.assertTrue(countryCodeList.contains(PhoneNumberEnum.UGANDA.getCountryCode()));
    }
}
