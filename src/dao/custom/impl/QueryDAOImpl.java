package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.QueryDAO;
import entity.Custom;
import entity.Instructor;
import entity.ScheduleDetails;
import entity.Vehicle;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<Custom> getAllCustomersDetails() throws Exception {
        String sql = "SELECT * FROM Customer c, CustomerProgram cp WHERE c.CustomerId=cp.CustomerId";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Custom> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new Custom(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDate(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getDouble(11),
                    rst.getDouble(12),
                    rst.getInt(13),
                    rst.getInt(14)
            ));
        }
        return list;
    }

    @Override
    public Custom searchCustomerDetails(String id) throws Exception {
        String sql = "SELECT * FROM Customer c, CustomerProgram cp WHERE c.CustomerId=cp.CustomerId AND c.CustomerId=?";
        ResultSet rst = CrudUtil.executeQuery(sql, id);
        if (rst.next()) {
            return new Custom(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDate(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getDouble(11),
                    rst.getDouble(12),
                    rst.getInt(13),
                    rst.getInt(14)
            );
        }
        return null;
    }

    @Override
    public Custom searchCustomerDetailsByNIC(String nic) throws Exception {
        String sql = "SELECT * FROM Customer c, CustomerProgram cp WHERE c.CustomerId=cp.CustomerId AND c.NIC=?";
        ResultSet rst = CrudUtil.executeQuery(sql, nic);
        if (rst.next()) {
            return new Custom(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDate(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getDouble(11),
                    rst.getDouble(12),
                    rst.getInt(13),
                    rst.getInt(14)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Custom> getSearchCustomersDetails(String id) throws Exception {
        String s = "" + "%" + id + "%";
        String sql = "SELECT * FROM Customer c, CustomerProgram cp WHERE c.CustomerId=cp.CustomerId AND c.NIC LIKE ?";
        ArrayList<Custom> list = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        while (rst.next()) {
            list.add(new Custom(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDate(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getDouble(11),
                    rst.getDouble(12),
                    rst.getInt(13),
                    rst.getInt(14)
            ));
        }
        return list;
    }

    @Override
    public ArrayList<Instructor> getAllAvailableInstructorsFromSchedule(Date date, String time) throws Exception {
        String sql = "SELECT * FROM Instructor WHERE NOT InstructorID IN (SELECT s.InstructorID FROM Instructor i INNER JOIN Schedule s ON i.InstructorID = s.InstructorID WHERE (Date=? AND Time=?) GROUP BY s.InstructorID)";
        ArrayList<Instructor> list = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery(sql, date, time);
        while (rst.next()) {
            list.add(new Instructor(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDate(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10)
            ));
        }
        return list;
    }

    @Override
    public ArrayList<Vehicle> getAllAvailableVehiclesFromSchedule(Date date, String time) throws Exception {
        String sql = "SELECT * FROM Vehicle WHERE NOT VehicleID IN (SELECT s.VehicleID FROM Vehicle v INNER JOIN Schedule s ON v.VehicleID = s.VehicleID WHERE (Date=? AND Time=?) GROUP BY s.VehicleID)";
        ArrayList<Vehicle> list = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery(sql, date, time);
        while (rst.next()) {
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
    public ScheduleDetails getScheduleDetailsFromId(String id) throws Exception {
        String sql = "SELECT s.ScheduleID, c.CustomerID, c.NIC, CONCAT(c.FirstName, \" \", c.LastName) AS CustomerName, i.InstructorID, i.NIC, CONCAT(i.FirstName, \" \", i.LastName) AS InstructorName, v.VehicleID, v.VehicleNumber, v.Type, v.Model, s.Date, s.Time FROM Schedule s, Customer c, Instructor i, Vehicle v WHERE c.CustomerID=s.CustomerID AND i.InstructorID=s.InstructorID AND v.VehicleID=s.VehicleID AND s.ScheduleID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, id);
        if (rst.next()) {
            return new ScheduleDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getString(11),
                    rst.getString(12),
                    rst.getString(13)
            );
        }
        return null;
    }

    @Override
    public ScheduleDetails getScheduleDetailsFromCusNIC(String nic) throws Exception {
        String sql = "SELECT s.ScheduleID, c.CustomerID, c.NIC, CONCAT(c.FirstName, \" \", c.LastName) AS CustomerName, i.InstructorID, i.NIC, CONCAT(i.FirstName, \" \", i.LastName) AS InstructorName, v.VehicleID, v.VehicleNumber, v.Type, v.Model, s.Date, s.Time FROM Schedule s, Customer c, Instructor i, Vehicle v WHERE c.CustomerID=s.CustomerID AND i.InstructorID=s.InstructorID AND v.VehicleID=s.VehicleID AND c.NIC=? ORDER BY ScheduleID DESC LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql, nic);
        if (rst.next()) {
            return new ScheduleDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getString(11),
                    rst.getString(12),
                    rst.getString(13)
            );
        }
        return null;
    }

    @Override
    public ArrayList<ScheduleDetails> getScheduleDetails() throws Exception {
        String sql = "SELECT s.ScheduleID, c.CustomerID, c.NIC, CONCAT(c.FirstName, \" \", c.LastName) AS CustomerName, i.InstructorID, i.NIC, CONCAT(i.FirstName, \" \", i.LastName) AS InstructorName, v.VehicleID, v.VehicleNumber, v.Type, v.Model, s.Date, s.Time FROM Schedule s, Customer c, Instructor i, Vehicle v WHERE c.CustomerID=s.CustomerID AND i.InstructorID=s.InstructorID AND v.VehicleID=s.VehicleID";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<ScheduleDetails> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new ScheduleDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getString(11),
                    rst.getString(12),
                    rst.getString(13)
            ));
        }
        return list;
    }

    @Override
    public ArrayList<ScheduleDetails> getSearchScheduleDetails(String id) throws Exception {
        String sql = "SELECT s.ScheduleID, c.CustomerID, c.NIC, CONCAT(c.FirstName, \" \", c.LastName) AS CustomerName, i.InstructorID, i.NIC, CONCAT(i.FirstName, \" \", i.LastName) AS InstructorName, v.VehicleID, v.VehicleNumber, v.Type, v.Model, s.Date, s.Time FROM Schedule s, Customer c, Instructor i, Vehicle v WHERE c.CustomerID=s.CustomerID AND i.InstructorID=s.InstructorID AND v.VehicleID=s.VehicleID AND c.NIC LIKE \'%"+id+"%\'";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<ScheduleDetails> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new ScheduleDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getString(11),
                    rst.getString(12),
                    rst.getString(13)
            ));
        }
        return list;
    }
}
