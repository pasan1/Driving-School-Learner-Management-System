package dao.custom;

import dao.CrudDAO;
import entity.Payment;

import java.sql.Date;
import java.util.ArrayList;

public interface PaymentDAO extends CrudDAO<Payment,String> {
    public String getPaymentLastId() throws Exception;

    ArrayList<Payment> chart() throws Exception;

    public int getTodayTotalSale(Date date) throws Exception;
}
