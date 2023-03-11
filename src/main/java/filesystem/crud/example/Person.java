package filesystem.crud.example;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Person extends BaseEntity<Person> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String fistName;
    private String lastName;
    private Address address;

    @Override
    public String toString() {

        return "Person(id=" + this.getId() +
                ", fistName=" + this.getFistName() +
                ", lastName=" + this.getLastName() +
                ", address=" + this.getAddress().toString() +
                ")";
    }

}
