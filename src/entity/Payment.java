package entity;

import java.sql.Date;

public class Payment implements SuperEntity {
    private String paymentId;
    private String customerId;
    private Date date;
    private String time;
    private double amount;

    public Payment() {
    }

    public Payment(String paymentId, String customerId, Date date, String time, double amount) {
        this.setPaymentId(paymentId);
        this.setCustomerId(customerId);
        this.setDate(date);
        this.setTime(time);
        this.setAmount(amount);
    }

    public Payment(Date date, double amount) {
        this.date = date;
        this.amount = amount;
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

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", date=" + date +
                ", time='" + time + '\'' +
                ", amount=" + amount +
                '}';
    }
}
