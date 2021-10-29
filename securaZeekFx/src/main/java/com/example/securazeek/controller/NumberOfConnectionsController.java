package com.example.securazeek.controller;

import com.example.securazeek.exceptions.NotValidInsertion;
import com.example.securazeek.exceptions.ReadFileException;
import com.example.securazeek.exceptions.TooManyConnections;
import com.example.securazeek.exceptions.WrongFileChosen;
import com.example.securazeek.functionalities.beacons.ManagingNumberOfConnections;
import com.example.securazeek.objConnection.ObjNumberOfConnections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;

public class NumberOfConnectionsController {

    private ManagingNumberOfConnections managingNumberOfConnections;

    @FXML
    private TextField displayNumberOfConnections;

    @FXML
    private Button loadFile;

    @FXML
    private Label loadFileError;

    @FXML
    private Button displayTable;

    @FXML
    private Slider numberToDisplay;

    @FXML
    private TableView<ObjNumberOfConnections> connectionsTable;

    @FXML
    private TableColumn<ObjNumberOfConnections, String> destinationIPColumn;

    @FXML
    private TableColumn<ObjNumberOfConnections, Integer> numberConnectionsColumn;

    @FXML
    private TableColumn<ObjNumberOfConnections, String> sourceIpColumn;

    @FXML
    private PieChart sourceIpChart;

    @FXML
    private Label graphInformationLabel;

    @FXML
    private Button displayByIp;

    @FXML
    private TextField sourceIpText;

    @FXML
    private TextField destinationIpText;

    @FXML
    private Button displayByNumber;

    @FXML
    private TextField numberText;

    @FXML
    private CheckBox reverseNumberFilter;

    @FXML
    private CheckBox reverseDisplayIpFilter;

    public NumberOfConnectionsController() {
        this.managingNumberOfConnections = new ManagingNumberOfConnections();
    }

    @FXML
    void loadFileClick(MouseEvent event) throws WrongFileChosen, FileNotFoundException, ReadFileException {
        if(event.getButton() == MouseButton.PRIMARY){
            lFile();
            unlockButtons();
            setUpChart();
        }
    }

    @FXML
    void loadFilePress(KeyEvent event) throws WrongFileChosen, FileNotFoundException, ReadFileException {
        if(event.getCode() == KeyCode.ENTER){
            lFile();
            unlockButtons();
            setUpChart();
        }
    }

    @FXML
    void displayClick(MouseEvent event) throws TooManyConnections {
        if(event.getButton() == MouseButton.PRIMARY){
            initializeTable();
            display();
        }
    }

    @FXML
    void displayPressed(KeyEvent event) throws TooManyConnections {
        if(event.getCode() == KeyCode.ENTER){
            initializeTable();
            display();
        }
    }

    @FXML
    void displayIpClick(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY){
            initializeTable();
            displayIp();
        }
    }

    @FXML
    void displayIpPress(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            initializeTable();
            displayIp();
        }
    }

    @FXML
    void displayNumberClick(MouseEvent event) throws NotValidInsertion {
        if(event.getButton() == MouseButton.PRIMARY){
            initializeTable();
            displayNumber();
        }
    }

    @FXML
    void displayNumberPress(KeyEvent event) throws NotValidInsertion {
        if(event.getCode() == KeyCode.ENTER){
            initializeTable();
            displayNumber();
        }
    }

    private void displayNumber() throws NotValidInsertion {
        boolean filterStatus = reverseNumberFilter.isSelected();
        int numberConnections = Integer.parseInt(numberText.getText());
        managingNumberOfConnections.displayConnectionsByNumber(numberConnections, filterStatus);
        connectionsTable.setItems(managingNumberOfConnections.getObsListByNumberConnections());
    }

    private void displayIp() {
        boolean filterStatus = reverseDisplayIpFilter.isSelected();
        String sourceIp = sourceIpText.getText();
        String destinationIp = destinationIpText.getText();
        if(filterStatus){
            managingNumberOfConnections.displayConnectionsByIpReverse(sourceIp, destinationIp);
        }else {
            managingNumberOfConnections.displayConnectionsByIp(sourceIp, destinationIp);
        }
        connectionsTable.setItems(managingNumberOfConnections.getObsListByIP());
    }

    private void setUpChart(){
        managingNumberOfConnections.countIp();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        managingNumberOfConnections.getCountIpMap().forEach((key, value) -> pieChartData.add(new PieChart.Data(key, value)));
        sourceIpChart.setData(pieChartData);
    }

    private void display() throws TooManyConnections {
        managingNumberOfConnections.displayAllConnections((int) numberToDisplay.getValue());
        connectionsTable.setItems(managingNumberOfConnections.getObsListDisplay());
    }

    private void initializeTable() {
        connectionsTable.getItems().clear();
        numberConnectionsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfConnections"));
        sourceIpColumn.setCellValueFactory(new PropertyValueFactory<>("sourceIp"));
        destinationIPColumn.setCellValueFactory(new PropertyValueFactory<>("destinationIp"));
    }

    private void unlockButtons(){
        displayTable.setDisable(false);
        displayByIp.setDisable(false);
        displayByNumber.setDisable(false);
        reverseNumberFilter.setDisable(false);
        reverseDisplayIpFilter.setDisable(false);
    }

    private void lFile() throws WrongFileChosen, FileNotFoundException, ReadFileException {
        String path;
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("NumberOfConnections files", "*.txt"));
        File file = fileChooser.showOpenDialog(null);
        if(file != null && file.getName().equals("NumberOfConnections.txt")){
            path = file.getAbsolutePath();
            loadFileError.setText("File loading complete");
            loadFileError.setTextFill(Color.GREEN);
            loadFileError.setVisible(true);
            loadFile.setDisable(true);
            graphInformationLabel.setVisible(false);
        }else{
            loadFileError.setText("Error in loading process");
            loadFileError.setTextFill(Color.RED);
            loadFileError.setVisible(true);
            throw new WrongFileChosen();
        }

        managingNumberOfConnections.openFile(path);
        int numberOfConnections = managingNumberOfConnections.getLoadNumberOfConnections().getObjNumberOfConnections().size();
        displayNumberOfConnections.setText(String.valueOf(numberOfConnections));
        numberToDisplay.setMax(managingNumberOfConnections.getLoadNumberOfConnections().getObjNumberOfConnections().size());
    }
}
