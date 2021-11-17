package app.repository;

import app.dto.PhoneNumberDTO;
import app.dto.PhoneNumberRequest;
import app.model.PhoneNumberEnum;
import com.vladmihalcea.hibernate.query.ListResultTransformer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Repository to handle all phone number related queries.
 */
@Repository
public class PhoneNumberRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Selects all the phone numbers, paginated, according to the filtering parameters received
     *
     * @param request Object containing filtering parameters and pagination settings
     * @return List of phone numbers paginated and filtered, if any filtering parameter was specified
     */
    public List<PhoneNumberDTO> selectPhoneNumbers(PhoneNumberRequest request) {
        String sql = selectPhoneNumbersQueryBuilder(request.getCountry(), request.getIsValid());

        Query query = entityManager.createNativeQuery(sql);
        List<PhoneNumberDTO> phoneNumberDTOList = query.unwrap(org.hibernate.query.Query.class)
                .setMaxResults(request.getNumberOfRecords())
                .setFirstResult(request.getNumberOfRecords() * request.getPageIndex())
                .setResultTransformer( // method is deprecated but there's currently no alternative to it
                    (ListResultTransformer)
                        (tuple, aliases) -> new PhoneNumberDTO(
                            ((int) tuple[0]),
                            ((String) tuple[1]),
                            ((String) tuple[2])
                        )
                )
                .getResultList();

//        if (request.getIsValid() != null)
//            phoneNumberDTOList.removeIf(dto -> !dto.isState() == request.getIsValid());

        return phoneNumberDTOList;
    }

    /**
     * Selects all the distinct country codes existing in the database
     *
     * @return All the distinct country codes existing in the database
     */
    public List<String> selectDistinctCountryCodes() {
        String sqlSelect = "SELECT DISTINCT SUBSTR(c.phone, " +
                "INSTR(c.phone, '(') + 1, " +
                "INSTR(c.phone, ')') - INSTR(c.phone, '(') - 1)";

        return entityManager.createNativeQuery(sqlSelect + " FROM customer c").getResultList();
    }

    /**
     * Builds the select phone numbers query based on the filtering parameters received
     *
     * @param country Country
     * @param numbersShouldBeValid Specifies if the phone numbers should be filtered by their valid state
     * @return SQL query
     */
    private String selectPhoneNumbersQueryBuilder(String country, Boolean numbersShouldBeValid) {
        StringBuilder sqlQuery = new StringBuilder("SELECT c.id AS id, c.name AS customerName, c.phone AS phone \nFROM customer c");
        String countryCode = null;

        if (country != null) {
            countryCode = PhoneNumberEnum.getCountryCodeByCountry(country);
            sqlQuery.append("\nWHERE c.phone LIKE '%(").append(countryCode).append(")%'");
        }

        if (numbersShouldBeValid != null) {
            String isValid = numbersShouldBeValid ? "" : "NOT";
            String clause = sqlQuery.toString().contains("WHERE") ? "\n\tAND" : "\nWHERE";

            sqlQuery.append(clause).append(" (");

            // If there's a specified country, validate only by its regex, otherwise it should validate by all the regexes
//            if (country != null) {
//                sqlQuery.append("c.phone REGEXP '").append(PhoneNumberEnum.getRegexByCountryCode(countryCode)).append("'");
//            } else {
//                for (String regex : PhoneNumberEnum.getAllRegexes())
//                    sqlQuery.append("\n\t(c.phone REGEXP '").append(regex).append("') OR");
//
//                sqlQuery.setLength(sqlQuery.length() - 4); // remove the last 'AND'
//            }

            sqlQuery.append(")");
        }

        return sqlQuery.toString();
    }
}
