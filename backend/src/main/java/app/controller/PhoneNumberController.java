package app.controller;

import app.dto.PhoneNumberDTO;
import app.dto.PhoneNumberRequest;
import app.service.PhoneNumberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
     * Returns a list of phone numbers paginated and filtered, if any filtering parameter was specified
     *
     * @param country Country
     * @param isValid True if it should return only valid numbers, false otherwise
     * @param numberOfRecords Number of records per page
     * @param pageIndex Page index
     * @return List of phone numbers paginated and filtered, if any filtering parameter was specified
     */
    @GetMapping("/getList")
    public List<PhoneNumberDTO> getPhoneNumbers(@RequestParam(required = false) String country, @RequestParam(required = false) Boolean isValid,
                                                @RequestParam(required = false) Integer numberOfRecords, @RequestParam(required = false) Integer pageIndex) {
        PhoneNumberRequest request = new PhoneNumberRequest(country, isValid, numberOfRecords, pageIndex);
        return service.getAllPhoneNumbers(request);
    }

    /**
     * Returns a list with all the phone numbers' countries that exist in the database
     *
     * @return List with all the phone numbers' countries that exist in the database
     */
    @GetMapping("/getCountries")
    public List<String> getAllCountries() {
        return service.getAllCountries();
    }
}
