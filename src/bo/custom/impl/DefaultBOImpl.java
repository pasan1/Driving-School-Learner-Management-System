package bo.custom.impl;

import bo.custom.DefaultBO;
import dao.DAOFactory;
import dao.custom.*;
import dto.ChartDTO;
import entity.Payment;

import java.sql.Date;
import java.util.ArrayList;

public class DefaultBOImpl implements DefaultBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    InstructorDAO instructorDAO = (InstructorDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.INSTRUCTOR);
    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.VEHICLE);
    ScheduleDAO scheduleDAO = (ScheduleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SCHEDULE);

    @Override
    public ArrayList<ChartDTO> barChart() throws Exception {
        ArrayList<Payment> populate = paymentDAO.chart();
        ArrayList<ChartDTO> amount = new ArrayList<>();
        for (Payment chart : populate) {
            amount.add(new ChartDTO(String.valueOf(chart.getDate()), chart.getAmount()));
        }
        return amount;
    }

    @Override
    public int getCountForCustomer() throws Exception {
        return customerDAO.getCount();
    }

    @Override
    public int getCountForInstructor() throws Exception {
        return instructorDAO.getCount();
    }

    @Override
    public int getCountForVehicle() throws Exception {
        return vehicleDAO.getCount();
    }

    @Override
    public int getCountForSchedule(Date date) throws Exception {
        return scheduleDAO.getCount(date);
    }

    @Override
    public int getTodayTotalSale(Date date) throws Exception {
        return paymentDAO.getTodayTotalSale(date);
    }
}
