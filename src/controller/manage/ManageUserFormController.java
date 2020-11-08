package controller.manage;

import bo.BOFactory;
import bo.custom.UserBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.InstructorDTO;
import dto.UserInfoDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import tm.InstructorTM;
import tm.UserTM;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class ManageUserFormController {
    public AnchorPane window;
    public JFXButton btnSearchUser;
    public TableView<UserTM> tbIUser;
    public TableColumn colUserID;
    public TableColumn colFirstName;
    public TableColumn colLastName;
    public TableColumn colNIC;
    public TableColumn colAddress;
    public TableColumn colMobileNumber;
    public TableColumn colDesignation;
    public TableColumn colUserName;
    public TextField txtSearchUser;
    public AnchorPane paneUserEdit;
    public JFXTextField txtFirstName;
    public JFXTextField txtLastName;
    public JFXTextField txtNIC;
    public JFXTextField txtAddress;
    public JFXTextField txtMobileNumber;
    public JFXTextField txtDrivingLicenseNo;
    public Text lblUserID;
    public JFXButton btnAdd;
    public JFXButton btnDelete;
    public JFXComboBox<String> cmbGender;
    public JFXDatePicker dpDob;
    public JFXTextField txtUserName;
    public JFXComboBox<String> cmbDesignation;
    public Text lblNewUser;
    public JFXButton btnNewUser;

    UserBO bo = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    public void initialize() {
        setTableCols();
        setDefault();
        loadTable();
        txtSearchUser.requestFocus();

        txtSearchUser.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                ArrayList<UserInfoDTO> allUsersDetails = bo.getUsersDetails(newValue);
                ObservableList<UserTM> list = FXCollections.observableArrayList();

                for (UserInfoDTO dto : allUsersDetails) {
                    list.add(new UserTM(
                            dto.getUserId(),
                            dto.getFirstName(),
                            dto.getLastName(),
                            dto.getNic(),
                            dto.getAddress(),
                            dto.getMobileNumber(),
                            dto.getDesignation(),
                            dto.getUserName()
                    ));
                }
                tbIUser.setItems(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        tbIUser.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setDefault();
            if (null != newValue)
                loadInstructorDataToForm(newValue.getUserId());
            paneUserEdit.setDisable(false);
            btnAdd.setText("Update");
            btnDelete.setVisible(true);
        });
    }

    public void btnSearchUserOnAction(ActionEvent actionEvent) {
        if (Pattern.compile("^[0-9]{9}V$").matcher(txtSearchUser.getText()).matches() || Pattern.compile("^[0-9]{9}v$").matcher(txtSearchUser.getText()).matches() || Pattern.compile("^[0-9]{12}$").matcher(txtSearchUser.getText()).matches()) {
            if (Pattern.compile("^[0-9]{9}v$").matcher(txtNIC.getText()).matches()) {
                String temp = txtNIC.getText();
                temp = temp.split("[a-z]")[1];
                temp = temp + "V";
                txtNIC.setText(temp);
            }
            loadInstructorDataToFormByNIC(txtSearchUser.getText());
            paneUserEdit.setDisable(false);
            btnAdd.setText("Update");
            btnDelete.setVisible(true);
        } else {
            txtSearchUser.setStyle("-fx-border-color: red");
            txtSearchUser.requestFocus();
        }
    }

    public void btnNewUserOnAction(ActionEvent actionEvent) {
        setDefault();
        loadLastId();
        paneUserEdit.setDisable(false);
        btnDelete.setVisible(false);
        lblNewUser.setVisible(true);
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
                                        if (Pattern.compile("^[A-z0-9]{1,}$").matcher(txtUserName.getText()).matches()) {
                                            if (cmbDesignation.getValue() != null) {
                                                //-----
                                                if (btnAdd.getText().equals("Add")) {
                                                    if (addUser()) {
                                                        new Alert(Alert.AlertType.INFORMATION, "SuccessFully Added").show();
                                                        loadLastId();
                                                        loadTable();
                                                        setDefault();
                                                    } else {
                                                        new Alert(Alert.AlertType.WARNING, "Adding Failed").show();
                                                    }
                                                } else if (btnAdd.getText().equals("Update")) {
                                                    if (updateUser()) {
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
                                                cmbDesignation.setFocusColor(Paint.valueOf("red"));
                                                cmbDesignation.requestFocus();
                                            }
                                        } else {
                                            txtUserName.setFocusColor(Paint.valueOf("red"));
                                            txtUserName.requestFocus();
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
                    txtNIC.setFocusColor(Paint.valueOf("red"));
                    txtNIC.requestFocus();
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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete " + lblUserID.getText() + " " + txtFirstName.getText() + "?", ButtonType.YES, ButtonType.NO);
            alert.setHeaderText("Do you want to delete?");
            Optional<ButtonType> buttonType = alert.showAndWait();
            ButtonType btnYes = new ButtonType("Yes");
            ButtonType btnNo = new ButtonType("No");
            if (buttonType.get() == ButtonType.YES) {
                if (bo.delete(lblUserID.getText())) {
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

    private boolean addUser() {
        try {
            String date = dpDob.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            java.sql.Date dbo = java.sql.Date.valueOf(date);
            return bo.save(new UserInfoDTO(
                    lblUserID.getText(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtNIC.getText(),
                    cmbGender.getValue(),
                    dbo,
                    txtAddress.getText(),
                    txtMobileNumber.getText(),
                    txtDrivingLicenseNo.getText(),
                    cmbDesignation.getValue(),
                    txtUserName.getText(),
                    "USER",
                    "0",
                    "0",
                    "0",
                    "",
                    "",
                    ""
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean updateUser() {
        try {
            String date = dpDob.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            java.sql.Date dbo = java.sql.Date.valueOf(date);
            return bo.updateUserBasic(new UserInfoDTO(
                    lblUserID.getText(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtNIC.getText(),
                    cmbGender.getValue(),
                    dbo,
                    txtAddress.getText(),
                    txtMobileNumber.getText(),
                    txtDrivingLicenseNo.getText(),
                    cmbDesignation.getValue(),
                    txtUserName.getText()
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void loadInstructorDataToForm(String id) {
        try {
            UserInfoDTO dto = bo.search(id);
            lblUserID.setText(dto.getUserId());
            txtFirstName.setText(dto.getFirstName());
            txtLastName.setText(dto.getLastName());
            txtNIC.setText(dto.getNic());
            txtDrivingLicenseNo.setText(dto.getDrivingLicense());
            txtAddress.setText(dto.getAddress());
            String dob = String.valueOf(dto.getDob());
            dpDob.setValue(LocalDate.parse(dob));
            cmbGender.setValue(dto.getGender());
            txtMobileNumber.setText(dto.getMobileNumber());
            cmbDesignation.setValue(dto.getDesignation());
            txtUserName.setText(dto.getUserName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadInstructorDataToFormByNIC(String nic) {
        try {
            UserInfoDTO dto = bo.searchByNIC(nic);
            lblUserID.setText(dto.getUserId());
            txtFirstName.setText(dto.getFirstName());
            txtLastName.setText(dto.getLastName());
            txtNIC.setText(dto.getNic());
            txtDrivingLicenseNo.setText(dto.getDrivingLicense());
            txtAddress.setText(dto.getAddress());
            String dob = String.valueOf(dto.getDob());
            dpDob.setValue(LocalDate.parse(dob));
            cmbGender.setValue(dto.getGender());
            txtMobileNumber.setText(dto.getMobileNumber());
            cmbDesignation.setValue(dto.getDesignation());
            txtUserName.setText(dto.getUserName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTableCols() {
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colMobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        colDesignation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
    }

    private void loadTable() {
        try {
            ArrayList<UserInfoDTO> allUsersDetails = bo.getAll();
            ObservableList<UserTM> list = FXCollections.observableArrayList();

            for (UserInfoDTO dto : allUsersDetails) {
                list.add(new UserTM(
                        dto.getUserId(),
                        dto.getFirstName(),
                        dto.getLastName(),
                        dto.getNic(),
                        dto.getAddress(),
                        dto.getMobileNumber(),
                        dto.getDesignation(),
                        dto.getUserName()
                ));
            }
            tbIUser.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadLastId() {
        try {
            String lastCode = bo.getUserLastId();
            if (null != lastCode) {
//                lastCode = lastCode.split("[A-Z]")[1];
                lastCode = "" + (Integer.parseInt(lastCode) + 001);
                lblUserID.setText(lastCode);
            } else {
                lblUserID.setText("1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadGender() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Male");
        list.add("Female");
        cmbGender.setItems(list);
    }

    private void loadDesignation() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Manager");
        list.add("Coordinator");
        list.add("InActive");
        cmbDesignation.setItems(list);
    }

    private void setDefault() {
        paneUserEdit.setDisable(true);
        txtSearchUser.clear();
        btnNewUser.setDisable(false);
        btnAdd.setText("Add");
        btnDelete.setVisible(true);
        lblUserID.setText("");
        loadLastId();
        txtFirstName.clear();
        txtLastName.clear();
        txtNIC.clear();
        txtDrivingLicenseNo.clear();
        txtAddress.clear();
        dpDob.setValue(LocalDate.now());
        loadGender();
        txtMobileNumber.clear();
        txtUserName.clear();
        loadDesignation();

        lblNewUser.setVisible(false);

        txtSearchUser.setStyle("-fx-border-color: black");
        txtFirstName.setFocusColor(Paint.valueOf("black"));
        txtLastName.setFocusColor(Paint.valueOf("black"));
        txtNIC.setFocusColor(Paint.valueOf("black"));
        txtDrivingLicenseNo.setFocusColor(Paint.valueOf("black"));
        txtAddress.setFocusColor(Paint.valueOf("black"));
        dpDob.setDefaultColor(Paint.valueOf("#2f2fff"));
        cmbGender.setFocusColor(Paint.valueOf("black"));
        txtMobileNumber.setFocusColor(Paint.valueOf("black"));
        txtUserName.setFocusColor(Paint.valueOf("black"));
        cmbDesignation.setFocusColor(Paint.valueOf("black"));
    }
}
