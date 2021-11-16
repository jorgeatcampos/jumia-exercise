package app.dto;

import lombok.Getter;

/**
 * Provides a pageable request for phone numbers.
 */
@Getter
public class PhoneNumberRequest extends PageableRequest {
    /**
     * Phone number's country
     */
    private String country;

    /**
     * Phone number is valid
     */
    private Boolean isValid;

    public PhoneNumberRequest(String country, Boolean isValid, int numberOfRecords, int pageIndex) {
        super(numberOfRecords, pageIndex);
        this.country = country;
        this.isValid = isValid;
    }

    public PhoneNumberRequest(String country, Boolean isValid) {
        super();
        this.country = country;
        this.isValid = isValid;
    }

    public PhoneNumberRequest(String country) {
        this.country = country;
    }

    public PhoneNumberRequest(Boolean isValid) {
        this.isValid = isValid;
    }

    public PhoneNumberRequest(int numberOfRecords, int pageIndex) {
        super(numberOfRecords, pageIndex);
    }

    public PhoneNumberRequest() {
        super();
    }
}
