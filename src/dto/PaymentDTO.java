package dto;

import java.sql.Date;

public class PaymentDTO {
    private String paymentId;
    private String customerId;
    private Date date;
    private String time;
    private double amount;
    private String firstName;
    private String lastName;
    private String nic;
    private String address;
    private String mobileNumber;
    private double programFullAmount;
    private double programBalanceAmount;

    public PaymentDTO() {
    }

    public PaymentDTO(String paymentId, String customerId, Date date, String time, double amount, String firstName, String lastName, String nic, String address, String mobileNumber, double programFullAmount, double programBalanceAmount) {
        this.setPaymentId(paymentId);
        this.setCustomerId(customerId);
        this.setDate(date);
        this.setTime(time);
        this.setAmount(amount);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setNic(nic);
        this.setAddress(address);
        this.setMobileNumber(mobileNumber);
        this.setProgramFullAmount(programFullAmount);
        this.setProgramBalanceAmount(programBalanceAmount);
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public double getProgramFullAmount() {
        return programFullAmount;
    }

    public void setProgramFullAmount(double programFullAmount) {
        this.programFullAmount = programFullAmount;
    }

    public double getProgramBalanceAmount() {
        return programBalanceAmount;
    }

    public void setProgramBalanceAmount(double programBalanceAmount) {
        this.programBalanceAmount = programBalanceAmount;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "paymentId='" + paymentId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", date=" + date +
                ", time='" + time + '\'' +
                ", amount=" + amount +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nic='" + nic + '\'' +
                ", address='" + address + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", programFullAmount=" + programFullAmount +
                ", programBalanceAmount=" + programBalanceAmount +
                '}';
    }
}
