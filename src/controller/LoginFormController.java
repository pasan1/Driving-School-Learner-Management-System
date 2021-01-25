package controller;

import animatefx.animation.FadeIn;
import bo.BOFactory;
import bo.custom.UserBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class LoginFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public Text lblError;
    public Text lblForgotPassword;
    public JFXButton btnLogin;
    public AnchorPane pane;

    UserBO bo = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);


    public void initialize() {
        lblError.setVisible(false);
        txtUserName.requestFocus();
    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        requestToLoad();
    }

    private void requestToLoad() throws IOException {
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        if (null == userName && null == password) {
            lblError.setVisible(true);
            txtUserName.setFocusColor(Paint.valueOf("red"));
            txtPassword.setFocusColor(Paint.valueOf("red"));
            txtUserName.requestFocus();
        } else {
            try {
                if (txtPassword.getText().equals(bo.getPasswordFromUserName(userName))) {
                    if (txtPassword.getText().equals(("USER"))) {
                        loadSignUpForm();
                    } else {
                        bo.setUserId(txtUserName.getText());
                        new AccountFormController().setUserId(bo.getUserId(userName));
                        new ManageMainFormController().setUserId(bo.getUserId(userName));
                        switch (bo.getDesignation(userName)) {
                            case "Manager":
                                loadManagerForm();
                                break;
                            case "Coordinator":
                                loadCoordinatorForm();
                                break;
                            case "InActive":
                                loadInActiveForm();
                                break;
                        }
                    }
                } else {
                    lblError.setVisible(true);
                    txtUserName.setFocusColor(Paint.valueOf("red"));
                    txtPassword.setFocusColor(Paint.valueOf("red"));
                    txtUserName.requestFocus();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void loadSignUpForm() throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("../view/SignUpForm.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        new FadeIn(root).setSpeed(0.7).play();
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.show();

        Stage primaryStage = (Stage) btnLogin.getScene().getWindow();
        primaryStage.close();
    }

    private void loadInActiveForm() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Your Account was in Inactive mode. Please contact Manager. \nIf Do you want to exit?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("Inactive user account");
        Optional<ButtonType> buttonType = alert.showAndWait();
        ButtonType btnYes = new ButtonType("Yes");
        ButtonType btnNo = new ButtonType("No");
        if (buttonType.get() == ButtonType.YES) {
            System.exit(0);
        } else if (buttonType.get() == ButtonType.NO) {
            alert.close();
        }
    }

    public void btnExitOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void lblForgotPasswordOnMouseClicked(MouseEvent mouseEvent) {
    }

    public void txtUserNameOnAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) {
        btnLogin.requestFocus();
    }


    public void btnLoginOnKeyPressed(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            requestToLoad();
        }
    }

    public void btnExitOnKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            System.exit(0);
        }
    }

    private void loadManagerForm() throws IOException {
        //Stage window = (Stage) this.pane.getScene().getWindow();
        //window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/ManageMainForm.fxml"))));

        File f = new File("../view/ManageMainForm.fxml");
        System.out.println(f.getAbsolutePath());
        Parent root = FXMLLoader.load(this.getClass().getResource("../view/ManageMainForm.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        new FadeIn(root).setSpeed(0.7).play();
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.show();

        Stage primaryStage = (Stage) btnLogin.getScene().getWindow();
        primaryStage.close();
        //window.centerOnScreen();
    }

    private void loadCoordinatorForm() throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("../view/CoordinateMainForm.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        new FadeIn(root).setSpeed(0.7).play();
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.show();

        Stage primaryStage = (Stage) btnLogin.getScene().getWindow();
        primaryStage.close();
    }

    public void btnMinimizeOnMouseClicked(MouseEvent mouseEvent) {
        Stage window = (Stage) pane.getScene().getWindow();
        window.setIconified(true);
    }
}
