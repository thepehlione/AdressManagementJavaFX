package com.example.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.Node;
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



public class RegisterController {



    @FXML
    private TextField emailarea;
    @FXML
    private PasswordField passwordarea;

    @FXML
    private void handleRegister() {

        String email = (emailarea.getText()).trim();
        String password = passwordarea.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in both email and password.");
            alert.showAndWait();
            return;  // return bu kodda kayıt işlemini durdurur.
        }



        User newUser = new User( email, password);

        ObjectMapper mapper = new ObjectMapper();
        File file = new File("users.json");

        List<User> userList = new ArrayList<>();


        // JSON dosyasına kaydetme
        try {
            // Daha önce kayıt yapılmışsa, eski kayıtları oku
            if (file.exists()) {
                // Liste halinde oku
                userList = Arrays.asList(mapper.readValue(file, User[].class));
                // Listeyi yeniden yazılabilir hale getir
                userList = new ArrayList<>(userList);
            }

            // Yeni kullanıcıyı listeye ekle
            userList.add(newUser);

            // Listeyi JSON dosyasına yeniden yaz
            mapper.writeValue(file, userList);

            // Bilgilendirme Kısmı
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration successful!");
            alert.setHeaderText(null);
            alert.setContentText("The user registered successful.");
            alert.showAndWait();

            Stage stage = (Stage) emailarea.getScene().getWindow();  //  scene üzerinden pencereye erişiyor.
            stage.close();  // Pencereyi kapatmak için


        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("There was an error during registration.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

}





}
