package model;

import java.util.Objects;

public class ContactData {

    private final String id;
    private final String firstName;
    private final String middleName;
    private final String lastName;

    private final String homePhone;
    private final String mobilePhone;
    private final String workPhone;
    private final String secondaryPhone;
    private final String firstEmail;
    private final String secondEmail;
    private final String thirdEmail;
    private final String address;
    private final String photo;


    public ContactData(String id, String firstName, String middleName, String lastName,
                       String homePhone, String mobilePhone, String workPhone, String secondaryPhone,
                       String firstEmail, String secondEmail, String thirdEmail, String address, String photo) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;

        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.secondaryPhone = secondaryPhone;

        this.firstEmail = firstEmail;
        this.secondEmail = secondEmail;
        this.thirdEmail = thirdEmail;
        this.address = address;
        this.photo = photo;
    }

    public ContactData() {
        this("", "", "", "",
               "", "", "",
               "", "", "", "", "", "");
    }

    public ContactData contactWithNames(String id, String firstName, String lastName) {
        return new ContactData(
               id, firstName, this.middleName, lastName,

               this.homePhone,
               this.mobilePhone,
               this.workPhone,
               this.secondaryPhone,

               this.firstEmail,
               this.secondEmail,
               this.thirdEmail,
               this.address,
               this.photo);
    }

    public ContactData contactWithPhones(String homePhone, String mobilePhone, String workPhone, String secondaryPhone) {
        return new ContactData(
               this.id, this.firstName,
               this.middleName,
               this.lastName,

               homePhone, mobilePhone, workPhone, secondaryPhone,

               this.firstEmail,
               this.secondEmail,
               this.thirdEmail,
               this.address,
               this.photo);
    }

    public ContactData contactWithEmails(String firstEmail, String secondEmail, String thirdEmail) {
        return new ContactData(
               this.id,
               this.firstName,
               this.middleName,
               this.lastName,

               this.homePhone,
               this.mobilePhone,
               this.workPhone,
               this.secondaryPhone,

               firstEmail, secondEmail, thirdEmail,
               this.address,
               this.photo);
    }

    public ContactData contactWithPhoto(String photo) {
        return new ContactData(
               this.id,
               this.firstName,
               this.middleName,
               this.lastName,

               this.homePhone,
               this.mobilePhone,
               this.workPhone,
               this.secondaryPhone,

               this.firstEmail,
               this.secondEmail,
               this.thirdEmail,
               this.address,
               photo);
    }

    public ContactData contactWithAddress(String address) {
        return new ContactData(
               this.id,
               this.firstName,
               this.middleName,
               this.lastName,

               this.homePhone,
               this.mobilePhone,
               this.workPhone,
               this.secondaryPhone,

               this.firstEmail,
               this.secondEmail,
               this.thirdEmail,
               address,
               photo);
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getSecondaryPhone() {
        return secondaryPhone;
    }

    public String getFirstEmail() {
        return firstEmail;
    }

    public String getSecondEmail() {
        return secondEmail;
    }

    public String getThirdEmail() {
        return thirdEmail;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoto() {
        return photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(firstName, that.firstName)) return false;
        if (!Objects.equals(middleName, that.middleName)) return false;
        if (!Objects.equals(lastName, that.lastName)) return false;
        if (!Objects.equals(homePhone, that.homePhone)) return false;
        if (!Objects.equals(mobilePhone, that.mobilePhone)) return false;
        if (!Objects.equals(workPhone, that.workPhone)) return false;
        if (!Objects.equals(secondaryPhone, that.secondaryPhone))
            return false;
        if (!Objects.equals(firstEmail, that.firstEmail)) return false;
        if (!Objects.equals(secondEmail, that.secondEmail)) return false;
        if (!Objects.equals(thirdEmail, that.thirdEmail)) return false;
        return Objects.equals(photo, that.photo);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
        result = 31 * result + (secondaryPhone != null ? secondaryPhone.hashCode() : 0);
        result = 31 * result + (firstEmail != null ? firstEmail.hashCode() : 0);
        result = 31 * result + (secondEmail != null ? secondEmail.hashCode() : 0);
        result = 31 * result + (thirdEmail != null ? thirdEmail.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        return result;
    }
}
