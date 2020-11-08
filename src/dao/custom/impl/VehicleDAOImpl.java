package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.VehicleDAO;
import entity.Vehicle;

import java.sql.ResultSet;
import java.util.ArrayList;

public class VehicleDAOImpl implements VehicleDAO {
    @Override
    public boolean save(Vehicle vehicle) throws Exception {
        String sql = "INSERT INTO Vehicle VALUES (?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql,
                vehicle.getVehicleId(),
                vehicle.getVehicleNumber(),
                vehicle.getType(),
                vehicle.getTransmission(),
                vehicle.getModel(),
                vehicle.getYear()
        );
    }

    @Override
    public boolean update(Vehicle vehicle) throws Exception {
        String sql = "UPDATE Vehicle SET VehicleNumber=?, Type=?, Transmission=?, Model=?, Year=? WHERE VehicleID=?";
        return CrudUtil.executeUpdate(sql,
                vehicle.getVehicleNumber(),
                vehicle.getType(),
                vehicle.getTransmission(),
                vehicle.getModel(),
                vehicle.getYear(),
                vehicle.getVehicleId()
        );
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql = "DELETE FROM Vehicle WHERE VehicleID=?";
        return CrudUtil.executeUpdate(sql,s);
    }

    @Override
    public Vehicle search(String s) throws Exception {
        String sql = "SELECT * FROM Vehicle WHERE VehicleID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        if (rst.next()){
            return new Vehicle(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Vehicle> getAll() throws Exception {
        String sql = "SELECT * FROM Vehicle";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Vehicle> list = new ArrayList<>();
        while (rst.next()){
            list.add(new Vehicle(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            ));
        }
        return list;
    }

    @Override
    public String getVehicleLastId() throws Exception {
        String sql = "SELECT MAX(VehicleID) FROM Vehicle";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public ArrayList<Vehicle> getSearchVehicleDetails(String no) throws Exception {
        String s = "" + "%" + no + "%";
        String sql = "SELECT * FROM Vehicle WHERE VehicleNumber LIKE ?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        ArrayList<Vehicle> list = new ArrayList<>();
        while (rst.next()){
            list.add(new Vehicle(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            ));
        }
        return list;
    }

    @Override
    public Vehicle searchByNumber(String no) throws Exception {
        String sql = "SELECT * FROM Vehicle WHERE VehicleNumber=?";
        ResultSet rst = CrudUtil.executeQuery(sql, no);
        if (rst.next()){
            return new Vehicle(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            );
        }
        return null;
    }

    @Override
    public String getVehicleNumberFormId(String id) throws Exception {
        String sql = "SELECT VehicleNumber FROM Vehicle WHERE VehicleID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, id);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public String getVehicleTypeFormId(String id) throws Exception {
        String sql = "SELECT Type FROM Vehicle WHERE VehicleID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, id);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public int getCount() throws Exception {
        String sql = "SELECT COUNT(*) FROM Vehicle";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }
}
