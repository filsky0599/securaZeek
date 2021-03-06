package com.example.securazeek.controller;

import com.example.securazeek.exceptions.NotValidInsertion;
import com.example.securazeek.exceptions.TooManyConnections;
import com.example.securazeek.exceptions.WrongFileChosen;
import com.example.securazeek.functionalities.connection.ManagingLongestConnection;
import com.example.securazeek.objModel.ObjLongestConnection;
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
import java.io.IOException;

public class LongestConnectionController {

    private ManagingLongestConnection managingLongestConnection;

    @FXML
    private Button loadFile;

    @FXML
    private TextField displayNumberOfConnections;

    @FXML
    private Label loadFileError;

    @FXML
    private Button displayTable;

    @FXML
    private TableView<ObjLongestConnection> connectionsTable;

    @FXML
    private TableColumn<ObjLongestConnection, String> sourceIp;

    @FXML
    private TableColumn<ObjLongestConnection, String> destinationIp;

    @FXML
    private TableColumn<ObjLongestConnection, Double> durationColumn;

    @FXML
    private TableColumn<ObjLongestConnection, Integer> portColumn;

    @FXML
    private TableColumn<ObjLongestConnection, String> protocolColumn;

    @FXML
    private TableColumn<ObjLongestConnection, String> serviceColumn;

    @FXML
    private TextField connectionsToDisplay;

    @FXML
    private Button displayByIp;

    @FXML
    private TextField sourceIpText;

    @FXML
    private TextField destinationIpText;

    @FXML
    private Button displayByDuration;

    @FXML
    private TextField durationSetter;

    @FXML
    private Button displayByPort;

    @FXML
    private TextField portSetter;

    @FXML
    private TextField protocolSetter;

    @FXML
    private Button displayByProtocol;

    @FXML
    private TextField serviceSetter;

    @FXML
    private Button displayByService;

    @FXML
    private TextArea informationTextArea;

    @FXML
    private PieChart portsPlot;

    @FXML
    private PieChart protocolPlots;

    @FXML
    private PieChart servicePlots;

    @FXML
    private Label graphInformationLabelPort;

    @FXML
    private Label graphInformationLabelProtocol;

    @FXML
    private Label graphInformationLabelService;

    @FXML
    private CheckBox reverseDurationFilter;

    @FXML
    private CheckBox reversePortProtoServiceFilter;

    @FXML
    private CheckBox reverseDisplayIpFilter;

    public LongestConnectionController() {
        this.managingLongestConnection = new ManagingLongestConnection();
    }

