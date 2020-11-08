package controller;

import bo.BOFactory;
import bo.custom.PaymentBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import dto.CustomerDTO;
import dto.PaymentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class PaymentFormController {
    public AnchorPane window;
    public Text lblIPaymentID;
    public JFXTextField txtCustomerName;
    public JFXComboBox<String> cmbCustomerNic;
    public JFXTextField txtCustomerNic;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerMobileNumber;
    public TextField txtProgramFee;
    public TextField txtPayingAmount;
    public TextField txtBalanceProgramFee;
    public JFXComboBox<String> cmbPaymentMethod;
    public TextField txtAmount;
    public TextField txtBalance;
    public JFXButton btnPay;
    public TextField txtDate;
    public TextField txtTime;
    public Text txtAlertFullyPaid;
    public AnchorPane panePay;

    PaymentBO bo = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PAYMENT);
    private CustomerDTO dto;

    public void initialize() {
        setDefaults();
        loadDateTime();
        loadCustomerNICsToComboBox();
        cmbCustomerNic.requestFocus();
    }

    public void cmbCustomerNicOnAction(ActionEvent actionEvent) {
        setDefaults();
        try {
            dto = bo.searchCustomerByNIC(cmbCustomerNic.getValue());
            txtCustomerName.setText(dto.getFirstName() + " " + dto.getLastName());
            txtCustomerNic.setText(dto.getNic());
            txtCustomerAddress.setText(dto.getAddress());
            txtCustomerMobileNumber.setText(dto.getMobileNumber());
            txtProgramFee.setText(String.valueOf(dto.getAmount() - dto.getBalance()));
            if (dto.getAmount() <= dto.getBalance()) {
                txtAlertFullyPaid.setVisible(true);
                panePay.setVisible(true);
            } else {
                panePay.setVisible(false);
                txtPayingAmount.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void txtPayingAmountOnAction(ActionEvent actionEvent) {
        if (Pattern.compile("^[0-9]{2,}$").matcher(txtPayingAmount.getText()).matches()) {
            txtBalanceProgramFee.setText(String.valueOf(Double.parseDouble(txtProgramFee.getText()) - Double.parseDouble(txtPayingAmount.getText())));
            cmbPaymentMethod.requestFocus();
        } else {
            txtPayingAmount.setStyle("-fx-border-color: red");
            txtPayingAmount.requestFocus();
        }
    }

    public void cmbPaymentMethodOnAction(ActionEvent actionEvent) {
        txtAmount.requestFocus();
    }

    public void txtAmountOnAction(ActionEvent actionEvent) {
        if (Pattern.compile("^[0-9]{2,}$").matcher(txtAmount.getText()).matches()) {
            if (Double.parseDouble(txtAmount.getText()) >= Double.parseDouble(txtPayingAmount.getText())) {
                txtBalance.setText(String.valueOf(Double.parseDouble(txtAmount.getText()) - Double.parseDouble(txtPayingAmount.getText())));
                btnPay.setDisable(false);
                btnPay.requestFocus();
            } else {
                txtAmount.setStyle("-fx-border-color: red");
                txtAmount.requestFocus();
            }
        } else {
            txtAmount.setStyle("-fx-border-color: red");
            txtAmount.requestFocus();
        }
    }

    public void btnPayOnAction(ActionEvent actionEvent) {
        doPayment();
    }

    public void btnPayOnKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            doPayment();
        }
    }

    private void doPayment() {
        if (savePayment()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to print bill?", ButtonType.YES, ButtonType.NO);
            alert.setHeaderText("Do you want to Print?");
            Optional<ButtonType> buttonType = alert.showAndWait();
            ButtonType btnYes = new ButtonType("Yes");
            ButtonType btnNo = new ButtonType("No");
            if (buttonType.get() == ButtonType.YES) {
                printBill();
            } else if (buttonType.get() == ButtonType.NO) {
                alert.close();
            }
            setDefaults();
        } else {
            new Alert(Alert.AlertType.WARNING, "Payment Failed").show();
        }
    }

    private void printBill() {
        try {
            InputStream is = this.getClass().getResourceAsStream("/report/PaymentBill.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(is);

            String s = "Rs. ";

            HashMap hs = new HashMap();
            hs.put("PaymentID", lblIPaymentID.getText());
            hs.put("CustomerName", txtCustomerName.getText());
            hs.put("CustomerNIC", txtCustomerNic.getText());
            hs.put("CustomerAddress", txtCustomerAddress.getText());
            hs.put("ProgramFee", s+String.valueOf(dto.getAmount()));
            hs.put("ProgramBalanceToBePaid", s+txtProgramFee.getText());
            hs.put("PayingAmount", s+txtPayingAmount.getText());
            hs.put("CashAmount", s+txtAmount.getText());
            hs.put("CashMethod", cmbPaymentMethod.getValue());
            hs.put("CashBalance", s+txtBalance.getText());

            JasperPrint jp = JasperFillManager.fillReport(jr, hs, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp, false);
//            JasperPrintManager.printReport(jp,true);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean savePayment() {
        try {
            return bo.save(new PaymentDTO(
                    lblIPaymentID.getText(),
                    dto.getCustomerId(),
                    java.sql.Date.valueOf(txtDate.getText()),
                    txtTime.getText(),
                    Double.parseDouble(txtPayingAmount.getText()),
                    dto.getFirstName(),
                    dto.getLastName(),
                    dto.getNic(),
                    dto.getAddress(),
                    dto.getMobileNumber(),
                    dto.getAmount(),
                    Double.parseDouble(txtPayingAmount.getText())
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void loadCustomerNICsToComboBox() {
        try {
            ObservableList<String> list = FXCollections.observableArrayList();
            ArrayList<CustomerDTO> allCustomers = bo.getAllCustomers();
            for (CustomerDTO dto : allCustomers) {
                list.add(dto.getNic());
            }
            cmbCustomerNic.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPaymentMethod() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("CASH");
        list.add("CARD");
        cmbPaymentMethod.setItems(list);
    }

    private void setDefaults() {
        lblIPaymentID.setText("");
        loadLastId();
        txtCustomerName.clear();
        txtCustomerNic.clear();
        txtCustomerAddress.clear();
        txtCustomerMobileNumber.clear();
        txtProgramFee.clear();
        txtAlertFullyPaid.setVisible(false);
        txtPayingAmount.clear();
        txtBalanceProgramFee.clear();
        loadPaymentMethod();
        txtAmount.clear();
        txtBalance.clear();
        btnPay.setDisable(true);
        txtPayingAmount.setStyle("-fx-border-color: black");
        txtAmount.setStyle("-fx-border-color: black");
    }

    private void loadDateTime() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable beeper = new Runnable() {
            public void run() {
                txtDate.setText(String.valueOf(LocalDate.now()));
                txtTime.setText((LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
            }
        };
        final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 1, 1, TimeUnit.SECONDS);
        scheduler.schedule(new Runnable() {
            public void run() {
                try {
                    new ManageMainFormController().loadLogin();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                beeperHandle.cancel(true);
            }
        }, 86400, TimeUnit.SECONDS);
    }

    private void loadLastId() {
        try {
            String lastCode = bo.getPaymentLastId();
            if (null != lastCode) {
//                lastCode = lastCode.split("[A-Z]")[1];
                lastCode = "" + (Integer.parseInt(lastCode) + 001);
                lblIPaymentID.setText(lastCode);
            } else {
                lblIPaymentID.setText("1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
