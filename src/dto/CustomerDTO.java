package dto;

import java.sql.Date;

public class CustomerDTO {
    private String customerId;
    private String firstName;
    private String lastName;
    private String nic;
    private String gender;
    private Date dob;
    private String address;
    private String mobileNumber;
    private String drivingLicense;
    private double amount;
    private double balance;
    private int dates;
    private int remainingDates;

    public CustomerDTO() {
    }

    public CustomerDTO(String customerId, String firstName, String lastName, String nic, String gender, Date dob, String address, String mobileNumber, String drivingLicense, double amount, double balance, int dates, int remainingDates) {
        this.setCustomerId(customerId);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setNic(nic);
        this.setGender(gender);
        this.setDob(dob);
        this.setAddress(address);
        this.setMobileNumber(mobileNumber);
        this.setDrivingLicense(drivingLicense);
        this.setAmount(amount);
        this.setBalance(balance);
        this.setDates(dates);
        this.setRemainingDates(remainingDates);
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getDates() {
        return dates;
    }

    public void setDates(int dates) {
        this.dates = dates;
    }

    public int getRemainingDates() {
        return remainingDates;
    }

    public void setRemainingDates(int remainingDates) {
        this.remainingDates = remainingDates;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customerId='" + customerId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nic='" + nic + '\'' +
                ", gender='" + gender + '\'' +
                ", dob=" + dob +
                ", address='" + address + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", drivingLicense='" + drivingLicense + '\'' +
                ", amount=" + amount +
                ", balance=" + balance +
                ", dates=" + dates +
                ", remainingDates=" + remainingDates +
                '}';
    }
}
