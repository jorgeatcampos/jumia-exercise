package app.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PhoneNumberServiceTest {
    @Autowired
    private PhoneNumberService service;

    @Test
    public void getAllPhoneNumbers_ListShouldNotBeEmpty() {
        Assertions.assertFalse(service.getAllPhoneNumbers().isEmpty());
    }

    @Test
    public void getAllPhoneNumbers_ListShouldHave41Elements() {
        Assertions.assertEquals(41, service.getAllPhoneNumbers().size());
    }
}