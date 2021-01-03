package sa.xgileit.altgen.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
public class BaseEntity {

    @NotNull
    @NotBlank
    private String countryCode;
    @NotNull
    @NotBlank
    private String currencyCode;

    @NotNull
    private String status = "A";// default status as active

    private String createdBy = "";
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdDate =  new Date();

    private String updatedBy = "";
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date updatedDate =  new Date(0);

    private String deletedBy ="";
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date deletedDate = new Date(0);

}
