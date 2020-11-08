package tm;

public class UserTM {
    private String userId;
    private String firstName;
    private String lastName;
    private String nic;
    private String address;
    private String mobileNumber;
    private String designation;
    private String userName;

    public UserTM() {
    }

    public UserTM(String userId, String firstName, String lastName, String nic, String address, String mobileNumber, String designation, String userName) {
        this.setUserId(userId);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setNic(nic);
        this.setAddress(address);
        this.setMobileNumber(mobileNumber);
        this.setDesignation(designation);
        this.setUserName(userName);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserTM{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nic='" + nic + '\'' +
                ", address='" + address + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", designation='" + designation + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
