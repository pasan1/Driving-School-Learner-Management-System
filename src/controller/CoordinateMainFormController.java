package controller;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeInRight;
import bo.BOFactory;
import bo.custom.UserBO;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class CoordinateMainFormController {
    public AnchorPane root;
    public AnchorPane paneTitle;
    public AnchorPane paneMenu;
    public AnchorPane paneBody;
    public AnchorPane paneUser;
    public Text lblTitle;
    public ImageView btnArrowDown;
    public ImageView btnArrowUp;
    public AnchorPane paneUserOptions;
    public ImageView imgHome;
    public Label lblUserName;
    public JFXButton btnLogout;
    public AnchorPane paneUserDisplay;

    UserBO bo = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);
    public static String userId;

    public void initialize() throws IOException {
        loadDefaultForm();
        setDefaults();
        loadDateTime();
        System.out.println("userId: " + this.userId);
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void btnMinimizeOnMouseClicked(MouseEvent mouseEvent) {
        Stage window = (Stage) root.getScene().getWindow();
        window.setIconified(true);
    }

    public void btnExitOnMouseClicked(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to exit ?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("EXIT ???");
        Optional<ButtonType> buttonType = alert.showAndWait();
        ButtonType btnYes = new ButtonType("Yes");
        ButtonType btnNo = new ButtonType("No");
        if (buttonType.get() == ButtonType.YES) {
            System.exit(0);
        } else if (buttonType.get() == ButtonType.NO) {
            alert.close();
        }
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        loadUI("coordinator/CoordinateCustomerForm");
        lblTitle.setText("Shan Learner's | Learner Management System | Customer Form");
    }

    public void btnPaymentOnAction(ActionEvent actionEvent) throws IOException {
        loadUI("PaymentForm");
        lblTitle.setText("Shan Learner's | Learner Management System | Payment Form");
    }

    public void btnInstructorOnAction(ActionEvent actionEvent) throws IOException {
        loadUI("coordinator/CoordinateInstructorForm");
        lblTitle.setText("Shan Learner's | Learner Management System | Instructor Form");
    }

    public void btnVehicleOnAction(ActionEvent actionEvent) throws IOException {
        loadUI("coordinator/CoordinateVehicleForm");
        lblTitle.setText("Shan Learner's | Learner Management System | Vehicle Form");
    }

    public void btnScheduleOnAction(ActionEvent actionEvent) throws IOException {
        loadUI("ScheduleForm");
        lblTitle.setText("Shan Learner's | Learner Management System | Schedule Form");
    }

    public void btnAccountOnAction(ActionEvent actionEvent) throws IOException {
        loadUI("AccountForm");
        lblTitle.setText("Shan Learner's | Learner Management System | Account Form");
    }

    public void txtGoBackOnMouseClicked(MouseEvent mouseEvent) {
        paneUserOptions.setVisible(false);
        new FadeIn(paneUserOptions).play();
        paneMenu.setVisible(true);
        btnArrowUp.setVisible(false);
        btnArrowDown.setVisible(true);
    }

    public void btnChangePasswordOnAction(ActionEvent actionEvent) throws IOException {
        loadUI("ChangePasswordForm");
        lblTitle.setText("Shan Learner's | Learner Management System | Change Password Form");
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to logout ?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("LOGOUT ???");
        Optional<ButtonType> buttonType = alert.showAndWait();
        ButtonType btnYes = new ButtonType("Yes");
        ButtonType btnNo = new ButtonType("No");
        if (buttonType.get() == ButtonType.YES) {
            loadLogin();
        } else if (buttonType.get() == ButtonType.NO) {
            alert.close();
        }
    }

    public void btnUserNameOnMouseClicked(MouseEvent mouseEvent) {
        if (paneUserOptions.isVisible()) {
            paneUserOptions.setVisible(false);
            new FadeIn(paneUserOptions).play();
            paneMenu.setVisible(true);
            btnArrowUp.setVisible(false);
            btnArrowDown.setVisible(true);
        } else {
            paneUserOptions.setVisible(true);
            new FadeIn(paneUserOptions).play();
            paneMenu.setVisible(false);
            btnArrowUp.setVisible(true);
            btnArrowDown.setVisible(false);
        }
    }

    private void loadUI(String formName) throws IOException {

        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("../view/"+formName+".fxml"));
        paneBody.getChildren().clear();
        paneBody.getChildren().add(pane);
        new FadeInRight(pane).play();
        //paneBody.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/" + formName + ".fxml")));
    }

    public void loadLogin() throws IOException {
//        Stage window = (Stage) this.root.getScene().getWindow();
//        window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"))));
//        window.centerOnScreen();

        Parent root = FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        new FadeIn(root).setSpeed(0.7).play();
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.show();

        Stage primaryStage = (Stage) btnLogout.getScene().getWindow();
        primaryStage.close();
    }

    private void setDefaults() {
        paneUserOptions.setVisible(false);
        paneMenu.setVisible(true);
        btnArrowDown.setVisible(true);
        btnArrowUp.setVisible(false);
    }

    private void loadDateTime() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable beeper = new Runnable() {
            public void run() {
//                txtDate.setText(String.valueOf(LocalDate.now()));
//                txtTime.setText((LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
            }
        };
        final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 1, 1, TimeUnit.SECONDS);
        scheduler.schedule(new Runnable() {
            public void run() {
                try {
                    loadLogin();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                beeperHandle.cancel(true);
            }
        }, 86400, TimeUnit.SECONDS);
    }

    public void imgHomeOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        loadDefaultForm();
    }

    private void loadDefaultForm() throws IOException {
        loadUI("DefaultForm");
        lblTitle.setText("Welcome to Shan Learner's | Learner Management System");
    }
}
