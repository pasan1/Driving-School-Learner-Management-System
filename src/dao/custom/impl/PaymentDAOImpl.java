package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.PaymentDAO;
import entity.Payment;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public String getPaymentLastId() throws Exception {
        String sql = "SELECT MAX(PaymentID) FROM Payment";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public ArrayList<Payment> chart() throws Exception {
        String sql="SELECT Date,SUM(Amount) FROM Payment GROUP BY Date";
        ResultSet rst=CrudUtil.executeQuery(sql);
        ArrayList<Payment> charts=new ArrayList<>();
        while (rst.next()){

            charts.add(new Payment(rst.getDate(1),rst.getDouble(2)));
        }
        return charts;
    }

    @Override
    public int getTodayTotalSale(Date date) throws Exception {
        String sql = "SELECT SUM(Amount) FROM Payment WHERE Date=? GROUP BY Date";
        ResultSet rst = CrudUtil.executeQuery(sql, date);
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean save(Payment payment) throws Exception {
        String sql = "INSERT INTO Payment VALUES (?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql,
                payment.getPaymentId(),
                payment.getCustomerId(),
                payment.getDate(),
                payment.getTime(),
                payment.getAmount()
        );
    }

    @Override
    public boolean update(Payment payment) throws Exception {
        String sql = "UPDATE Payment SET CustomerID=?, Date=?, Time=?, Amount=? WHERE PaymentID=?";
        return CrudUtil.executeUpdate(sql,
                payment.getCustomerId(),
                payment.getDate(),
                payment.getTime(),
                payment.getAmount(),
                payment.getPaymentId()
        );
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql = "DELETE FROM Payment WHERE PaymentID=?";
        return CrudUtil.executeUpdate(sql,s);
    }

    @Override
    public Payment search(String s) throws Exception {
        String sql = "SELECT * FROM Payment WHERE PaymentID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        if (rst.next()){
            return new Payment(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDate(3),
                    rst.getString(4),
                    rst.getDouble(5)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Payment> getAll() throws Exception {
        String sql = "SELECT * FROM Payment";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Payment> list = new ArrayList<>();
        while (rst.next()){
            list.add(new Payment(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDate(3),
                    rst.getString(4),
                    rst.getDouble(5)
            ));
        }
        return list;
    }
}
