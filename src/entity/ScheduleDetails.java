package entity;

public class ScheduleDetails implements SuperEntity {
    private String scheduleId;
    private String customerId;
    private String customerNic;
    private String customerName;
    private String instructorId;
    private String instructorNic;
    private String instructorName;
    private String vehicleId;
    private String vehicleNumber;
    private String vehicleType;
    private String vehicleModel;
    private String date;
    private String time;

    public ScheduleDetails() {
    }

    public ScheduleDetails(String scheduleId, String customerId, String customerNic, String customerName, String instructorId, String instructorNic, String instructorName, String vehicleId, String vehicleNumber, String vehicleType, String vehicleModel, String date, String time) {
        this.setScheduleId(scheduleId);
        this.setCustomerId(customerId);
        this.setCustomerNic(customerNic);
        this.setCustomerName(customerName);
        this.setInstructorId(instructorId);
        this.setInstructorNic(instructorNic);
        this.setInstructorName(instructorName);
        this.setVehicleId(vehicleId);
        this.setVehicleNumber(vehicleNumber);
        this.setVehicleType(vehicleType);
        this.setVehicleModel(vehicleModel);
        this.setDate(date);
        this.setTime(time);
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerNic() {
        return customerNic;
    }

    public void setCustomerNic(String customerNic) {
        this.customerNic = customerNic;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public String getInstructorNic() {
        return instructorNic;
    }

    public void setInstructorNic(String instructorNic) {
        this.instructorNic = instructorNic;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ScheduleDetails{" +
                "scheduleId='" + scheduleId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerNic='" + customerNic + '\'' +
                ", customerName='" + customerName + '\'' +
                ", instructorId='" + instructorId + '\'' +
                ", instructorNic='" + instructorNic + '\'' +
                ", instructorName='" + instructorName + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
