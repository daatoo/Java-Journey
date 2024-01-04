package fop.w5phone;
public class Entry{
    private String lastName;
    private String firstName;
    private String phoneNumber;
    public String getLastName(){
        return lastName;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public Entry(String firstName, String lastName, String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
}

