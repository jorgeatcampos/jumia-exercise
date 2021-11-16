package app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Valid phone numbers' countries.
 */
@AllArgsConstructor
@Getter
public enum PhoneNumberEnum {
    CAMEROON("Cameroon", "237", "\\(237\\)\\ ?[2368]\\d{7,8}$"),
    ETHIOPIA("Ethiopia", "251", "\\(251\\)\\ ?[1-59]\\d{8}$"),
    MOROCCO("Morocco", "212", "\\(212\\)\\ ?[5-9]\\d{8}$"),
    MOZAMBIQUE("Mozambique", "258", "\\(258\\)\\ ?[28]\\d{7,8}$"),
    UGANDA("Uganda", "256", "\\(256\\)\\ ?\\d{9}$");

    /**
     * Phone number's country
     */
    private String country;

    /**
     * Phone number's country code
     */
    private String countryCode;

    /**
     * Regex that validates a phone number according to its country
     */
    private String regex;

    /**
     * Returns the corresponding country's name of a given country code
     *
     * @param countryCode Country code
     * @return Corresponding country's name of a given country code
     */
    public static String getCountryByCountryCode(String countryCode) {
        return getEnumByCountryCode(countryCode).getCountry();
    }

    /**
     * Returns the regex that validates phone numbers of a given country code
     *
     * @param countryCode Country code
     * @return Regex that validates phone numbers from the given country code
     */
    public static String getRegexByCountryCode(String countryCode) {
        return getEnumByCountryCode(countryCode).getRegex();
    }

    /**
     * Returns the corresponding country code of a given country
     *
     * @param country Country
     * @return Corresponding country code of a given country
     */
    public static String getCountryCodeByCountry(String country) {
        return getEnumByCountry(country).getCountryCode();
    }

    /**
     * Returns a list with the regex of all the countries
     *
     * @return List with the regex of all the countries
     */
    public static List<String> getAllRegexes() {
        return Arrays.stream(PhoneNumberEnum.values()).map(PhoneNumberEnum::getRegex).collect(Collectors.toList());
    }

    //region PRIVATE
    /**
     * Returns the PhoneNumberEnum that matches the given country code.
     *
     * @param countryCode Country code
     * @return PhoneNumberEnum that matches the given country code
     */
    private static PhoneNumberEnum getEnumByCountryCode(String countryCode) {
        return Arrays.stream(values()).filter(e -> e.getCountryCode().equals(countryCode)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid country code -> " + countryCode));
    }

    /**
     * Returns the PhoneNumberEnum that matches the given country.
     *
     * @param country Country
     * @return PhoneNumberEnum that matches the given country
     */
    private static PhoneNumberEnum getEnumByCountry(String country) {
        return Arrays.stream(values()).filter(e -> e.getCountry().equals(country)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid country -> " + country));
    }
    //endregion
}
