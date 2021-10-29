package com.example.securazeek.controller;

import com.example.securazeek.exceptions.NotValidInsertion;
import com.example.securazeek.exceptions.WrongFileChosen;
import com.example.securazeek.objConnection.ObjAbsLongestConnection;
import com.example.securazeek.exceptions.ReadFileException;
import com.example.securazeek.functionalities.longConnection.ManagingAbsLongestConnection;
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

public class AbsConnectionController {

    private ManagingAbsLongestConnection managingAbsLongestConnection;

    @FXML
    private Button loadFile;

    @FXML
    private TableView<ObjAbsLongestConnection> connectionsTable;

    @FXML
    private TableColumn<ObjAbsLongestConnection, String> sourceIp;

    @FXML
    private TableColumn<ObjAbsLongestConnection, String> destinationIp;

    @FXML
    private TableColumn<ObjAbsLongestConnection, Double> duration;

    @FXML
    private TextField displayNumberOfConnections;

    @FXML
    private Button displayTable;

    @FXML
    private Slider numberToDisplay;

    @FXML
    private Button displayByIp;

    @FXML
    private TextField sourceIpText;

    @FXML
    private TextField destinationIpText;

    @FXML
    private TextField durationSetter;

    @FXML
    private Button displayByDuration;

    @FXML
    private PieChart piePlot;

    @FXML
    private Label loadFileError;

    @FXML
    private Label graphInformationLabel;

    @FXML
    private TextArea infoBoxArea;

    @FXML
    private CheckBox reverseDurationFilter;

    @FXML
    private CheckBox reverseDisplayIpFilter;

    public AbsConnectionController() {
        this.managingAbsLongestConnection = new ManagingAbsLongestConnection();
    }

    @FXML
    void displayDurationClick(MouseEvent event) throws NotValidInsertion {
        if(event.getButton() == MouseButton.PRIMARY){
            initializeTable();
            displayDuration();
        }
    }

    @FXML
    void displayDurationPress(KeyEvent event) throws NotValidInsertion {
        if(event.getCode() == KeyCode.ENTER){
            initializeTable();
            displayDuration();
        }
    }

    @FXML
    void displayIpClick(MouseEvent event){
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
    void displayClick(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY){
            initializeTable();
            display();
        }
    }

    @FXML
    void displayPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            initializeTable();
            display();
        }
    }

    @FXML
    void loadFileClick(MouseEvent event) throws FileNotFoundException, ReadFileException, WrongFileChosen {
        if(event.getButton() == MouseButton.PRIMARY){
            lFile();
            unlockButtons();
            setUpChart();
            setInfoBox();
        }
    }

    @FXML
    void loadFilePress(KeyEvent event) throws FileNotFoundException, ReadFileException, WrongFileChosen {
        if(event.getCode() == KeyCode.ENTER){
            lFile();
            unlockButtons();
            setUpChart();
            setInfoBox();
        }
    }

    private void displayDuration() throws NotValidInsertion {
        boolean filterStatus = reverseDurationFilter.isSelected();
        managingAbsLongestConnection.displayConnectionsByDuration(Double.parseDouble(durationSetter.getText()), filterStatus);
        connectionsTable.setItems(managingAbsLongestConnection.getObsListDuration());
    }

    private void displayIp(){
        boolean filterStatus = reverseDisplayIpFilter.isSelected();
        String sourceIp = sourceIpText.getText();
        String destinationIp = destinationIpText.getText();
        if(filterStatus){
            managingAbsLongestConnection.displayConnectionsByIpReverse(sourceIp, destinationIp);
        }else {
            managingAbsLongestConnection.displayConnectionsByIp(sourceIp, destinationIp);
        }
        connectionsTable.setItems(managingAbsLongestConnection.getObsListByIp());
    }

    private void initializeTable() {
        connectionsTable.getItems().clear();
        sourceIp.setCellValueFactory(new PropertyValueFactory<>("sourceIp"));
        destinationIp.setCellValueFactory(new PropertyValueFactory<>("destinationIp"));
        duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
    }

    private void display(){
        managingAbsLongestConnection.displayAllConnections((int) numberToDisplay.getValue());
        connectionsTable.setItems(managingAbsLongestConnection.getObsList());
    }

    private void lFile() throws FileNotFoundException, ReadFileException, WrongFileChosen {
        String path;
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("AbsLongestConnection file", "*.txt"));
        File file = fileChooser.showOpenDialog(null);
        if(file != null && file.getName().equals("AbsLongestConnection.txt")){
            path = file.getAbsolutePath();
            loadFileError.setText("File loading complete");
            loadFileError.setTextFill(Color.GREEN);
            loadFileError.setVisible(true);
            graphInformationLabel.setVisible(false);
            loadFile.setDisable(true);
        }else {
            loadFileError.setText("Error in loading process");
            loadFileError.setTextFill(Color.RED);
            loadFileError.setVisible(true);
            throw new WrongFileChosen();
        }

        managingAbsLongestConnection.openFile(path);

        int numberOfConnections = managingAbsLongestConnection.getLoadAbsLongestConnection().getObjAbsLongestConnections().size();
        displayNumberOfConnections.setText(String.valueOf(numberOfConnections));
        numberToDisplay.setMax(managingAbsLongestConnection.getLoadAbsLongestConnection().getObjAbsLongestConnections().size());
    }

    private void unlockButtons(){
        displayTable.setDisable(false);
        displayByIp.setDisable(false);
        displayByDuration.setDisable(false);
        reverseDurationFilter.setDisable(false);
        reverseDisplayIpFilter.setDisable(false);
    }

    private void setUpChart() {
        managingAbsLongestConnection.countConnectionsOccurrences();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        managingAbsLongestConnection.getCountConnections().forEach((key, value) -> pieChartData.add(new PieChart.Data(key, value)));
        piePlot.setData(pieChartData);
    }

    private void setInfoBox(){
        infoBoxArea.clear();
        infoBoxArea.setText("The duration average is: " + String.format("%.2f", managingAbsLongestConnection.connectionsAverage()));
    }

}
