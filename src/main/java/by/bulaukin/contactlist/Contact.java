package by.bulaukin.contactlist;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@ToString
@FieldNameConstants
public class Contact {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
