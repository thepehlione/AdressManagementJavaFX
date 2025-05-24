package com.example.project;


import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.Arrays;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoginController {

    @FXML
    private TextField loginEmail;
    @FXML
    private PasswordField loginPassword;

    private Stage mainStage; // Ana pencere

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }





    @FXML
    private void startLogin(){

        String email = loginEmail.getText();
        String password = loginPassword.getText();


        ObjectMapper mapper = new ObjectMapper();
        File file = new File("users.json");

        if (!file.exists()) {
            showAlert(Alert.AlertType.ERROR, "There is a problem with your login", "The user doesn't exist");
            return;
        }

        try {
            List<User> userList = Arrays.asList(mapper.readValue(file, User[].class));

            boolean loginSuccess = false;

            for (User user : userList) {
                if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                    loginSuccess = true;
                    break;
                }
            }

            if (loginSuccess) {
                showAlert(Alert.AlertType.INFORMATION, "Login is Successful", "Welcome our dear user, " + email + "!");

                // Giriş başarılıysa pencereyi kapat
                Stage stage = (Stage) loginEmail.getScene().getWindow();
                stage.close();

                if (mainStage != null) {
                    mainStage.close();
                }




                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("address-management.fxml"));
                    Parent root = fxmlLoader.load();


                    Stage addressStage = new Stage();
                    addressStage.setTitle("Address Management");

                    Scene scene = new Scene(root,700,550);
                    addressStage.setScene(scene);
                    addressStage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                    showAlert(Alert.AlertType.ERROR, "There is Error", "Address management screen not opened due to an error");
                }




            } else {
                showAlert(Alert.AlertType.ERROR, "Login Error", "Email or password incorrect.");
            }

            Stage stage = (Stage) loginEmail.getScene().getWindow();  //  scene üzerinden pencereye erişime.
            stage.close();  // Pencereyi kapat.


        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "File Error", "File read error ");
        }











    }



    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }








}
