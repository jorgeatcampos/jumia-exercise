package app.util;

import app.validation.PhoneNumberValidator;

/**
 * Provides utility methods related to phone numbers.
 */
public class PhoneNumberUtil {
    /**
     * Returns the country code of a given phone number
     *
     * @param phoneNumber Phone number
     * @return Country code
     * @throws IllegalArgumentException If the phone number doesn't contain a country code
     */
    public static String getCountryCodeFromPhoneNumber(String phoneNumber) {
        if (!PhoneNumberValidator.containsCountryCode(phoneNumber))
            throw new IllegalArgumentException("Phone number is missing country code.");

        return phoneNumber.substring(phoneNumber.indexOf("(") + 1, phoneNumber.indexOf(")"));
    }

    /**
     * Returns the number without the country code part of a given phone number
     *
     * @param phoneNumber Phone number
     * @return Number without country code
     * @throws IllegalArgumentException If the phone number doesn't contain a country code
     */
    public static String getNumberWithoutCountryCode(String phoneNumber) {
        if (!PhoneNumberValidator.containsCountryCode(phoneNumber))
            throw new IllegalArgumentException("Phone number is missing country code.");

        return phoneNumber.split(" ")[1];
    }
}
