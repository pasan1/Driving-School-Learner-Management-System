package dao.custom;

import dao.CrudDAO;
import entity.CustomerProgram;

public interface CustomerProgramDAO extends CrudDAO<CustomerProgram, String> {
    public double getBalance(String id) throws Exception;

    public boolean updateBalance(String id, String balanceAmount) throws Exception;

    public int getRemainingDates(String id) throws Exception;

    public boolean addRemainingDates(String id, int dates) throws Exception;

    public boolean minusRemainingDates(String id, int dates) throws Exception;
}
