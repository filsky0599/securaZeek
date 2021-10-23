package com.example.securazeek.controller;

import com.example.securazeek.SecuraZeek;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    void openNumberConnectionsWindowClick(MouseEvent event) throws IOException {
        if(event.getButton() == MouseButton.PRIMARY){
            openNewNumberConnections();
        }
    }

    @FXML
    void openNumberConnectionsWindowPress(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER){
            openNewNumberConnections();
        }
    }

    @FXML
    void openDNSWindowClick(MouseEvent event) throws IOException {
        if(event.getButton() == MouseButton.PRIMARY){
            openNewDNS();
        }
    }

    @FXML
    void openDNSWindowPress(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER){
            openNewDNS();
        }
    }

    @FXML
    void openBeaconsClick(MouseEvent event) throws IOException {
        if(event.getButton() == MouseButton.PRIMARY){
            openNewBeacons();
        }
    }

    @FXML
    void openBeaconsPress(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER){
            openNewBeacons();
        }
    }

    @FXML
    void openLongestConnectionsClick(MouseEvent event) throws IOException {
        if(event.getButton() == MouseButton.PRIMARY){
            openNewLongestConnections();
        }
    }

    @FXML
    void openLongestConnectionsPress(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER){
            openNewLongestConnections();
        }
    }

    @FXML
    void openConnectionsPress(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER){
            openNewAbsConnections();
        }
    }

    @FXML
    void openConnectionsClick(MouseEvent event) throws IOException {
        if(event.getButton() == MouseButton.PRIMARY){
            openNewAbsConnections();
        }
    }

    @FXML
    void openFileInformationClick(MouseEvent event) throws IOException {
        if(event.getButton() == MouseButton.PRIMARY){
            openNewFileInformation();
        }
    }

    @FXML
    void openFileInformationPress(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER){
            openNewFileInformation();
        }
    }

    private void openNewFileInformation() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SecuraZeek.class.getResource("fileInformationWindow.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("File Information");
        stage.initModality(Modality.NONE);
        //stage.initOwner(FileInformation.getScene().getWindow());
        stage.show();
    }

    private void openNewAbsConnections() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SecuraZeek.class.getResource("absLongConnectionsWindow.fxml"));
        Parent root2 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root2));
        stage.setTitle("Absolute Connections Analysis");
        stage.initModality(Modality.NONE);
        stage.setResizable(false);
        stage.show();
    }

    private void openNewLongestConnections() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SecuraZeek.class.getResource("longestConnectionsWindow.fxml"));
        Parent root3 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root3));
        stage.setTitle("Longest Connections Analysis");
        stage.initModality(Modality.NONE);
        stage.setResizable(false);
        stage.show();
    }

    private void openNewBeacons() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SecuraZeek.class.getResource("amountOfDataBeacons.fxml"));
        Parent root4 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root4));
        stage.setTitle("Amount Of Data");
        stage.initModality(Modality.NONE);
        stage.setResizable(false);
        stage.show();
    }

    private void openNewDNS() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SecuraZeek.class.getResource("numberOfHosts.fxml"));
        Parent root5 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root5));
        stage.setTitle("DNS Analysis");
        stage.initModality(Modality.NONE);
        stage.setResizable(false);
        stage.show();
    }

    private void openNewNumberConnections() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SecuraZeek.class.getResource("numberOfConnectionsBeacons.fxml"));
        Parent root6 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root6));
        stage.setTitle("Number Of Connections");
        stage.initModality(Modality.NONE);
        stage.setResizable(false);
        stage.show();
    }

}
