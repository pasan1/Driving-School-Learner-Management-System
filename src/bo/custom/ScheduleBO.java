package bo.custom;

import bo.SuperBO;
import dto.*;
import entity.ScheduleDetails;

import java.sql.Date;
import java.util.ArrayList;

public interface ScheduleBO extends SuperBO {
    public boolean save(ScheduleDTO dto) throws Exception;

    public boolean update(ScheduleDTO dto) throws Exception;

    public boolean delete(String id) throws Exception;

    public ScheduleDTO search(String id) throws Exception;

    public ArrayList<ScheduleDTO> getAll() throws Exception;

    public ArrayList<ScheduleDetailDTO> getAllSchedules() throws Exception;

    public ArrayList<ScheduleDetailDTO> getSearchScheduleDetails(String id) throws Exception;

    public ArrayList<CustomerDTO> getAllCustomers() throws Exception;

    public ArrayList<InstructorDTO> getAllInstructors() throws Exception;

    public InstructorDTO searchInstructorByNIC(String nic) throws Exception;

    public ArrayList<VehicleDTO> getAllVehicles() throws Exception;

    public VehicleDTO searchVehicleByNumber(String no) throws Exception;

    public String getScheduleId() throws Exception;

    public CustomerDTO searchCustomer(String nic) throws Exception;

    public ArrayList<InstructorDTO> getAllAvailableInstructorsFromSchedule(Date date, String time) throws Exception;

    public ArrayList<VehicleDTO> getAllAvailableVehiclesFromSchedule(Date date, String time) throws Exception;

    public ScheduleDetailDTO getScheduleDetailsFromId(String id) throws Exception;

    public ScheduleDetailDTO getScheduleDetailsFromCusNIC(String nic) throws Exception;

    public boolean addRemainingDates(String id, int dates) throws Exception;

    public boolean minusRemainingDates(String id, int dates) throws Exception;

    public int getRemainingDates(String id) throws Exception;
}
