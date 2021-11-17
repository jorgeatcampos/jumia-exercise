package app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Phone number list
 */
@AllArgsConstructor
@Getter
public class PhoneNumberList {
    /**
     * List of PhoneNumberDTO
     */
    private List<PhoneNumberDTO> phoneNumberDTOList;

    /**
     * Total number of records without pagination
     */
    private int totalRecords;
}
