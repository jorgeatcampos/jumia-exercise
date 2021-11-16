package app.service;

import app.dto.PhoneNumberDTO;
import app.dto.PhoneNumberRequest;
import app.model.PhoneNumberEnum;
import app.repository.PhoneNumberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides a service layer for phone number related requests.
 */
@Service
@AllArgsConstructor
public class PhoneNumberService {
    private final PhoneNumberRepository repository;

    /**
     * Returns a list of phone numbers paginated and filtered, if any filtering parameter was specified
     *
     * @return List of phone numbers paginated and filtered, if any filtering parameter was specified
     */
    public List<PhoneNumberDTO> getAllPhoneNumbers(PhoneNumberRequest request) {
        return repository.selectPhoneNumbers(request);
    }

    /**
     * Returns a list with all the phone numbers' countries that exist in the database
     *
     * @return List with all the phone numbers' countries that exist in the database
     */
    public List<String> getAllCountries() {
        List<String> countryList = new ArrayList<>();
        List<String> countryCodeList = repository.selectDistinctCountryCodes();

        countryCodeList.forEach(countryCode -> {
            countryList.add(PhoneNumberEnum.getCountryByCountryCode(countryCode));
        });

        return countryList;
    }
}
