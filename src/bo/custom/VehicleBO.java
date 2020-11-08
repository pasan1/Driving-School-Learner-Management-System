package bo.custom;

import bo.SuperBO;
import dto.VehicleDTO;

import java.util.ArrayList;

public interface VehicleBO extends SuperBO {
    public String getVehicleLastId() throws Exception;

    public ArrayList<VehicleDTO> getAll() throws Exception;

    public boolean save(VehicleDTO dto) throws Exception;

    public boolean update(VehicleDTO dto) throws Exception;

    public boolean delete(String id) throws Exception;

    public VehicleDTO search(String id) throws Exception;

    public ArrayList<VehicleDTO> getSearchVehicleDetails(String no) throws Exception;

    public VehicleDTO searchByNumber(String no) throws Exception;
}
