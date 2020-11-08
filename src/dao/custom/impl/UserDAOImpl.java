package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.UserDAO;
import entity.User;

import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User user) throws Exception {
        String sql = "INSERT INTO User VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql,
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getNic(),
                user.getGender(),
                user.getDob(),
                user.getAddress(),
                user.getMobileNumber(),
                user.getDrivingLicense(),
                user.getDesignation(),
                user.getUserName(),
                user.getPassword(),
                user.getQ1(),
                user.getQ2(),
                user.getQ3(),
                user.getA1(),
                user.getA2(),
                user.getA3()
        );
    }

    @Override
    public boolean update(User user) throws Exception {
        String sql = "UPDATE User SET FirstName=?, LastName=?, NIC=?, Gender=?, DOB=?, Address=?, MobileNumber=?, DrivingLicense=?, Designation=?, UserName=?, Password=?, Q1=?, Q2=?, Q3=?, A1=?, A2=?, A3=? WHERE UserID=?";
        return CrudUtil.executeUpdate(sql,
                user.getFirstName(),
                user.getLastName(),
                user.getNic(),
                user.getGender(),
                user.getDob(),
                user.getAddress(),
                user.getMobileNumber(),
                user.getDrivingLicense(),
                user.getDesignation(),
                user.getUserName(),
                user.getPassword(),
                user.getQ1(),
                user.getQ2(),
                user.getQ3(),
                user.getA1(),
                user.getA2(),
                user.getA3(),
                user.getUserId()
        );
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql = "DELETE FROM User WHERE UserID=?";
        return CrudUtil.executeUpdate(sql, s);
    }

    @Override
    public User search(String s) throws Exception {
        String sql = "SELECT * FROM User WHERE UserID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        if (rst.next()) {
            return new User(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDate(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getString(11),
                    rst.getString(12),
                    rst.getInt(13),
                    rst.getInt(14),
                    rst.getInt(15),
                    rst.getString(16),
                    rst.getString(17),
                    rst.getString(18)
            );
        }
        return null;
    }

    @Override
    public ArrayList<User> getAll() throws Exception {
        String sql = "SELECT * FROM User";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<User> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new User(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDate(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getString(11),
                    rst.getString(12),
                    rst.getInt(13),
                    rst.getInt(14),
                    rst.getInt(15),
                    rst.getString(16),
                    rst.getString(17),
                    rst.getString(18)
            ));
        }
        return list;
    }

    @Override
    public User searchByNIC(String nic) throws Exception {
        String sql = "SELECT * FROM User WHERE NIC=?";
        ResultSet rst = CrudUtil.executeQuery(sql, nic);
        if (rst.next()) {
            return new User(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDate(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getString(11),
                    rst.getString(12),
                    rst.getInt(13),
                    rst.getInt(14),
                    rst.getInt(15),
                    rst.getString(16),
                    rst.getString(17),
                    rst.getString(18)
            );
        }
        return null;
    }

    @Override
    public ArrayList<User> getUsersDetails(String nic) throws Exception {
        String s = "" + "%" + nic + "%";
        String sql = "SELECT * FROM User WHERE NIC LIKE ?";
        ResultSet rst = CrudUtil.executeQuery(sql, nic);
        ArrayList<User> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new User(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDate(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getString(11),
                    rst.getString(12),
                    rst.getInt(13),
                    rst.getInt(14),
                    rst.getInt(15),
                    rst.getString(16),
                    rst.getString(17),
                    rst.getString(18)
            ));
        }
        return list;
    }

    @Override
    public String getPasswordFromUserName(String userName) throws Exception {
        String sql = "SELECT Password FROM User WHERE UserName=?";
        ResultSet rst = CrudUtil.executeQuery(sql, userName);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public String getPasswordFromUserId(String userName) throws Exception {
        String sql = "SELECT Password FROM User WHERE UserId=?";
        ResultSet rst = CrudUtil.executeQuery(sql, userName);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public String getUserLastId() throws Exception {
        String sql = "SELECT MAX(UserID) FROM User";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public String getDesignation(String userName) throws Exception {
        String sql = "SELECT Designation FROM User WHERE UserName=?";
        ResultSet rst = CrudUtil.executeQuery(sql, userName);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public String getUserId(String userName) throws Exception {
        String sql = "SELECT UserID FROM User WHERE UserName=?";
        ResultSet rst = CrudUtil.executeQuery(sql, userName);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public String getUserFull(String userId) throws Exception {
        String sql = " SELECT CONCAT(FirstName, \" \", LastName) AS Name FROM User WHERE UserID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, userId);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean updateUserBasicInfo(User user) throws Exception {
        String sql = "UPDATE User SET FirstName=?, LastName=?, NIC=?, Gender=?, DOB=?, Address=?, MobileNumber=?, DrivingLicense=?, Designation=? WHERE UserID=?";
        return CrudUtil.executeUpdate(sql,
                user.getFirstName(),
                user.getLastName(),
                user.getNic(),
                user.getGender(),
                user.getDob(),
                user.getAddress(),
                user.getMobileNumber(),
                user.getDrivingLicense(),
                user.getDesignation(),
                user.getUserId()
        );
    }

    @Override
    public boolean updateUserBasic(User user) throws Exception {
        String sql = "UPDATE User SET FirstName=?, LastName=?, NIC=?, Gender=?, DOB=?, Address=?, MobileNumber=?, DrivingLicense=?, Designation=?, UserName=? WHERE UserID=?";
        return CrudUtil.executeUpdate(sql,
                user.getFirstName(),
                user.getLastName(),
                user.getNic(),
                user.getGender(),
                user.getDob(),
                user.getAddress(),
                user.getMobileNumber(),
                user.getDrivingLicense(),
                user.getDesignation(),
                user.getUserName(),
                user.getUserId()
        );
    }

    @Override
    public boolean updateUserQA(User user) throws Exception {
        String sql = "UPDATE User SET Q1=?, Q2=?, Q3=?, A1=?, A2=?, A3=? WHERE UserID=?";
        return CrudUtil.executeUpdate(sql,
                user.getQ1(),
                user.getQ2(),
                user.getQ3(),
                user.getA1(),
                user.getA2(),
                user.getA3(),
                user.getUserId()
        );
    }

    @Override
    public boolean updateUserPassword(User user) throws Exception {
        String sql = "UPDATE User SET Password=? WHERE UserID=?";
        return CrudUtil.executeUpdate(sql,
                user.getPassword(),
                user.getUserId()
        );
    }

    @Override
    public boolean isUserNameAvailable(String userName) throws Exception {
        String sql = "SELECT UserID FROM User WHERE UserName=?";
        ResultSet rst = CrudUtil.executeQuery(sql, userName);
        return rst.next();
    }

    @Override
    public boolean isPasswordAvailable(String password) throws Exception {
        String sql = "SELECT Password FROM User WHERE Password=?";
        ResultSet rst = CrudUtil.executeQuery(sql, password);
        return rst.next();
    }

    @Override
    public boolean updatePasswordAndSQ(User user) throws Exception {
        String sql = "UPDATE User SET Password=?, Q1=?, Q2=?, Q3=?, A1=?, A2=?, A3=? WHERE UserID=?";
        return CrudUtil.executeUpdate(sql,
                user.getPassword(),
                user.getQ1(),
                user.getQ2(),
                user.getQ3(),
                user.getA1(),
                user.getA2(),
                user.getA3(),
                user.getUserId()
        );
    }
}
