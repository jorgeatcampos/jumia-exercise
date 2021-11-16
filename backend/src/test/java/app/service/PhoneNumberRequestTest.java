package app.service;

import app.dto.PhoneNumberRequest;
import app.model.PhoneNumberEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PhoneNumberRequestTest {
    @Test
    public void newPhoneNumberRequest_AllParamsConstructorShouldHaveCorrectData() {
        PhoneNumberRequest phoneNumberRequest = new PhoneNumberRequest(PhoneNumberEnum.UGANDA.getCountry(), false, 15, 2);

        Assertions.assertEquals(PhoneNumberEnum.UGANDA.getCountry(), phoneNumberRequest.getCountry());
        Assertions.assertFalse(phoneNumberRequest.getIsValid());
        Assertions.assertEquals(15, phoneNumberRequest.getNumberOfRecords());
        Assertions.assertEquals(2, phoneNumberRequest.getPageIndex());
    }

    @Test
    public void newPhoneNumberRequest_FilteringParamsConstructorShouldHaveCorrectData() {
        PhoneNumberRequest phoneNumberRequest = new PhoneNumberRequest(PhoneNumberEnum.MOZAMBIQUE.getCountry(), true);

        Assertions.assertEquals(PhoneNumberEnum.MOZAMBIQUE.getCountry(), phoneNumberRequest.getCountry());
        Assertions.assertTrue(phoneNumberRequest.getIsValid());
    }

    @Test
    public void newPhoneNumberRequest_PaginationParamsConstructorShouldHaveCorrectData() {
        PhoneNumberRequest phoneNumberRequest = new PhoneNumberRequest(30, 5);

        Assertions.assertEquals(30, phoneNumberRequest.getNumberOfRecords());
        Assertions.assertEquals(5, phoneNumberRequest.getPageIndex());
    }

    @Test
    public void newPhoneNumberRequest_PaginationDefaultValuesShouldBeCorrect() {
        PhoneNumberRequest phoneNumberRequest = new PhoneNumberRequest();

        Assertions.assertEquals(10, phoneNumberRequest.getNumberOfRecords());
        Assertions.assertEquals(0, phoneNumberRequest.getPageIndex());
    }
}
