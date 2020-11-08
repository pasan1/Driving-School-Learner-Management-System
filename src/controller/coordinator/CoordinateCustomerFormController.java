package controller.coordinator;

import bo.BOFactory;
import bo.custom.CustomerBO;
import com.jfoenix.controls.*;
import db.DBConnection;
import dto.CustomerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import tm.CustomerTM;

import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class CoordinateCustomerFormController {
    public AnchorPane window;
    public JFXButton btnSearchCustomer;
    public TableView<CustomerTM> tblCustomer;
    public TableColumn colCustomerID;
    public TableColumn colFirstName;
    public TableColumn colLastName;
    public TableColumn colNIC;
    public TableColumn colAddress;
    public TableColumn colMobileNumber;
    public TableColumn colDrivingLicenseNo;
    public TableColumn colTrainingFee;
    public TableColumn colNoOfDates;
    public TextField txtSearchCustomer;
    public AnchorPane paneCustomerEdit;
    public JFXTextField txtFirstName;
    public JFXTextField txtLastName;
    public JFXTextField txtNIC;
    public JFXTextField txtAddress;
    public JFXTextField txtMobileNumber;
    public JFXTextField txtDrivingLicenseNo;
    public Text lblCustomerID;
    public JFXButton btnAdd;
    public JFXButton btnDelete;
    public JFXCheckBox chbDrivingLicense;
    public JFXTextField txtTrainingFee;
    public JFXTextField txtNoOfDates;
    public JFXComboBox<String> cmbGender;
    public JFXDatePicker dpDob;
    public JFXButton btnNewCustomer;
    public Text lblCompleteAll;
    public JFXButton btnPrintAllCustomers;

    CustomerBO bo = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);

    public void initialize() {
        setDefault();
        setTableCols();
        loadLastId();
        loadTable();
        txtSearchCustomer.requestFocus();

        txtSearchCustomer.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                ArrayList<CustomerDTO> allCustomersDetails = bo.getSearchCustomerDetails(newValue);
                ObservableList<CustomerTM> list = FXCollections.observableArrayList();

                for (CustomerDTO dto : allCustomersDetails) {
                    list.add(new CustomerTM(
                            dto.getCustomerId(),
                            dto.getFirstName(),
                            dto.getLastName(),
                            dto.getNic(),
                            dto.getAddress(),
                            dto.getMobileNumber(),
                            dto.getDrivingLicense(),
                            String.valueOf(dto.getAmount()),
                            String.valueOf(dto.getDates())
                    ));
                }
                tblCustomer.setItems(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setDefault();
            if (null != newValue)
                loadCustomerDataToForm(newValue.getCustomerId());
            paneCustomerEdit.setDisable(false);
            btnAdd.setText("Update");
            btnDelete.setVisible(true);
        });
    }

    public void btnSearchCustomerOnAction(ActionEvent actionEvent) {
        if (Pattern.compile("^[0-9]{9}V$").matcher(txtSearchCustomer.getText()).matches() || Pattern.compile("^[0-9]{9}v$").matcher(txtSearchCustomer.getText()).matches() || Pattern.compile("^[0-9]{12}$").matcher(txtSearchCustomer.getText()).matches()) {
            if (Pattern.compile("^[0-9]{9}v$").matcher(txtNIC.getText()).matches()) {
                String temp = txtNIC.getText();
                temp = temp.split("[a-z]")[1];
                temp = temp + "V";
                txtNIC.setText(temp);
            }
            loadCustomerDataToFormByNumber(txtSearchCustomer.getText());
            paneCustomerEdit.setDisable(false);
            btnAdd.setText("Update");
            btnDelete.setVisible(true);
        } else {
            txtSearchCustomer.setStyle("-fx-border-color: red");
            txtSearchCustomer.requestFocus();
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
                                        if (Pattern.compile("^[0-9]{2,}$").matcher(txtTrainingFee.getText()).matches()) {
                                            if (Pattern.compile("^[0-9]{1,2}$").matcher(txtNoOfDates.getText()).matches()) {
                                                //-----
                                                if (btnAdd.getText().equals("Add")) {
                                                    if (addCustomer()) {
                                                        new Alert(Alert.AlertType.INFORMATION, "SuccessFully Added").show();
                                                        loadLastId();
                                                        loadTable();
                                                        setDefault();
                                                    } else {
                                                        new Alert(Alert.AlertType.WARNING, "Adding Failed").show();
                                                    }
                                                } else if (btnAdd.getText().equals("Update")) {
                                                    if (updateCustomer()) {
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
                                                txtNoOfDates.setFocusColor(Paint.valueOf("red"));
                                                txtNoOfDates.requestFocus();
                                            }
                                        } else {
                                            txtTrainingFee.setFocusColor(Paint.valueOf("red"));
                                            txtTrainingFee.requestFocus();
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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete " + lblCustomerID.getText() + " " + txtFirstName.getText() + "?", ButtonType.YES, ButtonType.NO);
            alert.setHeaderText("Do you want to delete?");
            Optional<ButtonType> buttonType = alert.showAndWait();
            ButtonType btnYes = new ButtonType("Yes");
            ButtonType btnNo = new ButtonType("No");
            if (buttonType.get() == ButtonType.YES) {
                if (bo.delete(lblCustomerID.getText())) {
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

    public void btnNewCustomerOnAction(ActionEvent actionEvent) {
        setDefault();
        loadLastId();
        paneCustomerEdit.setDisable(false);
        btnDelete.setVisible(false);
        loadGender();
    }

    private void loadCustomerDataToForm(String id) {
        try {
            CustomerDTO dto = bo.search(id);
            lblCustomerID.setText(dto.getCustomerId());
            txtFirstName.setText(dto.getFirstName());
            txtLastName.setText(dto.getLastName());
            txtNIC.setText(dto.getNic());
            txtDrivingLicenseNo.setText(dto.getDrivingLicense());
            txtAddress.setText(dto.getAddress());
            String dob = String.valueOf(dto.getDob());
            dpDob.setValue(LocalDate.parse(dob));
            cmbGender.setValue(dto.getGender());
            txtMobileNumber.setText(dto.getMobileNumber());
            txtTrainingFee.setText(String.valueOf(dto.getAmount()));
            txtNoOfDates.setText(String.valueOf(dto.getDates()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCustomerDataToFormByNumber(String nic) {
        try {
            CustomerDTO dto = bo.searchByNIC(nic);
            lblCustomerID.setText(dto.getCustomerId());
            txtFirstName.setText(dto.getFirstName());
            txtLastName.setText(dto.getLastName());
            txtNIC.setText(dto.getNic());
            txtDrivingLicenseNo.setText(dto.getDrivingLicense());
            txtAddress.setText(dto.getAddress());
            String dob = String.valueOf(dto.getDob());
            dpDob.setValue(LocalDate.parse(dob));
            cmbGender.setValue(dto.getGender());
            txtMobileNumber.setText(dto.getMobileNumber());
            txtTrainingFee.setText(String.valueOf(dto.getAmount()));
            txtNoOfDates.setText(String.valueOf(dto.getDates()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean addCustomer() {
        try {
            String date = dpDob.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            java.sql.Date dbo = java.sql.Date.valueOf(date);
            return bo.save(new CustomerDTO(
                    lblCustomerID.getText(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtNIC.getText(),
                    cmbGender.getValue(),
                    dbo,
                    txtAddress.getText(),
                    txtMobileNumber.getText(),
                    txtDrivingLicenseNo.getText(),
                    Double.parseDouble(txtTrainingFee.getText()),
                    0.0,
                    Integer.parseInt(txtNoOfDates.getText()),
                    0
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean updateCustomer() {
        try {
            String date = dpDob.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            java.sql.Date dbo = java.sql.Date.valueOf(date);
            return bo.update(new CustomerDTO(
                    lblCustomerID.getText(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtNIC.getText(),
                    cmbGender.getValue(),
                    dbo,
                    txtAddress.getText(),
                    txtMobileNumber.getText(),
                    txtDrivingLicenseNo.getText(),
                    Double.parseDouble(txtTrainingFee.getText()),
                    bo.getBalance(lblCustomerID.getText()),
                    Integer.parseInt(txtNoOfDates.getText()),
                    bo.getRemainingDates(lblCustomerID.getText())
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void setTableCols() {
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colMobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        colDrivingLicenseNo.setCellValueFactory(new PropertyValueFactory<>("drivingLicense"));
        colTrainingFee.setCellValueFactory(new PropertyValueFactory<>("trainingFee"));
        colNoOfDates.setCellValueFactory(new PropertyValueFactory<>("noOfDates"));
    }

    private void loadTable() {
        try {
            ArrayList<CustomerDTO> allCustomersDetails = bo.getAll();
            ObservableList<CustomerTM> list = FXCollections.observableArrayList();

            for (CustomerDTO dto : allCustomersDetails) {
                list.add(new CustomerTM(
                        dto.getCustomerId(),
                        dto.getFirstName(),
                        dto.getLastName(),
                        dto.getNic(),
                        dto.getAddress(),
                        dto.getMobileNumber(),
                        dto.getDrivingLicense(),
                        String.valueOf(dto.getAmount()),
                        String.valueOf(dto.getDates())
                ));
            }
            tblCustomer.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadLastId() {
        try {
            String lastCode = bo.getCustomerLastId();
            if (null != lastCode) {
//                lastCode = lastCode.split("[A-Z]")[1];
                lastCode = "" + (Integer.parseInt(lastCode) + 001);
                lblCustomerID.setText(lastCode);
            } else {
                lblCustomerID.setText("1");
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

    public void chbDrivingLicenseOnAction(ActionEvent actionEvent) {
        if (chbDrivingLicense.isSelected()) {
            txtDrivingLicenseNo.setText("No");
            txtDrivingLicenseNo.setDisable(true);
            txtAddress.requestFocus();
        } else {
            txtDrivingLicenseNo.setText("");
            txtDrivingLicenseNo.setDisable(false);
            txtDrivingLicenseNo.requestFocus();
        }
    }

    private void setDefault() {
        paneCustomerEdit.setDisable(true);
        btnSearchCustomer.setVisible(true);
        btnSearchCustomer.setDisable(false);
        txtSearchCustomer.setDisable(false);
        txtSearchCustomer.clear();
        txtSearchCustomer.setStyle("-fx-border-color: black");
        btnNewCustomer.setDisable(false);
        btnAdd.setText("Add");
        lblCompleteAll.setVisible(false);
        btnDelete.setVisible(true);
        lblCustomerID.setText("");
        txtFirstName.clear();
        txtLastName.clear();
        txtNIC.clear();
        txtDrivingLicenseNo.clear();
        txtAddress.clear();
        dpDob.setValue(LocalDate.now());
        loadGender();
        txtMobileNumber.clear();
        txtTrainingFee.clear();
        txtNoOfDates.clear();

        txtFirstName.setFocusColor(Paint.valueOf("black"));
        txtLastName.setFocusColor(Paint.valueOf("black"));
        txtNIC.setFocusColor(Paint.valueOf("black"));
        txtDrivingLicenseNo.setFocusColor(Paint.valueOf("black"));
        txtAddress.setFocusColor(Paint.valueOf("black"));
        dpDob.setDefaultColor(Paint.valueOf("#2f2fff"));
        cmbGender.setFocusColor(Paint.valueOf("black"));
        txtMobileNumber.setFocusColor(Paint.valueOf("black"));
        txtTrainingFee.setFocusColor(Paint.valueOf("black"));
        txtNoOfDates.setFocusColor(Paint.valueOf("black"));
    }

    public void btnPrintAllCustomersOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/report/Customer.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(is);
            JasperPrint jp = JasperFillManager.fillReport(jr,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp,false);
//            JasperPrintManager.printReport(jp,true);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
