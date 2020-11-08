package entity;

public class CustomerProgram implements SuperEntity {
    private String customerId;
    private double amount;
    private double balance;
    private int dates;
    private int remainingDates;

    public CustomerProgram() {
    }

    public CustomerProgram(String customerId, double amount, double balance, int dates, int remainingDates) {
        this.setCustomerId(customerId);
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
        return "CustomerProgram{" +
                "customerId='" + customerId + '\'' +
                ", amount=" + amount +
                ", balance=" + balance +
                ", dates=" + dates +
                ", remainingDates=" + remainingDates +
                '}';
    }
}
