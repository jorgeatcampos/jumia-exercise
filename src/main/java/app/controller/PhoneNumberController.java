package app.controller;

import app.dto.PhoneNumberDTO;
import app.service.PhoneNumberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller that handles all phone number related requests.
 */
@RestController
@RequestMapping("phoneNumber")
@AllArgsConstructor
public class PhoneNumberController {
    private final PhoneNumberService service;

    /**
     * Returns a list with all the phone numbers that exist in the database
     *
     * @return List with all the phone numbers that exist in the database
     */
    @GetMapping("/getList")
    public List<PhoneNumberDTO> getAllPhoneNumbers() {
        return service.getAllPhoneNumbers();
    }
}
