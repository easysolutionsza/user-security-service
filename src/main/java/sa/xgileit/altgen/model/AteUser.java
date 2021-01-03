package sa.xgileit.altgen.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;


@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ate_users")
public class AteUser extends BaseEntity{
    @Id
    private String id;


    @Size(max = 100)
    @NotNull
    @NotBlank
    private String userName;
    @Size(max = 100)
    private String firstName;
    @Size(max = 100)
    private String lastName;
    @NotNull
    @NotBlank
    @Size(max = 50)
    private String password;
    @NotBlank
    @Size(max = 75)
    @Email
    private String emailId;
    @Size(max = 15)
     private String contactNumber;
    @DBRef
    private Set<AteRoles> roles = new HashSet<>();
}
