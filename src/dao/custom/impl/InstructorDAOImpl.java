package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.InstructorDAO;
import entity.Instructor;

import java.sql.ResultSet;
import java.util.ArrayList;

public class InstructorDAOImpl implements InstructorDAO {
    @Override
    public Instructor searchByNIC(String nic) throws Exception {
        String sql = "SELECT * FROM Instructor WHERE NIC=?";
        ResultSet rst = CrudUtil.executeQuery(sql, nic);
        if (rst.next()){
            return new Instructor(
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
            );
        }
        return null;
    }

    @Override
    public String getInstructorLastId() throws Exception {
        String sql = "SELECT MAX(InstructorID) FROM Instructor";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public ArrayList<Instructor> getSearchInstructorDetails(String id) throws Exception {
        String s = "" + "%" + id + "%";
        String sql = "SELECT * FROM Instructor  WHERE  NIC LIKE ?";
        ArrayList<Instructor> list = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery(sql, s);
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
    public String getInstructorNICFromId(String id) throws Exception {
        String sql = "SELECT NIC FROM Instructor WHERE InstructorID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, id);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public String getInstructorNameFromId(String id) throws Exception {
        String sql = "SELECT CONCAT(FirstName, \" \", LastName) AS Name FROM Instructor WHERE InstructorID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, id);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public int getCount() throws Exception {
        String sql = "SELECT COUNT(*) FROM Instructor";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean save(Instructor instructor) throws Exception {
        String sql = "INSERT INTO Instructor VALUES (?,?,?,?,?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql,
                instructor.getInstructorId(),
                instructor.getFirstName(),
                instructor.getLastName(),
                instructor.getNic(),
                instructor.getGender(),
                instructor.getDob(),
                instructor.getAddress(),
                instructor.getMobileNumber(),
                instructor.getDrivingLicense(),
                instructor.getEpfNo()
        );
    }

    @Override
    public boolean update(Instructor instructor) throws Exception {
        String sql = "UPDATE Instructor SET FirstName=?, LastName=?, NIC=?, Gender=?, DOB=?, Address=?, MobileNumber=?, DrivingLicense=?, EPFno=? WHERE InstructorID=?";
        return CrudUtil.executeUpdate(sql,
                instructor.getFirstName(),
                instructor.getLastName(),
                instructor.getNic(),
                instructor.getGender(),
                instructor.getDob(),
                instructor.getAddress(),
                instructor.getMobileNumber(),
                instructor.getDrivingLicense(),
                instructor.getEpfNo(),
                instructor.getInstructorId()
        );
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql = "DELETE FROM Instructor WHERE InstructorID=?";
        return CrudUtil.executeUpdate(sql,s);
    }

    @Override
    public Instructor search(String s) throws Exception {
        String sql = "SELECT * FROM Instructor WHERE InstructorID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        if (rst.next()){
            return new Instructor(
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
            );
        }
        return null;
    }

    @Override
    public ArrayList<Instructor> getAll() throws Exception {
        String sql = "SELECT * FROM Instructor";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Instructor> list = new ArrayList<>();
        while (rst.next()){
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
}