    @FXML
    public void displayServicePress(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            initializeTable();
            displayService();
        }
    }

    @FXML
    public void displayServiceClick(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY){
            initializeTable();
            displayService();
        }
    }

    @FXML
    public void displayProtocolPress(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            initializeTable();
            displayProtocol();
        }
    }

    @FXML
    public void displayProtocolClick(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY){
            initializeTable();
            displayProtocol();
        }
    }

    @FXML
    public void displayPortPress(KeyEvent event) throws NotValidInsertion {
        if(event.getCode() == KeyCode.ENTER){
            initializeTable();
            displayPort();
        }
    }

    @FXML
    public void displayPortClick(MouseEvent event) throws NotValidInsertion {
        if(event.getButton() == MouseButton.PRIMARY){
            initializeTable();
            displayPort();
        }
    }

    @FXML
    public void displayDurationPress(KeyEvent event) throws NotValidInsertion {
        if(event.getCode() == KeyCode.ENTER){
            initializeTable();
            displayDuration();
        }
    }

    @FXML
    public void displayDurationClick(MouseEvent event) throws NotValidInsertion {
        if(event.getButton() == MouseButton.PRIMARY){
            initializeTable();
            displayDuration();
        }
    }

    @FXML
    public void displayIpPress(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            initializeTable();
            displayIp();
        }
    }

    @FXML
    public void displayIpClick(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY){
            initializeTable();
            displayIp();
        }
    }

    @FXML
    public void displayPressed(KeyEvent event) throws TooManyConnections {
        if(event.getCode() == KeyCode.ENTER){
            initializeTable();
            display();
        }
    }

    @FXML
    public void displayClick(MouseEvent event) throws TooManyConnections {
        if(event.getButton() == MouseButton.PRIMARY){
            initializeTable();
            display();
        }
    }

    @FXML
    public void loadFilePress(KeyEvent event) throws WrongFileChosen, IOException {
        if(event.getCode() == KeyCode.ENTER){
            lFile();
            unlockButtons();
            setInfoBox();
            displayPortsPlot();
            displayProtocolPlot();
            displayServicePlot();
        }
    }

    @FXML
    public void loadFileClick(MouseEvent event) throws WrongFileChosen, IOException {
        if(event.getButton() == MouseButton.PRIMARY){
            lFile();
            unlockButtons();
            setInfoBox();
            displayPortsPlot();
            displayProtocolPlot();
            displayServicePlot();
        }
    }

    private void lFile() throws WrongFileChosen, IOException {
        String path;
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("LongestConnections file", "*.txt"));
        File file = fileChooser.showOpenDialog(null);
        if(file != null && file.getName().equals("LongestConnection.txt")){
            path = file.getAbsolutePath();
            loadFileError.setText("File loading complete");
            loadFileError.setTextFill(Color.GREEN);
            loadFileError.setVisible(true);
            graphInformationLabelPort.setVisible(false);
            graphInformationLabelProtocol.setVisible(false);
            graphInformationLabelService.setVisible(false);
            loadFile.setDisable(true);
        }else{
            loadFileError.setText("Error in loading process");
            loadFileError.setTextFill(Color.RED);
            loadFileError.setVisible(true);
            throw new WrongFileChosen();
        }

        managingLongestConnection.openFile(path);
        int numberOfConnections = managingLongestConnection.getLoadLongestConnection().getObjLongestConnection().size();
        displayNumberOfConnections.setText(String.valueOf(numberOfConnections));
    }

    private void unlockButtons(){
        displayTable.setDisable(false);
        displayByIp.setDisable(false);
        displayByDuration.setDisable(false);
        displayByPort.setDisable(false);
        displayByProtocol.setDisable(false);
        displayByService.setDisable(false);
        reverseDurationFilter.setDisable(false);
        reversePortProtoServiceFilter.setDisable(false);
        reverseDisplayIpFilter.setDisable(false);
    }

    private void initializeTable(){
        connectionsTable.getItems().clear();
        sourceIp.setCellValueFactory(new PropertyValueFactory<>("sourceIp"));
        destinationIp.setCellValueFactory(new PropertyValueFactory<>("destinationIp"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        portColumn.setCellValueFactory(new PropertyValueFactory<>("port"));
        protocolColumn.setCellValueFactory(new PropertyValueFactory<>("protocol"));
        serviceColumn.setCellValueFactory(new PropertyValueFactory<>("service"));
    }

    private void display() throws TooManyConnections {
        int numberOfConnections = Integer.parseInt(connectionsToDisplay.getText());
        managingLongestConnection.displayAllConnections(numberOfConnections);
        connectionsTable.setItems(managingLongestConnection.getObsListDisplay());
    }

    private void displayIp(){
        boolean filterStatus = reverseDisplayIpFilter.isSelected();
        String sourceIp = sourceIpText.getText();
        String destinationIp = destinationIpText.getText();
        if(filterStatus){
            managingLongestConnection.displayConnectionsByIpReverse(sourceIp, destinationIp);
        }else {
            managingLongestConnection.displayConnectionsByIp(sourceIp, destinationIp);
        }
        connectionsTable.setItems(managingLongestConnection.getObsListIp());
    }

    private void displayDuration() throws NotValidInsertion {
        boolean filterStatus = reverseDurationFilter.isSelected();
        managingLongestConnection.displayConnectionsByDuration(Double.parseDouble(durationSetter.getText()), filterStatus);
        connectionsTable.setItems(managingLongestConnection.getObsListDuration());
    }

    private void displayPort() throws NotValidInsertion {
        boolean filterStatus = reversePortProtoServiceFilter.isSelected();
        managingLongestConnection.displayConnectionsByPort(Integer.parseInt(portSetter.getText()), filterStatus);
        connectionsTable.setItems(managingLongestConnection.getObsListPort());
    }

    private void displayProtocol(){
        boolean filterStatus = reversePortProtoServiceFilter.isSelected();
        managingLongestConnection.displayConnectionsByProtocol(protocolSetter.getText(), filterStatus);
        connectionsTable.setItems(managingLongestConnection.getObsListProtocol());
    }

    private void displayService(){
        boolean filterStatus = reversePortProtoServiceFilter.isSelected();
        managingLongestConnection.displayConnectionsByService(serviceSetter.getText(), filterStatus);
        connectionsTable.setItems(managingLongestConnection.getObsListService());
    }

    private void setInfoBox(){
        informationTextArea.clear();
        informationTextArea.setText("The duration average is: " + String.format("%.2f", managingLongestConnection.connectionsAverage()));
    }

    private void displayPortsPlot(){
        managingLongestConnection.countPortOccurrences();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        managingLongestConnection.getCountPort().forEach((key, value) -> pieChartData.add(new PieChart.Data(String.valueOf(key), value)));
        portsPlot.setData(pieChartData);
    }

    private void displayProtocolPlot(){
        managingLongestConnection.countProtocolOccurrences();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        managingLongestConnection.getCountProtocol().forEach((key, value) -> pieChartData.add(new PieChart.Data(key, value)));
        protocolPlots.setData(pieChartData);
    }

    private void displayServicePlot(){
        managingLongestConnection.countServiceOccurrences();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        managingLongestConnection.getCountService().forEach((key, value) -> pieChartData.add(new PieChart.Data(key, value)));
        servicePlots.setData(pieChartData);
    }

}
