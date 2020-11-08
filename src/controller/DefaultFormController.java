package controller;

import bo.BOFactory;
import bo.custom.DefaultBO;
import dto.ChartDTO;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.ArrayList;

public class DefaultFormController {

    public AnchorPane window;
    public Text txtTotalCustomers;
    public Text txtTotalVehicles;
    public Text txtTotalInstructor;
    public Text txtTotalSchedule;
    public Text txtTotalSales;
    public Text txtTodayDate;
    public Text txtTodayD;
    @FXML
    private BarChart<String, Double> chartDailySummery;

    DefaultBO bo = (DefaultBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DEFAULT);

    public void initialize() throws Exception {
        loadChart();
        loadTotals();
    }

    private void loadTotals() {
        try {
            txtTotalCustomers.setText(String.valueOf(bo.getCountForCustomer()));
            txtTotalInstructor.setText(String.valueOf(bo.getCountForInstructor()));
            txtTotalVehicles.setText(String.valueOf(bo.getCountForVehicle()));
            txtTotalSchedule.setText(String.valueOf(bo.getCountForSchedule(java.sql.Date.valueOf(LocalDate.now()))));
            txtTodayDate.setText(String.valueOf(java.sql.Date.valueOf(LocalDate.now())));
            txtTodayD.setText(String.valueOf(java.sql.Date.valueOf(LocalDate.now())));
            txtTotalSales.setText(String.valueOf(bo.getTodayTotalSale(java.sql.Date.valueOf(LocalDate.now()))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadChart() throws Exception {
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        ArrayList<ChartDTO> barchart = bo.barChart();

        for (ChartDTO chartDTO : barchart) {
            series.getData().add(new XYChart.Data<>(chartDTO.getDate(), chartDTO.getAmount()));
        }
        chartDailySummery.getData().add(series);
    }
}
