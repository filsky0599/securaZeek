package com.example.securazeek.controller;

import com.example.securazeek.exceptions.NotValidInsertion;
import com.example.securazeek.exceptions.ReadFileException;
import com.example.securazeek.exceptions.TooManyConnections;
import com.example.securazeek.exceptions.WrongFileChosen;
import com.example.securazeek.functionalities.dns.ManagingNumberOfHosts;
import com.example.securazeek.objConnection.ObjNumberOfHosts;
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

public class NumberOfHostsController {

    private ManagingNumberOfHosts managingNumberOfHosts;

    @FXML
    private Button loadFile;

    @FXML
    private TableColumn<ObjNumberOfHosts, String> hostsNameColumn;

    @FXML
    private TableColumn<ObjNumberOfHosts, Integer> hostsNumberColumn;

    @FXML
    private TableView<ObjNumberOfHosts> hostsTable;

    @FXML
    private Label loadFileError;

    @FXML
    private TextField displayNumberOfHosts;

    @FXML
    private Button displayTable;

    @FXML
    private Slider numberToDisplay;

    @FXML
    private Button displayByNumberOfHosts;

    @FXML
    private TextField numberOfHostsText;

    @FXML
    private Button displayByHostName;

    @FXML
    private TextField hostNameText;

    @FXML
    private PieChart numberHostsChart;

    @FXML
    private Label graphInformationLabel;

    @FXML
    private CheckBox reverseNumberHostNameFilter;

    public NumberOfHostsController() {
        this.managingNumberOfHosts = new ManagingNumberOfHosts();
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
    void displayByNumberPress(KeyEvent event) throws NotValidInsertion {
        if(event.getCode() == KeyCode.ENTER){
            initializeTable();
            displayNumberHosts();
        }
    }

    @FXML
    void displayByNumberClick(MouseEvent event) throws NotValidInsertion {
        if(event.getButton() == MouseButton.PRIMARY){
            initializeTable();
            displayNumberHosts();
        }
    }

    @FXML
    void displayByHostNameClick(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY){
            initializeTable();
            displayHostsName();
        }
    }

    @FXML
    void displayByHostNamePress(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            initializeTable();
            displayHostsName();
        }
    }

    private void displayHostsName() {
        boolean filterStatus = reverseNumberHostNameFilter.isSelected();
        managingNumberOfHosts.displayByHosts(hostNameText.getText(), filterStatus);
        hostsTable.setItems(managingNumberOfHosts.getObsListHosts());
    }

    private void displayNumberHosts() throws NotValidInsertion {
        boolean filterStatus = reverseNumberHostNameFilter.isSelected();
        managingNumberOfHosts.displayByHostNumber(Integer.parseInt(numberOfHostsText.getText()), filterStatus);
        hostsTable.setItems(managingNumberOfHosts.getObsListNumberHosts());
    }

    private void display() throws TooManyConnections {
        managingNumberOfHosts.displayAllHosts((int) numberToDisplay.getValue());
        hostsTable.setItems(managingNumberOfHosts.getObsListDisplay());
    }

    private void initializeTable() {
        hostsTable.getItems().clear();
        hostsNumberColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfHosts"));
        hostsNameColumn.setCellValueFactory(new PropertyValueFactory<>("hostsNames"));
    }

    private void unlockButtons(){
        displayTable.setDisable(false);
        displayByNumberOfHosts.setDisable(false);
        displayByHostName.setDisable(false);
        reverseNumberHostNameFilter.setDisable(false);
    }

    private void lFile() throws WrongFileChosen, FileNotFoundException, ReadFileException {
        String path;
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("NumberOfHosts files", "*.txt"));
        File file = fileChooser.showOpenDialog(null);
        if(file != null && file.getName().equals("NumberOfHosts.txt")){
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

        managingNumberOfHosts.openFile(path);

        int numberOfConnections = managingNumberOfHosts.getLoadNumberOfHosts().getObjNumberOfHosts().size();
        displayNumberOfHosts.setText(String.valueOf(numberOfConnections));
        numberToDisplay.setMax(managingNumberOfHosts.getLoadNumberOfHosts().getObjNumberOfHosts().size());
    }

    private void setUpChart(){
        managingNumberOfHosts.countMapNumberHosts();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        managingNumberOfHosts.getMapNumberHosts().forEach((key, value) -> pieChartData.add(new PieChart.Data(key, value)));
        numberHostsChart.setData(pieChartData);
    }
}
