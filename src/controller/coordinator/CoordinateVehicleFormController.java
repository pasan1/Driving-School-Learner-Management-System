package controller.coordinator;

import bo.BOFactory;
import bo.custom.VehicleBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.VehicleDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import tm.VehicleTM;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class CoordinateVehicleFormController {
    public AnchorPane window;
    public JFXButton btnSearchVehicle;
    public TableView<VehicleTM> tblVehicle;
    public TableColumn colVehicleID;
    public TableColumn colVehicleNumber;
    public TableColumn colType;
    public TableColumn colTransmission;
    public TableColumn colModel;
    public TableColumn colYear;
    public TextField txtSearchVehicle;
    public AnchorPane paneVehicleEdit;
    public JFXTextField txtVehicleNumber;
    public JFXComboBox<String> cmbTransmission;
    public JFXComboBox<String> cmbType;
    public JFXTextField txtModel;
    public JFXTextField txtYear;
    public Text lblVehicleID;
    public JFXButton btnAdd;
    public JFXButton btnDelete;
    public JFXButton btnNewVehicle;
    public Text txtErrorVehicleNumber;
    public Text txtErrorVehicleNumberInSearch;

    VehicleBO bo = (VehicleBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.VEHICLE);

    public void initialize() {
        setTableCols();
        loadTable();
        setDefault();
        loadLastId();
        txtSearchVehicle.requestFocus();

        txtSearchVehicle.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                ArrayList<VehicleDTO> all = bo.getSearchVehicleDetails(newValue);
                ObservableList<VehicleTM> allVehicles = FXCollections.observableArrayList();
                for (VehicleDTO v : all) {
                    VehicleTM tm = new VehicleTM(
                            v.getVehicleId(),
                            v.getVehicleNumber(),
                            v.getType(),
                            v.getTransmission(),
                            v.getModel(),
                            v.getYear()
                    );
                    allVehicles.add(tm);
                }
                tblVehicle.setItems(allVehicles);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        tblVehicle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setDefault();
            if (null != newValue)
                loadInstructorDataToForm(newValue.getVehicleId());
            paneVehicleEdit.setDisable(false);
            btnAdd.setText("Update");
            btnDelete.setVisible(true);
        });
    }

    public void btnSearchVehicleOnAction(ActionEvent actionEvent) {
        if (Pattern.compile("^[A-Z0-9]{2,3}-[0-9]{4}$").matcher(txtSearchVehicle.getText()).matches()) {
            loadInstructorDataToFormByNumber(txtSearchVehicle.getText());
            paneVehicleEdit.setDisable(false);
            btnAdd.setText("Update");
            btnDelete.setVisible(true);
        } else {
            txtSearchVehicle.setStyle("-fx-border-color: red");
            txtSearchVehicle.requestFocus();
            txtErrorVehicleNumberInSearch.setVisible(true);
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        if (Pattern.compile("^[A-Z0-9]{2,3}-[0-9]{4}$").matcher(txtVehicleNumber.getText()).matches()) {
            if (Pattern.compile("^[A-z0-9]{1,}$").matcher(txtModel.getText()).matches()) {
                if (Pattern.compile("^[0-9]{4}$").matcher(txtYear.getText()).matches()) {
                    if (null != cmbType.getValue()) {
                        if (null != cmbTransmission.getValue()) {
                            //-----
                            if (btnAdd.getText().equals("Add")) {
                                if (addVehicle()) {
                                    new Alert(Alert.AlertType.INFORMATION, "SuccessFully Added").show();
                                    loadLastId();
                                    loadTable();
                                    setDefault();
                                } else {
                                    new Alert(Alert.AlertType.WARNING, "Adding Failed").show();
                                }
                            } else if (btnAdd.getText().equals("Update")) {
                                if (updateVehicle()) {
                                    new Alert(Alert.AlertType.INFORMATION, "SuccessFully Updated").show();
                                    loadLastId();
                                    loadTable();
                                    setDefault();
                                } else {
                                    new Alert(Alert.AlertType.WARNING, "Updating Failed").show();
                                }
                            } else {
                                new Alert(Alert.AlertType.ERROR, "Error on Add button").show();
                            }
                        } else {
                            cmbTransmission.setFocusColor(Paint.valueOf("red"));
                            cmbTransmission.requestFocus();
                        }
                    } else {
                        cmbType.setFocusColor(Paint.valueOf("red"));
                        cmbType.requestFocus();
                    }
                } else {
                    txtYear.setFocusColor(Paint.valueOf("red"));
                    txtYear.requestFocus();
                }
            } else {
                txtModel.setFocusColor(Paint.valueOf("red"));
                txtModel.requestFocus();
            }
        } else {
            txtVehicleNumber.setFocusColor(Paint.valueOf("red"));
            txtVehicleNumber.requestFocus();
            txtErrorVehicleNumber.setVisible(true);
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete " + lblVehicleID.getText() + " - " + txtVehicleNumber.getText() + "?", ButtonType.YES, ButtonType.NO);
            alert.setHeaderText("Do you want to delete?");
            Optional<ButtonType> buttonType = alert.showAndWait();
            ButtonType btnYes = new ButtonType("Yes");
            ButtonType btnNo = new ButtonType("No");
            if (buttonType.get() == ButtonType.YES) {
                if (bo.delete(lblVehicleID.getText())) {
                    new Alert(Alert.AlertType.INFORMATION, "SuccessFully Deleted").show();
                    loadLastId();
                    loadTable();
                    setDefault();
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

    public void btnNewVehicleOnAction(ActionEvent actionEvent) {
        setDefault();
        loadLastId();
        paneVehicleEdit.setDisable(false);
        btnDelete.setVisible(false);
    }

    private void loadInstructorDataToForm(String vehicleId) {
        try {
            VehicleDTO dto = bo.search(vehicleId);
            lblVehicleID.setText(dto.getVehicleId());
            txtVehicleNumber.setText(dto.getVehicleNumber());
            cmbType.setValue(dto.getType());
            cmbTransmission.setValue(dto.getTransmission());
            txtModel.setText(dto.getModel());
            txtModel.setText(dto.getYear());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadInstructorDataToFormByNumber(String vehicleId) {
        try {
            VehicleDTO dto = bo.searchByNumber(vehicleId);
            lblVehicleID.setText(dto.getVehicleId());
            txtVehicleNumber.setText(dto.getVehicleNumber());
            cmbType.setValue(dto.getType());
            cmbTransmission.setValue(dto.getTransmission());
            txtModel.setText(dto.getModel());
            txtModel.setText(dto.getYear());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean addVehicle() {
        try {
            return bo.save(new VehicleDTO(
                    lblVehicleID.getText(),
                    txtVehicleNumber.getText(),
                    cmbType.getValue(),
                    cmbTransmission.getValue(),
                    txtModel.getText(),
                    txtYear.getText()
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean updateVehicle() {
        try {
            return bo.update(new VehicleDTO(
                    lblVehicleID.getText(),
                    txtVehicleNumber.getText(),
                    cmbType.getValue(),
                    cmbTransmission.getValue(),
                    txtModel.getText(),
                    txtYear.getText()
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void loadLastId() {
        try {
            String lastCode = bo.getVehicleLastId();
            if (null != lastCode) {
//                lastCode = lastCode.split("[A-Z]")[1];
                lastCode = "" + (Integer.parseInt(lastCode) + 001);
                lblVehicleID.setText(lastCode);
            } else {
                lblVehicleID.setText("1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDefault() {
        txtSearchVehicle.clear();
        btnSearchVehicle.setDisable(false);
        btnNewVehicle.setDisable(false);
        paneVehicleEdit.setDisable(true);
        btnAdd.setText("Add");
        btnDelete.setVisible(true);
        lblVehicleID.setText("");
        loadLastId();
        txtVehicleNumber.clear();
        loadType();
        loadTransmission();
        txtModel.clear();
        txtYear.clear();
        txtErrorVehicleNumber.setVisible(false);
        txtErrorVehicleNumberInSearch.setVisible(false);
        txtSearchVehicle.setStyle("-fx-border-color: black");
        txtVehicleNumber.setFocusColor(Paint.valueOf("black"));
        cmbType.setFocusColor(Paint.valueOf("black"));
        cmbTransmission.setFocusColor(Paint.valueOf("black"));
        txtModel.setFocusColor(Paint.valueOf("black"));
        txtYear.setFocusColor(Paint.valueOf("black"));
    }

    private void setTableCols() {
        colVehicleID.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
        colVehicleNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colTransmission.setCellValueFactory(new PropertyValueFactory<>("transmission"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
    }

    private void loadTable() {
        try {
            ArrayList<VehicleDTO> all = bo.getAll();
            ObservableList<VehicleTM> allVehicles = FXCollections.observableArrayList();
            for (VehicleDTO v : all) {
                VehicleTM tm = new VehicleTM(
                        v.getVehicleId(),
                        v.getVehicleNumber(),
                        v.getType(),
                        v.getTransmission(),
                        v.getModel(),
                        v.getYear()
                );
                allVehicles.add(tm);
            }
            tblVehicle.setItems(allVehicles);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTransmission() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Manual");
        list.add("Auto");
        cmbTransmission.setItems(list);
    }

    private void loadType() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Car");
        list.add("Van");
        list.add("Jeep");
        list.add("Three-Wheel");
        list.add("Motor-Bicycle");
        list.add("Bus");
        list.add("Lorry");
        cmbType.setItems(list);
    }
}
