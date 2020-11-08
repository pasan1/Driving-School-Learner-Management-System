package entity;

import java.sql.Date;

public class Customer implements SuperEntity {
    private String customerId;
    private String firstName;
    private String lastName;
    private String nic;
    private String gender;
    private Date dob;
    private String address;
    private String mobileNumber;
    private String drivingLicense;

    public Customer() {
    }

    public Customer(String customerId, String firstName, String lastName, String nic, String gender, Date dob, String address, String mobileNumber, String drivingLicense) {
        this.setCustomerId(customerId);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setNic(nic);
        this.setGender(gender);
        this.setDob(dob);
        this.setAddress(address);
        this.setMobileNumber(mobileNumber);
        this.setDrivingLicense(drivingLicense);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
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

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nic='" + nic + '\'' +
                ", gender=" + gender +
                ", dob=" + dob +
                ", address='" + address + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", drivingLicense='" + drivingLicense + '\'' +
                '}';
    }
}
