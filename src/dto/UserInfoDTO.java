package dto;

import java.sql.Date;

public class UserInfoDTO {
    private String userId;
    private String firstName;
    private String lastName;
    private String nic;
    private String gender;
    private Date dob;
    private String address;
    private String mobileNumber;
    private String drivingLicense;
    private String designation;
    private String userName;
    private String password;
    private String q1;
    private String q2;
    private String q3;
    private String a1;
    private String a2;
    private String a3;

    public UserInfoDTO() {
    }

    public UserInfoDTO(String userId, String firstName, String lastName, String nic, String gender, Date dob, String address, String mobileNumber, String drivingLicense, String designation, String userName, String password, String q1, String q2, String q3, String a1, String a2, String a3) {
        this.setUserId(userId);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setNic(nic);
        this.setGender(gender);
        this.setDob(dob);
        this.setAddress(address);
        this.setMobileNumber(mobileNumber);
        this.setDrivingLicense(drivingLicense);
        this.setDesignation(designation);
        this.setUserName(userName);
        this.setPassword(password);
        this.setQ1(q1);
        this.setQ2(q2);
        this.setQ3(q3);
        this.setA1(a1);
        this.setA2(a2);
        this.setA3(a3);
    }

    public UserInfoDTO(String userId, String firstName, String lastName, String nic, String gender, Date dob, String address, String mobileNumber, String drivingLicense, String designation) {
        this.setUserId(userId);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setNic(nic);
        this.setGender(gender);
        this.setDob(dob);
        this.setAddress(address);
        this.setMobileNumber(mobileNumber);
        this.setDrivingLicense(drivingLicense);
        this.setDesignation(designation);
    }

    public UserInfoDTO(String userId, String q1, String q2, String q3, String a1, String a2, String a3) {
        this.setUserId(userId);
        this.setQ1(q1);
        this.setQ2(q2);
        this.setQ3(q3);
        this.setA1(a1);
        this.setA2(a2);
        this.setA3(a3);
    }

    public UserInfoDTO(String userId, String firstName, String lastName, String nic, String gender, Date dob, String address, String mobileNumber, String drivingLicense, String designation, String userName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nic = nic;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.drivingLicense = drivingLicense;
        this.designation = designation;
        this.userName = userName;
    }

    public UserInfoDTO(String userId, String password) {
        this.setUserId(userId);
        this.setPassword(password);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQ1() {
        return q1;
    }

    public void setQ1(String q1) {
        this.q1 = q1;
    }

    public String getQ2() {
        return q2;
    }

    public void setQ2(String q2) {
        this.q2 = q2;
    }

    public String getQ3() {
        return q3;
    }

    public void setQ3(String q3) {
        this.q3 = q3;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getA3() {
        return a3;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }

    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nic='" + nic + '\'' +
                ", gender='" + gender + '\'' +
                ", dob=" + dob +
                ", address='" + address + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", drivingLicense='" + drivingLicense + '\'' +
                ", designation='" + designation + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", q1='" + q1 + '\'' +
                ", q2='" + q2 + '\'' +
                ", q3='" + q3 + '\'' +
                ", a1='" + a1 + '\'' +
                ", a2='" + a2 + '\'' +
                ", a3='" + a3 + '\'' +
                '}';
    }
}
