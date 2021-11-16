package app.service;

import app.dto.PhoneNumberDTO;
import app.repository.PhoneNumberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides a service layer for phone number related requests.
 */
@Service
@AllArgsConstructor
public class PhoneNumberService {
    private final PhoneNumberRepository repository;

    /**
     * Returns a list with all the phone numbers that exist in the database
     *
     * @return List with all the phone numbers that exist in the database
     */
    public List<PhoneNumberDTO> getAllPhoneNumbers() {
        return repository.selectAllPhoneNumbers();
    }
}
