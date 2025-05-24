package com.example.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddressManagement {

    @FXML
    private TextField nameField, surnameField, addressField;

    @FXML
    private ListView<Address> listView;

    private ObservableList<Address> addressList = FXCollections.observableArrayList();
    private final File file = new File("addresses.json");
    private final ObjectMapper mapper = new ObjectMapper();

    @FXML
    public void initialize() {
        // JSON dosyasından oku
        if (file.exists()) {
            try {
                Address[] addresses = mapper.readValue(file, Address[].class);
                addressList.addAll(Arrays.asList(addresses));
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Address file could not be read."

                );
            }
        }

        listView.setItems(addressList);
        listView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Address item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.toString());
            }
        });
    }

    @FXML
    private void handleAdd() {
        String name = nameField.getText().trim();
        String surname = surnameField.getText().trim();
        String address = addressField.getText().trim();

        if (name.isEmpty() || surname.isEmpty() || address.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please Enter All Areas.");
            return;
        }

        Address newAddress = new Address(name, surname, address);
        addressList.add(newAddress);
        saveToFile();

        nameField.clear();
        surnameField.clear();
        addressField.clear();
    }

    @FXML
    private void handleDelete() {
        Address selected = listView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Choose an Address for delete.");
            return;
        }

        addressList.remove(selected);
        saveToFile();
    }

    @FXML
    private void handleEdit() {
        Address selected = listView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Choose an address to edit.");
            return;
        }

        String name = nameField.getText().trim();
        String surname = surnameField.getText().trim();
        String address = addressField.getText().trim();

        if (name.isEmpty() || surname.isEmpty() || address.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please enter all fields.");
            return;
        }

        // Mevcut öğeyi güncelle
        selected.setName(name);
        selected.setSurname(surname);
        selected.setAddress(address);

        listView.refresh();  // ListView'i güncelle
        saveToFile();        // Dosyaya yaz

        // Alanları temizle
        nameField.clear();
        surnameField.clear();
        addressField.clear();
    }







    private void saveToFile() {
        try {
            // ObservableList'i ArrayList'e dönüştürüp kaydet
            mapper.writeValue(file, new ArrayList<>(addressList));
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "The Address file could not be saved.!");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
