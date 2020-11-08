package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import entity.Customer;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer customer) throws Exception {
        String sql = "INSERT INTO Customer VALUES (?,?,?,?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql,
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getNic(),
                customer.getGender(),
                customer.getDob(),
                customer.getAddress(),
                customer.getMobileNumber(),
                customer.getDrivingLicense()
        );
    }

    @Override
    public boolean update(Customer customer) throws Exception {
        String sql = "UPDATE Customer SET FirstName=?, LastName=?, NIC=?, Gender=?, DOB=?, Address=?, MobileNumber=?, DrivingLicense=? WHERE CustomerID=?";
        return CrudUtil.executeUpdate(sql,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getNic(),
                customer.getGender(),
                customer.getDob(),
                customer.getAddress(),
                customer.getMobileNumber(),
                customer.getDrivingLicense(),
                customer.getCustomerId()
        );
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql = "DELETE FROM Customer WHERE CustomerID=?";
        return CrudUtil.executeUpdate(sql,s);
    }

    @Override
    public Customer search(String s) throws Exception {
        String sql = "SELECT * FROM Customer WHERE CustomerID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        if (rst.next()){
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDate(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Customer> getAll() throws Exception {
        String sql = "SELECT * FROM Customer";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Customer> list = new ArrayList<>();
        while (rst.next()){
            list.add(new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDate(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9)
            ));
        }
        return list;
    }

    @Override
    public Customer searchByNIC(String nic) throws Exception {
        String sql = "SELECT * FROM Customer WHERE NIC=?";
        ResultSet rst = CrudUtil.executeQuery(sql, nic);
        if (rst.next()){
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDate(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9)
            );
        }
        return null;
    }

    @Override
    public String getCustomerLastId() throws Exception {
        String sql = "SELECT CustomerID FROM Customer ORDER BY CustomerID DESC LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public String getCustomerNICFromId(String id) throws Exception {
        String sql = "SELECT NIC FROM Customer WHERE CustomerID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, id);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public String getCustomerNameFromId(String id) throws Exception {
        String sql = "SELECT CONCAT(FirstName, \" \", LastName) AS Name FROM Customer WHERE CustomerID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, id);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public int getCount() throws Exception {
        String sql = "SELECT COUNT(*) FROM Customer";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }
}
