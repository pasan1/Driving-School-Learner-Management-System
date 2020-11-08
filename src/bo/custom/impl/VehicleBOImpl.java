package bo.custom.impl;

import bo.custom.VehicleBO;
import dao.DAOFactory;
import dao.custom.VehicleDAO;
import dto.VehicleDTO;
import entity.Vehicle;

import java.util.ArrayList;

public class VehicleBOImpl implements VehicleBO {

    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.VEHICLE);

    @Override
    public String getVehicleLastId() throws Exception {
        return vehicleDAO.getVehicleLastId();
    }

    @Override
    public ArrayList<VehicleDTO> getAll() throws Exception {
        ArrayList<Vehicle> all = vehicleDAO.getAll();
        ArrayList<VehicleDTO> allVehicles = new ArrayList<>();
        for (Vehicle v : all) {
            VehicleDTO dto = new VehicleDTO(
                    v.getVehicleId(),
                    v.getVehicleNumber(),
                    v.getType(),
                    v.getTransmission(),
                    v.getModel(),
                    v.getYear()
            );
            allVehicles.add(dto);
        }
        return allVehicles;
    }

    @Override
    public boolean save(VehicleDTO dto) throws Exception {
        return vehicleDAO.save(new Vehicle(
                dto.getVehicleId(),
                dto.getVehicleNumber(),
                dto.getType(),
                dto.getTransmission(),
                dto.getModel(),
                dto.getYear()
        ));
    }

    @Override
    public boolean update(VehicleDTO dto) throws Exception {
        return vehicleDAO.update(new Vehicle(
                dto.getVehicleId(),
                dto.getVehicleNumber(),
                dto.getType(),
                dto.getTransmission(),
                dto.getModel(),
                dto.getYear()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return vehicleDAO.delete(id);
    }

    @Override
    public VehicleDTO search(String id) throws Exception {
        Vehicle v = vehicleDAO.search(id);
        return new VehicleDTO(
                v.getVehicleId(),
                v.getVehicleNumber(),
                v.getType(),
                v.getTransmission(),
                v.getModel(),
                v.getYear()
        );
    }

    @Override
    public ArrayList<VehicleDTO> getSearchVehicleDetails(String id) throws Exception {
        ArrayList<Vehicle> all = vehicleDAO.getSearchVehicleDetails(id);
        ArrayList<VehicleDTO> allVehicles = new ArrayList<>();
        for (Vehicle v : all) {
            VehicleDTO dto = new VehicleDTO(
                    v.getVehicleId(),
                    v.getVehicleNumber(),
                    v.getType(),
                    v.getTransmission(),
                    v.getModel(),
                    v.getYear()
            );
            allVehicles.add(dto);
        }
        return allVehicles;
    }

    @Override
    public VehicleDTO searchByNumber(String no) throws Exception {
        Vehicle v = vehicleDAO.search(no);
        return new VehicleDTO(
                v.getVehicleId(),
                v.getVehicleNumber(),
                v.getType(),
                v.getTransmission(),
                v.getModel(),
                v.getYear()
        );
    }
}
