package sa.xgileit.altgen.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import sa.xgileit.altgen.model.AteRoles;
import sa.xgileit.altgen.model.EURole;

import java.util.Optional;

public interface AteRolesRepository extends MongoRepository<AteRoles, String> {
    Optional<AteRoles> findByRoleName(EURole roleName);
}
