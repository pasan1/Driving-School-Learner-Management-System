package entity;

import java.sql.Date;

public class Schedule implements SuperEntity {
    private String scheduleId;
    private String customerId;
    private String instructorId;
    private String vehicleId;
    private Date date;
    private String time;

    public Schedule() {
    }

    public Schedule(String scheduleId, String customerId, String instructorId, String vehicleId, Date date, String time) {
        this.setScheduleId(scheduleId);
        this.setCustomerId(customerId);
        this.setInstructorId(instructorId);
        this.setVehicleId(vehicleId);
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

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
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

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId='" + scheduleId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", instructorId='" + instructorId + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", date=" + date +
                ", time='" + time + '\'' +
                '}';
    }
}
