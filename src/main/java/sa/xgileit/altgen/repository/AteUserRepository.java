package sa.xgileit.altgen.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import sa.xgileit.altgen.model.AteUser;

import java.util.Optional;


@Repository
public interface AteUserRepository extends MongoRepository<AteUser, Long> {
    Optional<AteUser> findByUserName(String userName);
    Boolean existsByUserName(String userName);

    Boolean existsByEmailId(String emailId);

}
