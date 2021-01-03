package sa.xgileit.altgen.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ate_roles")
public class AteRoles extends BaseEntity{
    @Id
    private String id;


    @Size(max = 100)
    @NotNull
    @NotBlank
    private String roleName;
    @Size(max = 100)
    @NotNull
    @NotBlank
    private String roleType;


}
