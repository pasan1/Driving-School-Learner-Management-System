package dao.custom;

import dao.CrudDAO;
import entity.Customer;

public interface CustomerDAO extends CrudDAO<Customer, String> {
    public Customer searchByNIC(String nic) throws Exception;

    public String getCustomerLastId() throws Exception;

    public String getCustomerNICFromId(String id) throws Exception;

    public String getCustomerNameFromId(String id) throws Exception;

    public int getCount() throws Exception;
}
