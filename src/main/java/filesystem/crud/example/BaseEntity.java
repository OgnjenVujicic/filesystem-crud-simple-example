package filesystem.crud.example;


import lombok.EqualsAndHashCode;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

@EqualsAndHashCode
abstract class BaseEntity <T extends BaseEntity<?>> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private ArrayList<T> records = new ArrayList<>();

    private String id;

    private String getFileName(){
        return this.getClass().getSimpleName() + ".ser";
    }

    BaseEntity()
    {
        deserializeToList();
    }

    private void deserializeToList() {
        File file = new File(getFileName());
        if(file.exists()) {
            try ( FileInputStream fileIn = new FileInputStream(getFileName());
              ObjectInputStream objectIn = new ObjectInputStream(fileIn);) {
                records = (ArrayList<T>) objectIn.readObject();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void save() {

        File file = new File(getFileName());

        try (
                FileOutputStream fout = new FileOutputStream(file);
                ObjectOutputStream oout = new ObjectOutputStream(fout);
             ) {

            this.setId(generateId());
            records.add((T)this);
            oout.writeObject(records);
            System.out.println("The Object  was successfully written to a file.");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method for finding the object.
     * This method is not static, as we can not get the child class inside static method in Java
     * therefore cannot return object of that class.
     */
    public T find(String id) {
        deserializeToList();
        var filteredList =  records.stream().filter(rec -> rec.getId().equals(id)).toList();
        if(!filteredList.isEmpty()) {
            return filteredList.get(0);
        }
        return null;
    }

    /**
     * Method for finding the object.
     * If deserialize parameter is false Deserialization is not used as we have all elements cached in the list.
     */
    public T find(String id, boolean deserialize) {
        if(!deserialize) {
            var filteredList =  records.stream().filter(rec -> rec.getId().equals(id)).toList();
            if(!filteredList.isEmpty()) {
                return filteredList.get(0);
            }
            return null;
        }
        return find(id);
    }

    public void delete() {
        File file = new File(getFileName());

        try (
                FileOutputStream fout = new FileOutputStream(file);
                ObjectOutputStream oout = new ObjectOutputStream(fout);
        ) {

            records.removeIf(rec -> rec.getId().equals(this.getId()));
            oout.writeObject(records);
            this.setId(null);
            System.out.println("The Object was successfully removed from file.");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private String generateId(){
        return UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
