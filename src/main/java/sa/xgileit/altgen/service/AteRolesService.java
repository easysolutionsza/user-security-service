package sa.xgileit.altgen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sa.xgileit.altgen.model.AteRoles;
import sa.xgileit.altgen.model.AteUser;
import sa.xgileit.altgen.model.AteUserRequest;
import sa.xgileit.altgen.model.AteUserResponse;
import sa.xgileit.altgen.repository.AteRolesRepository;
import sa.xgileit.altgen.utils.ObjectMapperUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AteRolesService {
    @Autowired
    AteRolesRepository rolesRepository;

    public List<AteRoles> getAllRoles()
    {
        return rolesRepository.findAll();
    }
    public AteRoles addRoles(AteRoles roles)
    {
        roles.setId(UUID.randomUUID().toString());
        roles.setCreatedDate(new Date());
        return rolesRepository.save(roles);
    }
}
