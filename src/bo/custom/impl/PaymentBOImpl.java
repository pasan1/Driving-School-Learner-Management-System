package bo.custom.impl;

import bo.custom.PaymentBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dao.custom.CustomerProgramDAO;
import dao.custom.PaymentDAO;
import dao.custom.QueryDAO;
import db.DBConnection;
import dto.CustomerDTO;
import dto.PaymentDTO;
import entity.Custom;
import entity.Payment;

import java.sql.Connection;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    CustomerProgramDAO customerProgramDAO = (CustomerProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMERPROGRAM);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public String getPaymentLastId() throws Exception {
        return paymentDAO.getPaymentLastId();
    }

    @Override
    public boolean save(PaymentDTO dto) throws Exception {
        boolean temp = false;
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            if (paymentDAO.save(new Payment(dto.getPaymentId(), dto.getCustomerId(), dto.getDate(), dto.getTime(), dto.getAmount()))){
                if (customerProgramDAO.updateBalance(dto.getCustomerId(), String.valueOf(dto.getProgramBalanceAmount()))){
                    connection.commit();
                    temp = true;
                } else {
                    connection.rollback();
                }
            } else {
                connection.rollback();
            }
        } finally {
            connection.setAutoCommit(true);
        }
        return temp;
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws Exception {
        ArrayList<Custom> all = queryDAO.getAllCustomersDetails();
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        for (Custom c : all) {
            CustomerDTO dto = new CustomerDTO(
                    c.getCustomerId(),
                    c.getFirstName(),
                    c.getLastName(),
                    c.getNic(),
                    c.getGender(),
                    c.getDob(),
                    c.getAddress(),
                    c.getMobileNumber(),
                    c.getDrivingLicense(),
                    c.getAmount(),
                    c.getBalance(),
                    c.getDates(),
                    c.getRemainingDates()
            );
            allCustomers.add(dto);
        }
        return allCustomers;
    }

    @Override
    public CustomerDTO searchCustomerByNIC(String nic) throws Exception {
        Custom c = queryDAO.searchCustomerDetailsByNIC(nic);
        return new CustomerDTO(
                c.getCustomerId(),
                c.getFirstName(),
                c.getLastName(),
                c.getNic(),
                c.getGender(),
                c.getDob(),
                c.getAddress(),
                c.getMobileNumber(),
                c.getDrivingLicense(),
                c.getAmount(),
                c.getBalance(),
                c.getDates(),
                c.getRemainingDates()
        );
    }
}
