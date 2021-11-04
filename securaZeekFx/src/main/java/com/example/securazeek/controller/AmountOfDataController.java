package com.example.securazeek.controller;

import com.example.securazeek.exceptions.NotValidInsertion;
import com.example.securazeek.exceptions.TooManyConnections;
import com.example.securazeek.exceptions.WrongFileChosen;
import com.example.securazeek.functionalities.beacons.ManagingAmountOfData;
import com.example.securazeek.objModel.ObjAmountOfData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class AmountOfDataController {

    private ManagingAmountOfData managingAmountOfData;

    @FXML
    private Button loadFile;

    @FXML
    private TableView<ObjAmountOfData> connectionsTable;

    @FXML
    private TableColumn<ObjAmountOfData, Integer> numberConnectionsColumn;

    @FXML
    private TableColumn<ObjAmountOfData, String> sourceIpColumn;

    @FXML
    private TableColumn<ObjAmountOfData, String> destinationIpColumn;

    @FXML
    private TableColumn<ObjAmountOfData, Double> amountDataColumn;

    @FXML
    private TextField displayNumberOfConnections;

    @FXML
    private Label loadFileError;

    @FXML
    private Slider numberToDisplay;

    @FXML
    private Button displayTable;

    @FXML
    private TextField sourceIpText;

    @FXML
    private TextField destinationIpText;

    @FXML
    private Button displayByIp;

    @FXML
    private TextField dataText;

    @FXML
    private Button displayByData;

    @FXML
    private Button displayByNumber;

    @FXML
    private TextField numberText;

    @FXML
    private PieChart numberPieChart;

    @FXML
    private PieChart dataPieChart;

    @FXML
    private Label graphInformationData;

    @FXML
    private Label graphInformationNumber;

    @FXML
    private CheckBox reverseDataNumberFilter;

    @FXML
    private CheckBox reverseDisplayIpFilter;

    public AmountOfDataController() {
        this.managingAmountOfData = new ManagingAmountOfData();
    }

    @FXML
    void loadFileClick(MouseEvent event) throws WrongFileChosen, IOException {
        if(event.getButton() == MouseButton.PRIMARY){
            lFile();
            unlockButtons();
            setUpChartData();
            setUpChartNumber();
        }
    }

    @FXML
    void loadFilePress(KeyEvent event) throws WrongFileChosen, IOException {
        if(event.getCode() == KeyCode.ENTER){
            lFile();
            unlockButtons();
            setUpChartData();
            setUpChartNumber();
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
        if (event.getCode() == KeyCode.ENTER){
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
    void displayDataClick(MouseEvent event) throws NotValidInsertion {
        if(event.getButton() == MouseButton.PRIMARY){
            initializeTable();
            displayData();
        }
    }

    @FXML
    void displayDataPress(KeyEvent event) throws NotValidInsertion {
        if(event.getCode() == KeyCode.ENTER){
            initializeTable();
            displayData();
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
        boolean filterStatus = reverseDataNumberFilter.isSelected();
        int numberConnections = Integer.parseInt(numberText.getText());
        managingAmountOfData.displayConnectionsByNumber(numberConnections, filterStatus);
        connectionsTable.setItems(managingAmountOfData.getObsListNumber());
    }

    private void displayData() throws NotValidInsertion {
        boolean filterStatus = reverseDataNumberFilter.isSelected();
        double data = Double.parseDouble(dataText.getText());
        managingAmountOfData.displayConnectionsByData(data, filterStatus);
        connectionsTable.setItems(managingAmountOfData.getObsListData());
    }

    private void displayIp() {
        boolean filterStatus = reverseDisplayIpFilter.isSelected();
        String sourceIp = sourceIpText.getText();
        String destinationIp = destinationIpText.getText();
        if(filterStatus){
            managingAmountOfData.displayConnectionsByIpReverse(sourceIp, destinationIp);
        }else {
            managingAmountOfData.displayConnectionsByIp(sourceIp, destinationIp);
        }
        connectionsTable.setItems(managingAmountOfData.getObsListIp());
    }

    private void display() throws TooManyConnections {
        managingAmountOfData.displayAllConnections((int) numberToDisplay.getValue());
        connectionsTable.setItems(managingAmountOfData.getObsListDisplay());
    }

    private void initializeTable() {
        connectionsTable.getItems().clear();
        numberConnectionsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfConnections"));
        sourceIpColumn.setCellValueFactory(new PropertyValueFactory<>("sourceIp"));
        destinationIpColumn.setCellValueFactory(new PropertyValueFactory<>("destinationIp"));
        amountDataColumn.setCellValueFactory(new PropertyValueFactory<>("amountOfData"));
    }

    private void unlockButtons() {
        displayTable.setDisable(false);
        displayByIp.setDisable(false);
        displayByData.setDisable(false);
        displayByNumber.setDisable(false);
        reverseDataNumberFilter.setDisable(false);
        reverseDisplayIpFilter.setDisable(false);
    }

    private void lFile() throws WrongFileChosen, IOException {
        String path;
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("AmountOfData files", "*.txt"));
        File file = fileChooser.showOpenDialog(null);
        if(file != null && file.getName().equals("AmountOfData.txt")){
            path = file.getAbsolutePath();
            loadFileError.setText("File loading complete");
            loadFileError.setTextFill(Color.GREEN);
            loadFileError.setVisible(true);
            graphInformationData.setVisible(false);
            graphInformationNumber.setVisible(false);
            loadFile.setDisable(true);
        }else {
            loadFileError.setText("Error in loading process");
            loadFileError.setTextFill(Color.RED);
            loadFileError.setVisible(true);
            throw new WrongFileChosen();
        }

        managingAmountOfData.openFile(path);

        int numberOfConnections = managingAmountOfData.getLoadAmountOfData().getObjAmountOfData().size();
        displayNumberOfConnections.setText(String.valueOf(numberOfConnections));
        numberToDisplay.setMax(managingAmountOfData.getLoadAmountOfData().getObjAmountOfData().size());
    }

    private void setUpChartData(){
        managingAmountOfData.dataMap();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        managingAmountOfData.getDataChart().forEach((key, value) -> pieChartData.add(new PieChart.Data(key, value)));
        dataPieChart.setData(pieChartData);
    }

    private void setUpChartNumber(){
        managingAmountOfData.numberMap();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        managingAmountOfData.getNumberChart().forEach((key, value) -> pieChartData.add(new PieChart.Data(key, value)));
        numberPieChart.setData(pieChartData);
    }
}
