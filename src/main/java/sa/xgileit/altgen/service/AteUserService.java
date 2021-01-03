package sa.xgileit.altgen.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sa.xgileit.altgen.config.security.UserDetailsImpl;
import sa.xgileit.altgen.config.security.jwt.JwtConfig;
import sa.xgileit.altgen.errors.ResourceNotFoundException;
import sa.xgileit.altgen.model.*;
import sa.xgileit.altgen.repository.AteRolesRepository;
import sa.xgileit.altgen.repository.AteUserRepository;
import sa.xgileit.altgen.utils.ObjectMapperUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AteUserService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    AteUserRepository userRepository;
    @Autowired
    AteRolesRepository rolesRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtConfig jwtConfig;
    public List<AteUser> getAllUsers()
    {
       return userRepository.findAll();
    }





    public  Optional<AteUser> getUserById(Long Id)

    {
        Optional<AteUser> ateUser=userRepository.findById(Id);
        return ateUser;
    }

    public AteUserResponse registerUser(AteUserRequest userReq)
    {
        AteUserResponse response= new AteUserResponse();
        if (userRepository.existsByUserName(userReq.getUserName())) {
            response.setResponseMessage("Error: Username is already taken!");
           return response;
        }

        if (userRepository.existsByEmailId(userReq.getEmailId())) {
            response.setResponseMessage("Error: Email is already in use!");
            return response;
        }
        AteUser userEntity= ObjectMapperUtils.map(userReq,AteUser.class);

        Set<String> strRoles = userReq.getRole();
        Set<AteRoles> roles = new HashSet<>();

        if (strRoles == null) {
            AteRoles userRole = rolesRepository.findByRoleName(EURole.ROLE_DEFAULT_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
        userEntity.setRoles(roles);
        userEntity.setId(UUID.randomUUID().toString());
        userEntity.setCreatedDate(new Date());
        userEntity.setPassword(encoder.encode(userReq.getPassword()));
        userEntity = userRepository.save(userEntity);
        return ObjectMapperUtils.map(userEntity, AteUserResponse.class);
    }

    public AteUserResponse updateUser(Long userId,AteUserRequest userReq)
    {

        AteUser  user = userRepository.findById(userId).get();
        AteUserResponse ateUserResponse= new AteUserResponse();
        BeanUtils.copyProperties(userReq,user);
      /*  user.seFir(employeeDetails.getEmailId());
        user.setLastName(employeeDetails.getLastName());
        user.setFirstName(employeeDetails.getFirstName());*/
        BeanUtils.copyProperties( userRepository.save(user),ateUserResponse);
         return ateUserResponse;
    }

    public Map < String, Boolean >  removeUser(Long userId)
    {
        AteUser user =userRepository.findById(userId).get();
        userRepository.delete(user);
        Map< String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;

    }

    public String authanticateUser(LoginRequest loginRequest)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtConfig.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

       return jwt;
    }
}
