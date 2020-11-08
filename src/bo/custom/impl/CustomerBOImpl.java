package bo.custom.impl;

import bo.custom.CustomerBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dao.custom.CustomerProgramDAO;
import dao.custom.QueryDAO;
import db.DBConnection;
import dto.CustomerDTO;
import entity.Custom;
import entity.Customer;
import entity.CustomerProgram;

import java.sql.Connection;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    boolean temp;

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    CustomerProgramDAO customerProgramDAO = (CustomerProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMERPROGRAM);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public String getCustomerLastId() throws Exception {
        return customerDAO.getCustomerLastId();
    }

    @Override
    public ArrayList<CustomerDTO> getAll() throws Exception {
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
    public boolean save(CustomerDTO dto) throws Exception {
        temp = false;
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            if (customerDAO.save(new Customer(dto.getCustomerId(), dto.getFirstName(), dto.getLastName(), dto.getNic(), dto.getGender(), dto.getDob(), dto.getAddress(), dto.getMobileNumber(), dto.getDrivingLicense()))) {
                if (customerProgramDAO.save(new CustomerProgram(dto.getCustomerId(), dto.getAmount(), dto.getBalance(), dto.getDates(), dto.getRemainingDates()))) {
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
    public boolean update(CustomerDTO dto) throws Exception {
        temp = false;
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            if (customerDAO.update(new Customer(dto.getCustomerId(), dto.getFirstName(), dto.getLastName(), dto.getNic(), dto.getGender(), dto.getDob(), dto.getAddress(), dto.getMobileNumber(), dto.getDrivingLicense()))) {
                if (customerProgramDAO.update(new CustomerProgram(dto.getCustomerId(), dto.getAmount(), dto.getBalance(), dto.getDates(), dto.getRemainingDates()))) {
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
    public boolean delete(String id) throws Exception {
        boolean temp = false;
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            if (customerProgramDAO.delete(id)) {
                if (customerDAO.delete(id)) {
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
    public CustomerDTO search(String id) throws Exception {
        Custom c = queryDAO.searchCustomerDetails(id);
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

    @Override
    public CustomerDTO searchByNIC(String nic) throws Exception {
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

    @Override
    public double getBalance(String id) throws Exception {
        return customerProgramDAO.getBalance(id);
    }

    @Override
    public int getRemainingDates(String id) throws Exception {
        return customerProgramDAO.getRemainingDates(id);
    }

    @Override
    public ArrayList<CustomerDTO> getSearchCustomerDetails(String id) throws Exception {
        ArrayList<Custom> all = queryDAO.getSearchCustomersDetails(id);
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
}
