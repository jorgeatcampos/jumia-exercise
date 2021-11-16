package app.dto;

import app.model.PhoneNumberEnum;
import app.util.PhoneNumberUtil;
import app.validation.PhoneNumberValidator;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO that represents a phone number and its information.
 */
@Getter
@Setter
public class PhoneNumberDTO {
    /**
     * Name of the costumer who owns the phone number
     */
    private String customerName;

    /**
     * Phone number's country
     */
    private String country;

    /**
     * Phone number's country code
     */
    private String countryCode;

    /**
     * Phone number without its country code
     */
    private String number;

    /**
     * Indicates if the phone number is valid
     */
    private boolean state;

    /**
     * Creates a new PhoneNumberDTO instance
     *
     * @param customerName Name of the costumer who owns the phone number
     * @param phoneNumber Phone number
     * @throws IllegalArgumentException If any of the parameters are null or if the phone number doesn't contain a country code
     */
    public PhoneNumberDTO(String customerName, String phoneNumber) {
        if (customerName == null)
            throw new IllegalArgumentException("Customer name can't be null.");

        String countryCode = PhoneNumberUtil.getCountryCodeFromPhoneNumber(phoneNumber);

        this.customerName = customerName;
        this.country = PhoneNumberEnum.getCountryByCountryCode(countryCode);
        this.countryCode = countryCode;
        this.number = PhoneNumberUtil.getNumberWithoutCountryCode(phoneNumber);
        this.state = PhoneNumberValidator.isValidPhoneNumber(phoneNumber, countryCode);
    }
}
