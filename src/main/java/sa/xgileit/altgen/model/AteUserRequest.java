package sa.xgileit.altgen.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter

public class AteUserRequest {
     private String userName;

    private String firstName;

    private String lastName;

    private String password;

    private String emailId;

    private String contactNumber;
    private Set<String> role;
}
