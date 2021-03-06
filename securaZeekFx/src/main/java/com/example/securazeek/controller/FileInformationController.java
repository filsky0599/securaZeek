package com.example.securazeek.controller;

import com.example.securazeek.exceptions.WrongFileChosen;
import com.example.securazeek.functionalities.fileInformation.ManagingFileInformation;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class FileInformationController {

    @FXML
    private TextArea fileInformationPrint;

    @FXML
    void printValue(KeyEvent event) throws IOException, WrongFileChosen {
        if(event.getCode() == KeyCode.ENTER){
            String path;
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("FileInformation file", "*.txt"));
            File file = fileChooser.showOpenDialog(null);
            if (file != null && file.getName().equals("FileInformation.txt")){
                path = file.getAbsolutePath();
            }else {
                fileInformationPrint.clear();
                fileInformationPrint.setText("The selected file is not correct. Please retry.");
                throw new WrongFileChosen();
            }

            ManagingFileInformation managingFileInformation = new ManagingFileInformation();
            managingFileInformation.openFile(path);
            fileInformationPrint.clear();
            fileInformationPrint.setText(managingFileInformation.printFile());
        }
    }

}