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
public class Business extends BaseEntity<Business> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private Address address;


    @Override
    public String toString() {

        return "Person(id=" + this.getId() +
                ", name=" + this.getName() +
                ", address=" + this.getAddress().toString() +
                ")";
    }

}
