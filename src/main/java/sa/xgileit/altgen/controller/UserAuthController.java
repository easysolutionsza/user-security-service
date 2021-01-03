package sa.xgileit.altgen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sa.xgileit.altgen.errors.ResourceNotFoundException;
import sa.xgileit.altgen.model.*;
import sa.xgileit.altgen.repository.AteUserRepository;
import sa.xgileit.altgen.service.AteRolesService;
import sa.xgileit.altgen.service.AteUserService;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserAuthController {

    @Autowired
    AteUserService userService;
    @Autowired
    AteRolesService roleService;
    @Autowired
    AteUserRepository ateUserRepository;
    @GetMapping("/users")
    public List<AteUser> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/roles")
    public List<AteRoles> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity< AteUser > getEmployeeById(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        AteUser users = userService.getUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/registerUsers")
    public ResponseEntity<AteUserResponse> addUser(@Valid @RequestBody AteUserRequest userRq) {

        AteUserResponse response= userService.registerUser(userRq);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addRoles")
    public ResponseEntity<AteRoles> addRoles(@Valid @RequestBody AteRoles roles) {

        return ResponseEntity.ok( roleService.addRoles(roles));
    }


    @PutMapping("/users/{id}")
    public ResponseEntity <AteUserResponse> updateUser(@PathVariable(value = "id") Long userId,
                                                      @Valid @RequestBody AteUserRequest userRq) throws ResourceNotFoundException {
        AteUserResponse response= userService.updateUser(userId,userRq);


        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/employees/{id}")
    public Map< String, Boolean > deleteEmployee(@PathVariable(value = "id") Long userId)
    {
        return userService.removeUser(userId);
    }



    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {


         return ResponseEntity.ok( userService.authanticateUser(loginRequest));
    }


}
