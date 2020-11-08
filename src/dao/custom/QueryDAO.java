package dao.custom;

import dao.SuperDAO;
import entity.Custom;
import entity.Instructor;
import entity.ScheduleDetails;
import entity.Vehicle;

import java.sql.Date;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    public ArrayList<Custom> getAllCustomersDetails() throws Exception;

    public Custom searchCustomerDetails(String id) throws Exception;

    public Custom searchCustomerDetailsByNIC(String id) throws Exception;

    public ArrayList<Custom> getSearchCustomersDetails(String id) throws Exception;

    public ArrayList<Instructor> getAllAvailableInstructorsFromSchedule(Date date, String time) throws Exception;

    public ArrayList<Vehicle> getAllAvailableVehiclesFromSchedule(Date date, String time) throws Exception;

    public ScheduleDetails getScheduleDetailsFromId(String id) throws Exception;

    public ScheduleDetails getScheduleDetailsFromCusNIC(String nic) throws Exception;

    public ArrayList<ScheduleDetails> getScheduleDetails() throws Exception;

    public ArrayList<ScheduleDetails> getSearchScheduleDetails(String id) throws Exception;
}
