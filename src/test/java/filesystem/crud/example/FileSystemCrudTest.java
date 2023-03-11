package filesystem.crud.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileSystemCrudTest {

    @DisplayName("Test Application")
    @Test
    void testApp() {
        var address = new Address("5455 Apache Trail", "Queen Creek", "AZ", "85243");
        var person = new Person("Jane", "Smith", address);
        var biz = new Business("Alliance Reservations Network", address);

        assertNull(person.getId());
        person.save();
        assertNotNull(person.getId());

        assertNull(biz.getId());
        biz.save();
        assertNotNull(biz.getId());

        Person savedPerson = person.find(person.getId());
        assertNotNull(savedPerson);
        assertEquals(person.getAddress(), address);
        assertEquals(savedPerson.getAddress(), address);
        assertEquals(person.getId(), savedPerson.getId());
        assertEquals(person.getFistName(), savedPerson.getFistName());
        assertEquals(person.getLastName(), savedPerson.getLastName());
        assertEquals(person, savedPerson);
        assertNotSame(person, savedPerson);
        assertNotSame(person.getAddress(), savedPerson.getAddress());
        assertEquals(person.getAddress(), savedPerson.getAddress());

        Business savedBusiness = biz.find(biz.getId());
        assertNotNull(savedBusiness);
        assertSame(biz.getAddress(), address);
        assertEquals(savedBusiness.getAddress(), address);
        assertEquals(biz.getId(), savedBusiness.getId());
        assertEquals(biz.getName(), savedBusiness.getName());

        assertEquals(biz, savedBusiness);
        assertNotSame(biz, savedBusiness);
        assertNotSame(biz.getAddress(), savedBusiness.getAddress());
        assertEquals(biz.getAddress(), savedBusiness.getAddress());

        var deletedPersonId = person.getId();
        person.delete();
        assertNull(person.getId());
        assertNull(person.find(deletedPersonId));

        var deletedBizId = biz.getId();
        biz.delete();
        assertNull(biz.getId());
        assertNull(biz.find(deletedBizId));
    }

}
