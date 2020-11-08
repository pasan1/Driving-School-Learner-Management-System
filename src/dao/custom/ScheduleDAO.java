package dao.custom;

import dao.CrudDAO;
import entity.Schedule;

import java.sql.Date;

public interface ScheduleDAO extends CrudDAO<Schedule, String> {
    public String getScheduleId() throws Exception;

    public int getCount(Date date) throws Exception;

    public String getCustomerID(String id) throws Exception;
}
