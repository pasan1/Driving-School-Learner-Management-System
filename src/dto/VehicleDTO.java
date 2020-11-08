package dto;

public class VehicleDTO {
    private String vehicleId;
    private String vehicleNumber;
    private String type;
    private String transmission;
    private String model;
    private String year;

    public VehicleDTO() {
    }

    public VehicleDTO(String vehicleId, String vehicleNumber, String type, String transmission, String model, String year) {
        this.setVehicleId(vehicleId);
        this.setVehicleNumber(vehicleNumber);
        this.setType(type);
        this.setTransmission(transmission);
        this.setModel(model);
        this.setYear(year);
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "VehicleDTO{" +
                "vehicleId='" + vehicleId + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", type='" + type + '\'' +
                ", transmission='" + transmission + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
