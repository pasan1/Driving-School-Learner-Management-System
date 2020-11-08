package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerProgramDAO;
import entity.CustomerProgram;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CustomerProgramDAOImpl implements CustomerProgramDAO {
    @Override
    public boolean save(CustomerProgram customerProgram) throws Exception {
        String sql = "INSERT INTO CustomerProgram VALUES (?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql,
                customerProgram.getCustomerId(),
                customerProgram.getAmount(),
                customerProgram.getBalance(),
                customerProgram.getDates(),
                customerProgram.getRemainingDates()
        );
    }

    @Override
    public boolean update(CustomerProgram customerProgram) throws Exception {
        String sql = "UPDATE CustomerProgram SET Amount=?, Balance=?, Dates=?, RemainingDates=? WHERE CustomerID=?";
        return CrudUtil.executeUpdate(sql,
                customerProgram.getAmount(),
                customerProgram.getBalance(),
                customerProgram.getDates(),
                customerProgram.getRemainingDates(),
                customerProgram.getCustomerId()
        );
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql = "DELETE FROM CustomerProgram WHERE CustomerID=?";
        return CrudUtil.executeUpdate(sql,s);
    }

    @Override
    public CustomerProgram search(String s) throws Exception {
        String sql = "SELECT * FROM CustomerProgram WHERE CustomerID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        if (rst.next()){
            return new CustomerProgram(
                    rst.getString(1),
                    rst.getDouble(2),
                    rst.getDouble(3),
                    rst.getInt(4),
                    rst.getInt(5)
            );
        }
        return null;
    }

    @Override
    public ArrayList<CustomerProgram> getAll() throws Exception {
        String sql = "SELECT * FROM CustomerProgram";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<CustomerProgram> list = new ArrayList<>();
        while (rst.next()){
            list.add(new CustomerProgram(
                    rst.getString(1),
                    rst.getDouble(2),
                    rst.getDouble(3),
                    rst.getInt(4),
                    rst.getInt(5)
            ));
        }
        return list;
    }

    @Override
    public double getBalance(String id) throws Exception {
        String sql = "SELECT Balance FROM CustomerProgram WHERE CustomerID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, id);
        if (rst.next()){
                    return Double.parseDouble(rst.getString(1));
        }
        return 0;
    }

    @Override
    public boolean updateBalance(String id, String balanceAmount) throws Exception {
        String sql = "UPDATE CustomerProgram SET Balance=Balance+? WHERE CustomerID=?";
        return CrudUtil.executeUpdate(sql, balanceAmount, id);
    }

    @Override
    public int getRemainingDates(String id) throws Exception {
        String sql = "SELECT RemainingDates FROM CustomerProgram WHERE CustomerID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, id);
        if (rst.next()){
            return Integer.parseInt(rst.getString(1));
        }
        return 0;
    }

    @Override
    public boolean addRemainingDates(String id, int dates) throws Exception {
        String sql = "UPDATE CustomerProgram SET RemainingDates=RemainingDates+? WHERE CustomerID=?";
        return CrudUtil.executeUpdate(sql, dates, id);
    }

    @Override
    public boolean minusRemainingDates(String id, int dates) throws Exception {
        String sql = "UPDATE CustomerProgram SET RemainingDates=RemainingDates-? WHERE CustomerID=?";
        return CrudUtil.executeUpdate(sql, dates, id);
    }
}
