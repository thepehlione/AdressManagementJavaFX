package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {



    @FXML
    private Button registerbtn;

    @FXML
    protected  void  myfunct(){

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
            Parent root = fxmlLoader.load();

            // Yeni bir pencere oluştur
            Stage stage = new Stage();

            stage.setTitle("REGISTER WINDOW");
            Scene scene = new Scene(root,700,550);
            stage.setScene(scene);
            stage.show();






        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 @FXML
    private Button loginbtn;


    @FXML
    protected void loginfunct() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = fxmlLoader.load();

            // Controller'a eriş
            LoginController controller = fxmlLoader.getController();

            // Şu anki pencereyi alıyoruz
            Stage mainStage = (Stage) loginbtn.getScene().getWindow();

            // Ana pencereyi login controller'a gönder
            controller.setMainStage(mainStage);

            // Yeni bir pencere oluştur
            Stage loginStage = new Stage();
            loginStage.setTitle("LOGIN WINDOW");

            Scene scene = new Scene(root,700,550);
            loginStage.setScene(scene);
            loginStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }














    }










