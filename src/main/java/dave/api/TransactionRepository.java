package dave.api;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This is based on JPA, where the framework provides the repository object that talk to configured data source
 */
@Repository
public interface TransactionRepository extends CrudRepository<Transaction, String> {
}
