package controller.manage;

import bo.BOFactory;
import bo.custom.InstructorBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import dto.InstructorDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import tm.CustomerTM;
import tm.InstructorTM;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class ManageInstructorFormController {

    public AnchorPane window;
    public JFXButton btnSearchInstructor;
    public TableView<InstructorTM> tblInstructor;
    public TableColumn colInstructorID;
    public TableColumn colEPFno;
    public TableColumn colFirstName;
    public TableColumn colLastName;
    public TableColumn colNIC;
    public TableColumn colAddress;
    public TableColumn colMobileNumber;
    public TableColumn colDrivingLicenseNo;
    public TextField txtSearchInstructor;
    public AnchorPane paneInstructorEdit;
    public JFXTextField txtFirstName;
    public JFXTextField txtLastName;
    public JFXTextField txtNIC;
    public JFXTextField txtAddress;
    public JFXTextField txtMobileNumber;
    public JFXTextField txtDrivingLicenseNo;
    public Text lblInstructorID;
    public JFXButton btnAdd;
    public JFXButton btnDelete;
    public JFXTextField txtEPFno;
    public JFXComboBox<String> cmbGender;
    public JFXDatePicker dpDob;
    public JFXButton btnNewInstructor;

    InstructorBO bo = (InstructorBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.INSTRUCTOR);

    public void initialize() {
        setDefault();
        setTableCols();
        loadLastId();
        loadTable();
        txtSearchInstructor.requestFocus();

        txtSearchInstructor.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                ArrayList<InstructorDTO> allInstructorsDetails = bo.getSearchInstructorDetails(newValue);
                ObservableList<InstructorTM> list = FXCollections.observableArrayList();

                for (InstructorDTO dto : allInstructorsDetails) {
                    list.add(new InstructorTM(
                            dto.getInstructorId(),
                            dto.getFirstName(),
                            dto.getLastName(),
                            dto.getNic(),
                            dto.getAddress(),
                            dto.getMobileNumber(),
                            dto.getDrivingLicense(),
                            dto.getEpfNo()
                    ));
                }
                tblInstructor.setItems(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        tblInstructor.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setDefault();
            if (null != newValue)
                loadInstructorDataToForm(newValue.getInstructorId());
            paneInstructorEdit.setDisable(false);
            btnAdd.setText("Update");
            btnDelete.setVisible(true);
        });
    }

    public void btnSearchInstructorOnAction(ActionEvent actionEvent) {
        if (Pattern.compile("^[0-9]{9}V$").matcher(txtSearchInstructor.getText()).matches() || Pattern.compile("^[0-9]{9}v$").matcher(txtSearchInstructor.getText()).matches() || Pattern.compile("^[0-9]{12}$").matcher(txtSearchInstructor.getText()).matches()) {
            if (Pattern.compile("^[0-9]{9}v$").matcher(txtNIC.getText()).matches()) {
                String temp = txtNIC.getText();
                temp = temp.split("[a-z]")[1];
                temp = temp + "V";
                txtNIC.setText(temp);
            }
            loadInstructorDataToFormByNumber(txtSearchInstructor.getText());
            paneInstructorEdit.setDisable(false);
            btnAdd.setText("Update");
            btnDelete.setVisible(true);
        } else {
            txtSearchInstructor.setStyle("-fx-border-color: red");
            txtSearchInstructor.requestFocus();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        if (Pattern.compile("^[A-z ]{2,}$").matcher(txtFirstName.getText()).matches()) {
            if (Pattern.compile("^[A-z ]{2,}$").matcher(txtLastName.getText()).matches()) {
                if (Pattern.compile("^[0-9]{9}V$").matcher(txtNIC.getText()).matches() || Pattern.compile("^[0-9]{9}v$").matcher(txtNIC.getText()).matches() || Pattern.compile("^[0-9]{12}$").matcher(txtNIC.getText()).matches()) {
                    if (Pattern.compile("^[0-9]{9}v$").matcher(txtNIC.getText()).matches()) {
                        String temp = txtNIC.getText();
                        temp = temp.toUpperCase();
                        txtNIC.setText(temp);
                    }
                    if (null != txtDrivingLicenseNo.getText()) {
                        if (Pattern.compile("^[A-z0-9 ,.]{2,}$").matcher(txtAddress.getText()).matches()) {
                            if (Pattern.compile("^0[0-9]{9}$").matcher(txtMobileNumber.getText()).matches()) {
                                if (dpDob.getValue() != null) {
                                    if (cmbGender.getValue() != null) {
                                        if (Pattern.compile("^[0-9]{1,}$").matcher(txtEPFno.getText()).matches()) {
                                            //-----
                                            if (btnAdd.getText().equals("Add")) {
                                                if (addInstructor()) {
                                                    new Alert(Alert.AlertType.INFORMATION, "SuccessFully Added").show();
                                                    loadLastId();
                                                    loadTable();
                                                    setDefault();
                                                } else {
                                                    new Alert(Alert.AlertType.WARNING, "Adding Failed").show();
                                                }
                                            } else if (btnAdd.getText().equals("Update")) {
                                                if (updateInstructor()) {
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
                                            txtEPFno.setFocusColor(Paint.valueOf("red"));
                                            txtEPFno.requestFocus();
                                        }
                                    } else {
                                        cmbGender.setFocusColor(Paint.valueOf("red"));
                                        cmbGender.requestFocus();
                                    }
                                } else {
                                    dpDob.setDefaultColor(Paint.valueOf("red"));
                                    dpDob.requestFocus();
                                }
                            } else {
                                txtMobileNumber.setFocusColor(Paint.valueOf("red"));
                                txtMobileNumber.requestFocus();
                            }
                        } else {
                            txtAddress.setFocusColor(Paint.valueOf("red"));
                            txtAddress.requestFocus();
                        }
                    } else {
                        txtDrivingLicenseNo.setFocusColor(Paint.valueOf("red"));
                        txtDrivingLicenseNo.requestFocus();
                    }
                } else {
                    txtLastName.setFocusColor(Paint.valueOf("red"));
                    txtLastName.requestFocus();
                }
            } else {
                txtLastName.setFocusColor(Paint.valueOf("red"));
                txtLastName.requestFocus();
            }
        } else {
            txtFirstName.setFocusColor(Paint.valueOf("red"));
            txtFirstName.requestFocus();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete " + lblInstructorID.getText() + " " + txtFirstName.getText() + "?", ButtonType.YES, ButtonType.NO);
            alert.setHeaderText("Do you want to delete?");
            Optional<ButtonType> buttonType = alert.showAndWait();
            ButtonType btnYes = new ButtonType("Yes");
            ButtonType btnNo = new ButtonType("No");
            if (buttonType.get() == ButtonType.YES) {
                if (bo.delete(lblInstructorID.getText())) {
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

    public void btnNewInstructorOnAction(ActionEvent actionEvent) {
        setDefault();
        loadLastId();
        paneInstructorEdit.setDisable(false);
        btnDelete.setVisible(false);
        loadGender();
    }

    private void loadInstructorDataToForm(String id) {
        try {
            InstructorDTO dto = bo.search(id);
            lblInstructorID.setText(dto.getInstructorId());
            txtFirstName.setText(dto.getFirstName());
            txtLastName.setText(dto.getLastName());
            txtNIC.setText(dto.getNic());
            txtDrivingLicenseNo.setText(dto.getDrivingLicense());
            txtAddress.setText(dto.getAddress());
            String dob = String.valueOf(dto.getDob());
            dpDob.setValue(LocalDate.parse(dob));
            cmbGender.setValue(dto.getGender());
            txtMobileNumber.setText(dto.getMobileNumber());
            txtEPFno.setText(dto.getEpfNo());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadInstructorDataToFormByNumber(String nic) {
        try {
            InstructorDTO dto = bo.searchByNIC(nic);
            lblInstructorID.setText(dto.getInstructorId());
            txtFirstName.setText(dto.getFirstName());
            txtLastName.setText(dto.getLastName());
            txtNIC.setText(dto.getNic());
            txtDrivingLicenseNo.setText(dto.getDrivingLicense());
            txtAddress.setText(dto.getAddress());
            String dob = String.valueOf(dto.getDob());
            dpDob.setValue(LocalDate.parse(dob));
            cmbGender.setValue(dto.getGender());
            txtMobileNumber.setText(dto.getMobileNumber());
            txtEPFno.setText(dto.getEpfNo());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean addInstructor() {
        try {
            String date = dpDob.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            java.sql.Date dbo = java.sql.Date.valueOf(date);
            return bo.save(new InstructorDTO(
                    lblInstructorID.getText(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtNIC.getText(),
                    cmbGender.getValue(),
                    dbo,
                    txtAddress.getText(),
                    txtMobileNumber.getText(),
                    txtDrivingLicenseNo.getText(),
                    txtEPFno.getText()
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean updateInstructor() {
        try {
            String date = dpDob.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            java.sql.Date dbo = java.sql.Date.valueOf(date);
            return bo.update(new InstructorDTO(
                    lblInstructorID.getText(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtNIC.getText(),
                    cmbGender.getValue(),
                    dbo,
                    txtAddress.getText(),
                    txtMobileNumber.getText(),
                    txtDrivingLicenseNo.getText(),
                    txtEPFno.getText()
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void loadGender() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Male");
        list.add("Female");
        cmbGender.setItems(list);
    }

    private void setTableCols() {
        colInstructorID.setCellValueFactory(new PropertyValueFactory<>("instructorId"));
        colEPFno.setCellValueFactory(new PropertyValueFactory<>("epfNo"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colMobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        colDrivingLicenseNo.setCellValueFactory(new PropertyValueFactory<>("drivingLicense"));
    }

    private void loadTable() {
        try {
            ArrayList<InstructorDTO> allInstructorsDetails = bo.getAll();
            ObservableList<InstructorTM> list = FXCollections.observableArrayList();

            for (InstructorDTO dto : allInstructorsDetails) {
                list.add(new InstructorTM(
                        dto.getInstructorId(),
                        dto.getEpfNo(),
                        dto.getFirstName(),
                        dto.getLastName(),
                        dto.getNic(),
                        dto.getAddress(),
                        dto.getMobileNumber(),
                        dto.getDrivingLicense()
                ));
            }
            tblInstructor.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadLastId() {
        try {
            String lastCode = bo.getInstructorLastId();
            if (null != lastCode) {
//                lastCode = lastCode.split("[A-Z]")[1];
                lastCode = "" + (Integer.parseInt(lastCode) + 001);
                lblInstructorID.setText(lastCode);
            } else {
                lblInstructorID.setText("1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDefault() {
        paneInstructorEdit.setDisable(true);
        txtSearchInstructor.clear();
        btnNewInstructor.setDisable(false);
        btnAdd.setText("Add");
        btnDelete.setVisible(true);
        lblInstructorID.setText("");
        txtFirstName.clear();
        txtLastName.clear();
        txtNIC.clear();
        txtDrivingLicenseNo.clear();
        txtAddress.clear();
        dpDob.setValue(LocalDate.now());
        loadGender();
        txtMobileNumber.clear();
        txtEPFno.clear();

        txtSearchInstructor.setStyle("-fx-border-color: black");
        txtFirstName.setFocusColor(Paint.valueOf("black"));
        txtLastName.setFocusColor(Paint.valueOf("black"));
        txtNIC.setFocusColor(Paint.valueOf("black"));
        txtDrivingLicenseNo.setFocusColor(Paint.valueOf("black"));
        txtAddress.setFocusColor(Paint.valueOf("black"));
        dpDob.setDefaultColor(Paint.valueOf("#2f2fff"));
        cmbGender.setFocusColor(Paint.valueOf("black"));
        txtMobileNumber.setFocusColor(Paint.valueOf("black"));
        txtEPFno.setFocusColor(Paint.valueOf("black"));
    }
}
