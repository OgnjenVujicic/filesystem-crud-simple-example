package filesystem.crud.example;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Address extends BaseEntity<Address> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String street;
    private String city;
    private String state;
    private String zipCode;

    @Override
    public String toString() {

        return "Address(id=" + this.getId() +
                ", street=" + this.getStreet() +
                ", city=" + this.getCity() +
                ", state=" + this.getState() +
                ", zipCode=" + this.getZipCode() +
                ")";
    }

}
