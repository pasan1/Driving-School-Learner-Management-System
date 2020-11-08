package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;

import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    public String getCustomerLastId() throws Exception;

    public ArrayList<CustomerDTO> getAll() throws Exception;

    public boolean save(CustomerDTO dto) throws Exception;

    public boolean update(CustomerDTO dto) throws Exception;

    public boolean delete(String id) throws Exception;

    public CustomerDTO search(String id) throws Exception;

    public CustomerDTO searchByNIC(String nic) throws Exception;

    public double getBalance(String id) throws Exception;

    public int getRemainingDates(String id) throws Exception;

    public ArrayList<CustomerDTO> getSearchCustomerDetails(String id) throws Exception;
}
