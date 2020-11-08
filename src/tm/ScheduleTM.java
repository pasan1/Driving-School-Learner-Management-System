package tm;

public class ScheduleTM {
    private String scheduleId;
    private String customerNic;
    private String customerName;
    private String instructorNic;
    private String instructorName;
    private String vehicleNumber;
    private String vehicleType;
    private String date;
    private String time;

    public ScheduleTM() {
    }

    public ScheduleTM(String scheduleId, String customerNic, String customerName, String instructorNic, String instructorName, String vehicleNumber, String vehicleType, String date, String time) {
        this.setScheduleId(scheduleId);
        this.setCustomerNic(customerNic);
        this.setCustomerName(customerName);
        this.setInstructorNic(instructorNic);
        this.setInstructorName(instructorName);
        this.setVehicleNumber(vehicleNumber);
        this.setVehicleType(vehicleType);
        this.setDate(date);
        this.setTime(time);
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
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
        return "ScheduleTM{" +
                "scheduleId='" + scheduleId + '\'' +
                ", customerNic='" + customerNic + '\'' +
                ", customerName='" + customerName + '\'' +
                ", instructorNic='" + instructorNic + '\'' +
                ", instructorName='" + instructorName + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
