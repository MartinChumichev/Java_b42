package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addressbook")
public class ContactRecord {
    @Id
    @Column(name = "id")
    public int id;
    @Column(name = "firstname")
    public String firstname;
    @Column(name = "lastname")
    public String lastname;
    public String home;
    public String mobile;
    public String work;
    @Column(name = "phone2")
    public String secondaryPhone;
    public String email;
    public String email2;
    public String email3;
    public String address;

    public ContactRecord() {
    }

    public ContactRecord(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
