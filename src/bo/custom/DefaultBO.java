package bo.custom;

import bo.SuperBO;
import dto.ChartDTO;

import java.sql.Date;
import java.util.ArrayList;

public interface DefaultBO extends SuperBO {
    ArrayList<ChartDTO> barChart() throws Exception;

    public int getCountForCustomer() throws Exception;

    public int getCountForInstructor() throws Exception;

    public int getCountForVehicle() throws Exception;

    public int getCountForSchedule(Date date) throws Exception;

    public int getTodayTotalSale(Date date) throws Exception;
}
