package app.service;

import app.dto.PhoneNumberDTO;
import app.model.PhoneNumberEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PhoneNumberDTOTest {
    @Test
    public void newPhoneNumberDTO_ShouldHaveCorrectData() {
        PhoneNumberDTO phoneNumberDTO = new PhoneNumberDTO(1, "Customer", "(237) 695539786");

        Assertions.assertEquals(1, phoneNumberDTO.getId());
        Assertions.assertEquals("Customer", phoneNumberDTO.getCustomerName());
        Assertions.assertEquals(PhoneNumberEnum.CAMEROON.getCountry(), phoneNumberDTO.getCountry());
        Assertions.assertEquals(PhoneNumberEnum.CAMEROON.getCountryCode(), phoneNumberDTO.getCountryCode());
        Assertions.assertEquals("695539786", phoneNumberDTO.getNumber());
        Assertions.assertTrue(phoneNumberDTO.isState());
    }

    @Test
    public void newPhoneNumberDTO_ThrowsExceptionIfCustomerNameIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PhoneNumberDTO(1, null, "(237) 695539786"));
    }

    @Test
    public void newPhoneNumberDTO_ThrowsExceptionIfPhoneNumberIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PhoneNumberDTO(1, "Customer", null));
    }
}
