package tm;

public class CustomerTM {
    private String customerId;
    private String firstName;
    private String lastName;
    private String nic;
    private String address;
    private String mobileNumber;
    private String drivingLicense;
    private String trainingFee;
    private String noOfDates;

    public CustomerTM() {
    }

    public CustomerTM(String customerId, String firstName, String lastName, String nic, String address, String mobileNumber, String drivingLicense, String trainingFee, String noOfDates) {
        this.setCustomerId(customerId);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setNic(nic);
        this.setAddress(address);
        this.setMobileNumber(mobileNumber);
        this.setDrivingLicense(drivingLicense);
        this.setTrainingFee(trainingFee);
        this.setNoOfDates(noOfDates);
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

    public String getTrainingFee() {
        return trainingFee;
    }

    public void setTrainingFee(String trainingFee) {
        this.trainingFee = trainingFee;
    }

    public String getNoOfDates() {
        return noOfDates;
    }

    public void setNoOfDates(String noOfDates) {
        this.noOfDates = noOfDates;
    }

    @Override
    public String toString() {
        return "CustomerTM{" +
                "customerId='" + customerId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nic='" + nic + '\'' +
                ", address='" + address + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", drivingLicense='" + drivingLicense + '\'' +
                ", trainingFee='" + trainingFee + '\'' +
                ", noOfDates='" + noOfDates + '\'' +
                '}';
    }
}
