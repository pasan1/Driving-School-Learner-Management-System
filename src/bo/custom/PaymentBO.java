package bo.custom;

import bo.SuperBO;
import dto.ChartDTO;
import dto.CustomerDTO;
import dto.PaymentDTO;

import java.util.ArrayList;

public interface PaymentBO extends SuperBO {

    public String getPaymentLastId() throws Exception;

    public boolean save(PaymentDTO dto) throws Exception;

    public ArrayList<CustomerDTO> getAllCustomers() throws Exception;

    public CustomerDTO searchCustomerByNIC(String nic) throws Exception;
}
