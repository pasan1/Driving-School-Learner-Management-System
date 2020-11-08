package dao.custom;

import dao.CrudDAO;
import entity.Vehicle;

import java.util.ArrayList;

public interface VehicleDAO extends CrudDAO<Vehicle, String> {
    public String getVehicleLastId() throws Exception;

    public ArrayList<Vehicle> getSearchVehicleDetails(String no) throws Exception;

    public Vehicle searchByNumber(String no) throws Exception;

    public String getVehicleNumberFormId(String id) throws Exception;

    public String getVehicleTypeFormId(String id) throws Exception;

    public int getCount() throws Exception;
}
