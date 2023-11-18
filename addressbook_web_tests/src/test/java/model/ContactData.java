package model;

public class ContactData {

    private final String firstName;
    private final String middleName;
    private final String lastName;

    private final String homePhone;
    private final String mobilePhone;
    private final String workPhone;

    private final String firstEmail;
    private final String secondEmail;
    private final String thirdEmail;


    public ContactData(String firstName, String middleName, String lastName,
                       String homePhone, String mobilePhone, String workPhone,
                       String firstEmail, String secondEmail, String thirdEmail) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;

        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;

        this.firstEmail = firstEmail;
        this.secondEmail = secondEmail;
        this.thirdEmail = thirdEmail;
    }

    public ContactData() {
        this("", "", "",
               "", "", "",
               "", "", "");
    }

    public ContactData contactWithNames(String firstName, String middleName, String lastName) {
        return new ContactData(
               firstName, middleName, lastName,

               this.homePhone,
               this.mobilePhone,
               this.workPhone,

               this.firstEmail,
               this.secondEmail,
               this.thirdEmail);
    }

    public ContactData contactWithPhones(String homePhone, String mobilePhone, String workPhone) {
        return new ContactData(
               this.firstName,
               this.middleName,
               this.lastName,

               homePhone, mobilePhone, workPhone,

               this.firstEmail,
               this.secondEmail,
               this.thirdEmail);
    }

    public ContactData contactWithEmails(String firstEmail, String secondEmail, String thirdEmail) {
        return new ContactData(
               this.firstName,
               this.middleName,
               this.lastName,

               this.homePhone,
               this.mobilePhone,
               this.workPhone,

               firstEmail, secondEmail, thirdEmail);
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

    public String getFirstEmail() {
        return firstEmail;
    }

    public String getSecondEmail() {
        return secondEmail;
    }

    public String getThirdEmail() {
        return thirdEmail;
    }
}
