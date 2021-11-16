package app.repository;

import app.dto.PhoneNumberDTO;
import com.vladmihalcea.hibernate.query.ListResultTransformer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Repository to handle all phone number related queries
 */
@Repository
public class PhoneNumberRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Selects all the phone numbers that exist in the database
     *
     * @return List of phone numbers
     */
    public List<PhoneNumberDTO> selectAllPhoneNumbers() {
        Query query = entityManager.createNativeQuery("SELECT c.name AS customerName, c.phone AS phone FROM customer c");

        return query.unwrap(org.hibernate.query.Query.class)
                .setResultTransformer( // method is deprecated but there's currently no alternative to it
                    (ListResultTransformer)
                        (tuple, aliases) -> new PhoneNumberDTO(
                            ((String) tuple[0]),
                            ((String) tuple[1])
                        )
                )
                .getResultList();
    }
}
