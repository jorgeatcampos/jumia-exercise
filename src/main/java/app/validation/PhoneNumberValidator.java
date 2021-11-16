package app.validation;

import app.model.PhoneNumberEnum;
import app.util.PhoneNumberUtil;

/**
 * Validates phone numbers.
 */
public class PhoneNumberValidator {
    /**
     * Validates if a given phone number contains a country code
     *
     * @param phoneNumber Phone number
     * @return True if the phone number contains a country code, false otherwise
     */
    public static boolean containsCountryCode(String phoneNumber) {
        return (phoneNumber != null && (phoneNumber.contains("(") && phoneNumber.contains(")")));
    }

    /**
     * Validates a phone number through regex
     *
     * @param phoneNumber Phone number
     * @return True if the phone number matches its corresponding regex, false otherwise
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String countryCode = PhoneNumberUtil.getCountryCodeFromPhoneNumber(phoneNumber);
        String regex = PhoneNumberEnum.getRegexByCountryCode(countryCode);

        return phoneNumber.matches(regex);
    }

    /**
     * Validates a phone number through regex
     *
     * @param phoneNumber Phone number
     * @param countryCode Phone number's country code
     * @return True if the phone number matches its corresponding regex, false otherwise
     */
    public static boolean isValidPhoneNumber(String phoneNumber, String countryCode) {
        String regex = PhoneNumberEnum.getRegexByCountryCode(countryCode);

        return phoneNumber.matches(regex);
    }
}
