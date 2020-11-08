package controller;

import bo.BOFactory;
import bo.custom.ScheduleBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import tm.ScheduleTM;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class ScheduleFormController {
    public AnchorPane window;
    public JFXButton btnNewSchedule;
    public TableView<ScheduleTM> tblSchedule;
    public TableColumn colScheduleId;
    public TableColumn colCustomerNic;
    public TableColumn colCustomerName;
    public TableColumn colInstructorNic;
    public TableColumn colInstructorName;
    public TableColumn colVehicleNumber;
    public TableColumn colVehicleType;
    public TableColumn colDate;
    public TableColumn colTime;
    public TextField txtSearchCustomer;
    public JFXTextField txtCustomerName;
    public Text lblScheduleID;
    public JFXComboBox<String> cmbCustomerNIC;
    public JFXDatePicker dpDate;
    public JFXTextField txtCustomerNic;
    public JFXTextField txtCustomerId;
    public TextField txtDatesRemaining;
    public JFXComboBox<String> cmbTime;
    public JFXComboBox<String> cmbInstructorNIC;
    public JFXTextField txtInstructorName;
    public JFXTextField txtInstructorNic;
    public JFXTextField txtInstructorId;
    public JFXComboBox<String> cmbVehicleNumber;
    public JFXTextField txtVehicleNumber;
    public JFXTextField txtVehicleId;
    public JFXButton btnAdd;
    public JFXButton btnDelete;
    public JFXTextField txtVehicleType;
    public JFXTextField txtVehicleModel;
    public AnchorPane paneScheduleEdit;
    public JFXButton btnSearchCustomer;
    public JFXButton btnCheckAvailability;

    ScheduleBO bo = (ScheduleBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SCHEDULE);

    public void initialize() {
        setTableCols();
        loadTable();
        loadTime();
        setDefaults();
        loadCustomersToCombo();
        loadLastId();
        txtSearchCustomer.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                ArrayList<ScheduleDetailDTO> allScheduleDetails = bo.getSearchScheduleDetails(newValue);
                ObservableList<ScheduleTM> list = FXCollections.observableArrayList();

                for (ScheduleDetailDTO dto : allScheduleDetails) {
                    list.add(new ScheduleTM(
                            dto.getScheduleId(),
                            dto.getCustomerNic(),
                            dto.getCustomerName(),
                            dto.getInstructorNic(),
                            dto.getInstructorName(),
                            dto.getVehicleNumber(),
                            dto.getVehicleType(),
                            dto.getDate(),
                            dto.getTime()
                    ));
                }
                tblSchedule.setItems(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        tblSchedule.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setDefaults();
            cmbCustomerNIC.setDisable(true);
            if (null != (newValue))
                loadScheduleDataToFormById(newValue.getScheduleId());
            paneScheduleEdit.setDisable(false);
            btnAdd.setText("Update");
            btnDelete.setVisible(true);
            btnAdd.setDisable(false);
            btnDelete.setDisable(false);
        });
    }

    public void cmbCustomerNICOnAction(ActionEvent actionEvent) {
        try {
            CustomerDTO dto = bo.searchCustomer(cmbCustomerNIC.getValue());
            txtCustomerId.setText(dto.getCustomerId());
            txtCustomerName.setText(dto.getFirstName() + " " + dto.getLastName());
            txtCustomerNic.setText(dto.getNic());
            int remainingDates = dto.getRemainingDates();
            txtDatesRemaining.setText(String.valueOf(remainingDates));
            if (dto.getDates() >= dto.getRemainingDates()) {
                btnCheckAvailability.setDisable(false);
                dpDate.requestFocus();
            } else {
                txtDatesRemaining.setStyle("-fx-border-color: red");
                btnCheckAvailability.setDisable(true);
                dpDate.setDisable(true);
                cmbTime.setDisable(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnNewSchedulerOnAction(ActionEvent actionEvent) {
        loadCustomersToCombo();
        btnDelete.setVisible(false);
        paneScheduleEdit.setDisable(false);
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        if (null != cmbCustomerNIC.getValue()) {
            if (null != dpDate.getValue()) {
                if (null != cmbTime.getValue()) {
                    if (null != cmbInstructorNIC.getValue()) {
                        if (null != cmbVehicleNumber.getValue()) {
                            //-----
                            if (btnAdd.getText().equals("Add")) {
                                if (addSchedule()) {
                                    new Alert(Alert.AlertType.INFORMATION, "SuccessFully Added").show();
                                    loadLastId();
                                    loadTable();
                                    setDefaults();
                                } else {
                                    new Alert(Alert.AlertType.WARNING, "Adding Failed").show();
                                }
                            } else if (btnAdd.getText().equals("Update")) {
                                if (updateSchedule()) {
                                    new Alert(Alert.AlertType.INFORMATION, "SuccessFully Updated").show();
                                    loadLastId();
                                    loadTable();
                                    setDefaults();
                                } else {
                                    new Alert(Alert.AlertType.WARNING, "Updating Failed").show();
                                }
                            } else {
                                new Alert(Alert.AlertType.ERROR, "Error on Add button").show();
                            }
                        } else {
                            cmbVehicleNumber.setFocusColor(Paint.valueOf("red"));
                            cmbVehicleNumber.requestFocus();
                        }
                    } else {
                        cmbInstructorNIC.setFocusColor(Paint.valueOf("red"));
                        cmbInstructorNIC.requestFocus();
                    }
                } else {
                    cmbTime.setFocusColor(Paint.valueOf("red"));
                    cmbTime.requestFocus();
                }
            } else {
                dpDate.setDefaultColor(Paint.valueOf("red"));
                dpDate.requestFocus();
            }
        } else {
            cmbCustomerNIC.setFocusColor(Paint.valueOf("red"));
            cmbCustomerNIC.requestFocus();
        }
    }

    private boolean addSchedule() {
        try {
            String date = dpDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            java.sql.Date dbo = java.sql.Date.valueOf(date);
            return bo.save(new ScheduleDTO(
                    lblScheduleID.getText(),
                    txtCustomerId.getText(),
                    txtInstructorId.getText(),
                    txtVehicleId.getText(),
                    dbo,
                    cmbTime.getValue()
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean updateSchedule() {
        try {
            String date = dpDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            java.sql.Date dbo = java.sql.Date.valueOf(date);
            return bo.update(new ScheduleDTO(
                    lblScheduleID.getText(),
                    txtCustomerId.getText(),
                    txtInstructorId.getText(),
                    txtVehicleId.getText(),
                    dbo,
                    cmbTime.getValue()
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete " + lblScheduleID.getText() + "?", ButtonType.YES, ButtonType.NO);
            alert.setHeaderText("Do you want to delete?");
            Optional<ButtonType> buttonType = alert.showAndWait();
            ButtonType btnYes = new ButtonType("Yes");
            ButtonType btnNo = new ButtonType("No");
            if (buttonType.get() == ButtonType.YES) {
                if (bo.delete(lblScheduleID.getText())) {
                    new Alert(Alert.AlertType.INFORMATION, "SuccessFully Deleted").show();
                    loadLastId();
                    loadTable();
                    setDefaults();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Deleting was Failed").show();
                }
            } else if (buttonType.get() == ButtonType.NO) {
                alert.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnSearchCustomerOnAction(ActionEvent actionEvent) {
        if (Pattern.compile("^[0-9]{9}V$").matcher(txtSearchCustomer.getText()).matches() || Pattern.compile("^[0-9]{9}v$").matcher(txtSearchCustomer.getText()).matches() || Pattern.compile("^[0-9]{12}$").matcher(txtSearchCustomer.getText()).matches()) {
            if (Pattern.compile("^[0-9]{9}v$").matcher(txtSearchCustomer.getText()).matches()) {
                String temp = txtSearchCustomer.getText();
                temp = temp.split("[a-z]")[1];
                temp = temp + "V";
                txtSearchCustomer.setText(temp);
            }
            loadScheduleDataToFormByCusNic(txtSearchCustomer.getText());
            paneScheduleEdit.setDisable(false);
            btnAdd.setText("Update");
            btnDelete.setVisible(true);
        } else {
            txtSearchCustomer.setStyle("-fx-border-color: red");
            txtSearchCustomer.requestFocus();
        }
    }

    private void loadScheduleDataToFormById(String id) {
        try {
            ScheduleDetailDTO dto = bo.getScheduleDetailsFromId(id);
            lblScheduleID.setText(dto.getScheduleId());
            cmbCustomerNIC.setValue(dto.getCustomerNic());
            txtCustomerId.setText(dto.getCustomerId());
            txtCustomerName.setText(dto.getCustomerName());
            txtCustomerNic.setText(dto.getCustomerNic());
            txtDatesRemaining.setText(String.valueOf(bo.getRemainingDates(dto.getCustomerId())));
            txtInstructorId.setText(dto.getInstructorId());
            txtInstructorName.setText(dto.getInstructorName());
            txtInstructorNic.setText(dto.getInstructorNic());
            String dob = String.valueOf(dto.getDate());
            dpDate.setValue(LocalDate.parse(dob));
            cmbTime.setValue(dto.getTime());
            txtVehicleId.setText(dto.getVehicleId());
            txtVehicleNumber.setText(dto.getVehicleNumber());
            txtVehicleType.setText(dto.getVehicleType());
            txtVehicleModel.setText(dto.getVehicleModel());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadScheduleDataToFormByCusNic(String nic) {
        try {
            ScheduleDetailDTO dto = bo.getScheduleDetailsFromCusNIC(nic);
            lblScheduleID.setText(dto.getScheduleId());
            cmbCustomerNIC.setValue(dto.getCustomerNic());
            txtCustomerId.setText(dto.getCustomerId());
            txtCustomerName.setText(dto.getCustomerName());
            txtCustomerNic.setText(dto.getCustomerNic());
            txtDatesRemaining.setText(String.valueOf(bo.getRemainingDates(dto.getCustomerId())));
            txtInstructorId.setText(dto.getInstructorId());
            txtInstructorName.setText(dto.getInstructorName());
            txtInstructorNic.setText(dto.getInstructorNic());
            String dob = String.valueOf(dto.getDate());
            dpDate.setValue(LocalDate.parse(dob));
            cmbTime.setValue(dto.getTime());
            txtVehicleId.setText(dto.getVehicleId());
            txtVehicleNumber.setText(dto.getVehicleNumber());
            txtVehicleType.setText(dto.getVehicleType());
            txtVehicleModel.setText(dto.getVehicleModel());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTableCols() {
        colScheduleId.setCellValueFactory(new PropertyValueFactory<>("scheduleId"));
        colCustomerNic.setCellValueFactory(new PropertyValueFactory<>("customerNic"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colInstructorNic.setCellValueFactory(new PropertyValueFactory<>("instructorNic"));
        colInstructorName.setCellValueFactory(new PropertyValueFactory<>("instructorName"));
        colVehicleNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
    }

    private void loadTable() {
        try {
            ArrayList<ScheduleDetailDTO> allScheduleDetails = bo.getAllSchedules();
            ObservableList<ScheduleTM> list = FXCollections.observableArrayList();

            for (ScheduleDetailDTO dto : allScheduleDetails) {
                list.add(new ScheduleTM(
                        dto.getScheduleId(),
                        dto.getCustomerNic(),
                        dto.getCustomerName(),
                        dto.getInstructorNic(),
                        dto.getInstructorName(),
                        dto.getVehicleNumber(),
                        dto.getVehicleType(),
                        dto.getDate(),
                        dto.getTime()
                ));
            }
            tblSchedule.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnCheckAvailabilityOnAction(ActionEvent actionEvent) {
        loadAvailableInstructorAndVehicle();
        cmbInstructorNIC.setDisable(false);
        cmbVehicleNumber.setDisable(false);
    }

    private void loadAvailableInstructorAndVehicle() {
        try {
            String date = dpDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            java.sql.Date dbo = java.sql.Date.valueOf(date);
            String time = cmbTime.getValue();

            ArrayList<InstructorDTO> instructorDTOS = bo.getAllAvailableInstructorsFromSchedule(dbo, time);
            ObservableList<String> listI = FXCollections.observableArrayList();
            for (InstructorDTO dto : instructorDTOS) {
                listI.add(dto.getNic());
            }
            cmbInstructorNIC.setItems(listI);

            ArrayList<VehicleDTO> vehicleDTOS = bo.getAllAvailableVehiclesFromSchedule(dbo, time);
            ObservableList<String> listV = FXCollections.observableArrayList();
            for (VehicleDTO dto : vehicleDTOS) {
                listV.add(dto.getVehicleNumber());
            }
            cmbVehicleNumber.setItems(listV);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDefaults() {
        btnSearchCustomer.setDisable(false);
        btnNewSchedule.setDisable(false);
        paneScheduleEdit.setDisable(true);
        btnAdd.setText("Add");
        txtSearchCustomer.clear();
        txtSearchCustomer.setStyle("-fx-border-color: black");
        btnCheckAvailability.setDisable(true);
        lblScheduleID.setText("");
        txtDatesRemaining.clear();
        cmbCustomerNIC.setDisable(false);
        loadLastId();
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtCustomerNic.clear();
        dpDate.setValue(LocalDate.now());
        dpDate.setDefaultColor(Paint.valueOf("black"));
        loadTime();
        cmbInstructorNIC.setDisable(true);
        txtInstructorId.clear();
        txtInstructorName.clear();
        txtInstructorNic.clear();
        cmbVehicleNumber.setDisable(true);
        txtVehicleId.clear();
        txtVehicleNumber.clear();
        txtVehicleType.clear();
        txtVehicleModel.clear();
        txtDatesRemaining.setStyle("-fx-border-color: black");
        dpDate.setDisable(false);
        cmbTime.setDisable(false);
    }

    private void loadCustomersToCombo() {
        try {
            ArrayList<CustomerDTO> dto = bo.getAllCustomers();
            ObservableList list = FXCollections.observableArrayList();
            for (CustomerDTO customerDTO : dto) {
                list.add(customerDTO.getNic());
            }
            cmbCustomerNIC.setItems(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadLastId() {
        try {
            String lastCode = bo.getScheduleId();
            if (null != lastCode) {
//                lastCode = lastCode.split("[A-Z]")[1];
                lastCode = "" + (Integer.parseInt(lastCode) + 001);
                lblScheduleID.setText(lastCode);
            } else {
                lblScheduleID.setText("1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTime() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("08:00");
        list.add("09:00");
        list.add("10:00");
        list.add("11:00");
        list.add("13:00");
        list.add("14:00");
        list.add("15:00");
        list.add("16:00");
        list.add("17:00");
        cmbTime.setItems(list);
    }

    public void cmbInstructorNICOnAction(ActionEvent actionEvent) {
        try {
            InstructorDTO dto = bo.searchInstructorByNIC(cmbInstructorNIC.getValue());
            txtInstructorId.setText(dto.getInstructorId());
            txtInstructorName.setText(dto.getFirstName() + dto.getLastName());
            txtInstructorNic.setText(dto.getNic());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cmbVehicleNumber.getValue() != null) {
            btnAdd.setVisible(true);
            btnAdd.setDisable(false);
        }
    }

    public void cmbVehicleNumberOnAction(ActionEvent actionEvent) {
        try {
            VehicleDTO dto = bo.searchVehicleByNumber(cmbVehicleNumber.getValue());
            txtVehicleId.setText(dto.getVehicleId());
            txtVehicleNumber.setText(dto.getVehicleNumber());
            txtVehicleType.setText(dto.getType());
            txtVehicleModel.setText(dto.getModel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cmbInstructorNIC.getValue() != null) {
            btnAdd.setVisible(true);
            btnAdd.setDisable(false);
        }
    }

    public void cmbTimeOnAction(ActionEvent actionEvent) {
        cmbInstructorNIC.setDisable(true);
        cmbVehicleNumber.setDisable(true);
        btnCheckAvailability.requestFocus();
    }

    public void dpDateOnAction(ActionEvent actionEvent) {
        cmbInstructorNIC.setDisable(true);
        cmbVehicleNumber.setDisable(true);
        btnCheckAvailability.requestFocus();
    }
}
