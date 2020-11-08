package bo.custom.impl;

import bo.custom.ScheduleBO;
import dao.DAOFactory;
import dao.custom.*;
import db.DBConnection;
import dto.*;
import entity.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

public class ScheduleBOImpl implements ScheduleBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    CustomerProgramDAO customerProgramDAO = (CustomerProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMERPROGRAM);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
    InstructorDAO instructorDAO = (InstructorDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.INSTRUCTOR);
    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.VEHICLE);
    ScheduleDAO scheduleDAO = (ScheduleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SCHEDULE);

    private boolean temp;

    @Override
    public boolean save(ScheduleDTO dto) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        temp=false;
        try {
            if (scheduleDAO.save(new Schedule(dto.getScheduleId(), dto.getCustomerId(), dto.getInstructorId(), dto.getVehicleId(), dto.getDate(), dto.getTime()))){
                if (customerProgramDAO.addRemainingDates(dto.getCustomerId(),1)){
                    connection.commit();
                    temp=true;
                } else {
                    connection.rollback();
                    temp=false;
                }
            } else {
                connection.rollback();
                temp=false;
            }
        } finally {
            connection.setAutoCommit(true);
        }
        return temp;
    }

    @Override
    public boolean update(ScheduleDTO dto) throws Exception {
        return scheduleDAO.update(new Schedule(dto.getScheduleId(), dto.getCustomerId(), dto.getInstructorId(), dto.getVehicleId(), dto.getDate(), dto.getTime()));
    }

    @Override
    public boolean delete(String id) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        temp=false;
        try {
            if (scheduleDAO.delete(id)){
                System.out.println("scheduleDAO: true");
                if (customerProgramDAO.minusRemainingDates(scheduleDAO.getCustomerID(id),1)){  //error
                    System.out.println("customerProgramDAO: true");
                    connection.commit();
                    temp=true;
                } else {
                    System.out.println("customerProgramDAO: false");
                    connection.rollback();
                    temp=false;
                }
            } else {
                System.out.println("scheduleDAO: false");
                connection.rollback();
                temp=false;
            }
        } finally {
            connection.setAutoCommit(true);
        }
        return temp;
    }

    @Override
    public ScheduleDTO search(String id) throws Exception {
        Schedule search = scheduleDAO.search(id);
        return new ScheduleDTO(
                search.getScheduleId(),
                search.getCustomerId(),
                search.getInstructorId(),
                search.getVehicleId(),
                search.getDate(),
                search.getTime()
        );
    }

    @Override
    public ArrayList<ScheduleDTO> getAll() throws Exception {
        ArrayList<Schedule> all = scheduleDAO.getAll();
        ArrayList<ScheduleDTO> allSchedules = new ArrayList<>();
        for (Schedule s : all) {
            ScheduleDTO dto = new ScheduleDTO(
                    s.getScheduleId(),
                    s.getCustomerId(),
                    s.getInstructorId(),
                    s.getVehicleId(),
                    s.getDate(),
                    s.getTime()
            );
            allSchedules.add(dto);
        }
        return allSchedules;
    }

    @Override
    public ArrayList<ScheduleDetailDTO> getAllSchedules() throws Exception {
        ArrayList<ScheduleDetails> all = queryDAO.getScheduleDetails();
        ArrayList<ScheduleDetailDTO> allSchedules = new ArrayList<>();
        for (ScheduleDetails s : all) {
            ScheduleDetailDTO dto = new ScheduleDetailDTO(
                    s.getScheduleId(),
                    s.getCustomerId(),
                    s.getCustomerNic(),
                    s.getCustomerName(),
                    s.getInstructorNic(),
                    s.getInstructorNic(),
                    s.getInstructorName(),
                    s.getVehicleId(),
                    s.getVehicleNumber(),
                    s.getVehicleType(),
                    s.getVehicleModel(),
                    s.getDate(),
                    s.getTime()
            );
            allSchedules.add(dto);
        }
        return allSchedules;
    }

    @Override
    public ArrayList<ScheduleDetailDTO> getSearchScheduleDetails(String id) throws Exception {
        ArrayList<ScheduleDetails> all = queryDAO.getSearchScheduleDetails(id);
        ArrayList<ScheduleDetailDTO> allSchedules = new ArrayList<>();
        for (ScheduleDetails s : all) {
            ScheduleDetailDTO dto = new ScheduleDetailDTO(
                    s.getScheduleId(),
                    s.getCustomerId(),
                    s.getCustomerNic(),
                    s.getCustomerName(),
                    s.getInstructorNic(),
                    s.getInstructorNic(),
                    s.getInstructorName(),
                    s.getVehicleId(),
                    s.getVehicleNumber(),
                    s.getVehicleType(),
                    s.getVehicleModel(),
                    s.getDate(),
                    s.getTime()
            );
            allSchedules.add(dto);
        }
        return allSchedules;
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
    public ArrayList<InstructorDTO> getAllInstructors() throws Exception {
        ArrayList<Instructor> all = instructorDAO.getAll();
        ArrayList<InstructorDTO> allInstructors = new ArrayList<>();
        for (Instructor i : all) {
            InstructorDTO dto = new InstructorDTO(
                    i.getInstructorId(),
                    i.getFirstName(),
                    i.getLastName(),
                    i.getNic(),
                    i.getGender(),
                    i.getDob(),
                    i.getAddress(),
                    i.getMobileNumber(),
                    i.getDrivingLicense(),
                    i.getEpfNo()
            );
            allInstructors.add(dto);
        }
        return allInstructors;
    }

    @Override
    public InstructorDTO searchInstructorByNIC(String nic) throws Exception {
        Instructor i = instructorDAO.searchByNIC(nic);
        return new InstructorDTO(
                i.getInstructorId(),
                i.getFirstName(),
                i.getLastName(),
                i.getNic(),
                i.getGender(),
                i.getDob(),
                i.getAddress(),
                i.getMobileNumber(),
                i.getDrivingLicense(),
                i.getEpfNo()
        );
    }

    @Override
    public ArrayList<VehicleDTO> getAllVehicles() throws Exception {
        ArrayList<Vehicle> all = vehicleDAO.getAll();
        ArrayList<VehicleDTO> allVehicles = new ArrayList<>();
        for (Vehicle v : all) {
            VehicleDTO dto = new VehicleDTO(
                    v.getVehicleId(),
                    v.getVehicleNumber(),
                    v.getType(),
                    v.getTransmission(),
                    v.getModel(),
                    v.getYear()
            );
            allVehicles.add(dto);
        }
        return allVehicles;
    }

    @Override
    public VehicleDTO searchVehicleByNumber(String no) throws Exception {
        Vehicle v = vehicleDAO.searchByNumber(no);
        return new VehicleDTO(
                v.getVehicleId(),
                v.getVehicleNumber(),
                v.getType(),
                v.getTransmission(),
                v.getModel(),
                v.getYear()
        );
    }

    @Override
    public String getScheduleId() throws Exception {
        return scheduleDAO.getScheduleId();
    }

    @Override
    public CustomerDTO searchCustomer(String nic) throws Exception {
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
    public ArrayList<InstructorDTO> getAllAvailableInstructorsFromSchedule(Date date, String time) throws Exception {
        ArrayList<Instructor> allAvailableInstructorsFromSchedule = queryDAO.getAllAvailableInstructorsFromSchedule(date, time);
        ArrayList<InstructorDTO> allInstructors = new ArrayList<>();
        for (Instructor i : allAvailableInstructorsFromSchedule) {
            InstructorDTO dto = new InstructorDTO(
                    i.getInstructorId(),
                    i.getFirstName(),
                    i.getLastName(),
                    i.getNic(),
                    i.getGender(),
                    i.getDob(),
                    i.getAddress(),
                    i.getMobileNumber(),
                    i.getDrivingLicense(),
                    i.getEpfNo()
            );
            allInstructors.add(dto);
        }
        return allInstructors;
    }

    @Override
    public ArrayList<VehicleDTO> getAllAvailableVehiclesFromSchedule(Date date, String time) throws Exception {
        ArrayList<Vehicle> allAvailableVehiclesFromSchedule = queryDAO.getAllAvailableVehiclesFromSchedule(date, time);
        ArrayList<VehicleDTO> allVehicles = new ArrayList<>();
        for (Vehicle v : allAvailableVehiclesFromSchedule) {
            VehicleDTO dto = new VehicleDTO(
                    v.getVehicleId(),
                    v.getVehicleNumber(),
                    v.getType(),
                    v.getTransmission(),
                    v.getModel(),
                    v.getYear()
            );
            allVehicles.add(dto);
        }
        return allVehicles;
    }

    @Override
    public ScheduleDetailDTO getScheduleDetailsFromId(String id) throws Exception {
        ScheduleDetails s = queryDAO.getScheduleDetailsFromId(id);
        return new ScheduleDetailDTO(
                s.getScheduleId(),
                s.getCustomerId(),
                s.getCustomerNic(),
                s.getCustomerName(),
                s.getInstructorNic(),
                s.getInstructorNic(),
                s.getInstructorName(),
                s.getVehicleId(),
                s.getVehicleNumber(),
                s.getVehicleType(),
                s.getVehicleModel(),
                s.getDate(),
                s.getTime()
        );
    }

    @Override
    public ScheduleDetailDTO getScheduleDetailsFromCusNIC(String nic) throws Exception {
        ScheduleDetails s = queryDAO.getScheduleDetailsFromCusNIC(nic);
        return new ScheduleDetailDTO(
                s.getScheduleId(),
                s.getCustomerId(),
                s.getCustomerNic(),
                s.getCustomerName(),
                s.getInstructorNic(),
                s.getInstructorNic(),
                s.getInstructorName(),
                s.getVehicleId(),
                s.getVehicleNumber(),
                s.getVehicleType(),
                s.getVehicleModel(),
                s.getDate(),
                s.getTime()
        );
    }

    @Override
    public boolean addRemainingDates(String id, int dates) throws Exception {
        return customerProgramDAO.addRemainingDates(id,dates);
    }

    @Override
    public boolean minusRemainingDates(String id, int dates) throws Exception {
        return customerProgramDAO.minusRemainingDates(id,dates);
    }

    @Override
    public int getRemainingDates(String id) throws Exception {
        return customerProgramDAO.getRemainingDates(id);
    }
}
