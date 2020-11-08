package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ScheduleDAO;
import entity.Schedule;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ScheduleDAOImpl implements ScheduleDAO {
    @Override
    public String getScheduleId() throws Exception {
        String sql = "SELECT ScheduleID FROM Schedule ORDER BY ScheduleID DESC LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public int getCount(Date date) throws Exception {
        String sql = "SELECT COUNT(*) FROM Schedule WHERE Date=?";
        ResultSet rst = CrudUtil.executeQuery(sql, date);
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }

    @Override
    public String getCustomerID(String id) throws Exception {
        String sql = "SELECT CustomerID FROM Schedule";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean save(Schedule schedule) throws Exception {
        String sql = "INSERT INTO Schedule VALUES (?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql,
                schedule.getScheduleId(),
                schedule.getCustomerId(),
                schedule.getInstructorId(),
                schedule.getVehicleId(),
                schedule.getDate(),
                schedule.getTime()
        );
    }

    @Override
    public boolean update(Schedule schedule) throws Exception {
        String sql = "UPDATE Schedule SET CustomerID=?, InstructorID=?, VehicleID=?, Date=?, Time=? WHERE ScheduleID=?";
        return CrudUtil.executeUpdate(sql,
                schedule.getCustomerId(),
                schedule.getInstructorId(),
                schedule.getVehicleId(),
                schedule.getDate(),
                schedule.getTime(),
                schedule.getScheduleId()
        );
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql = "DELETE FROM Schedule WHERE ScheduleID=?";
        return CrudUtil.executeUpdate(sql, s);
    }

    @Override
    public Schedule search(String s) throws Exception {
        String sql = "SELECT * FROM Schedule WHERE ScheduleID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        if (rst.next()) {
            return new Schedule(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDate(5),
                    rst.getString(6)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Schedule> getAll() throws Exception {
        String sql = "SELECT * FROM Schedule";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Schedule> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new Schedule(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDate(5),
                    rst.getString(6)
            ));
        }
        return list;
    }
}
