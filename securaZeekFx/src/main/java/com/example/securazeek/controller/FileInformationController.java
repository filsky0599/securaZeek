package com.example.securazeek.controller;

import com.example.securazeek.exceptions.ReadFileException;
import com.example.securazeek.exceptions.WrongFileChosen;
import com.example.securazeek.functionalities.fileInformation.ManagingFileInformation;
import com.example.securazeek.loadingFiles.LoadFileInformation;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;

public class FileInformationController {

    @FXML
    private TextArea fileInformationPrint;

    @FXML
    void printValue(KeyEvent event) throws FileNotFoundException, ReadFileException, WrongFileChosen {
        if(event.getCode() == KeyCode.ENTER){
            String path = "";
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("FileInformation file", "*.txt"));
            File file = fileChooser.showOpenDialog(null);
            if (file != null && file.getName().equals("FileInformation.txt")){
                path = file.getAbsolutePath();
            }else {
                throw new WrongFileChosen();
            }

            LoadFileInformation loadFileInformation = new LoadFileInformation();
            ManagingFileInformation managingFileInformation = new ManagingFileInformation(loadFileInformation);
            managingFileInformation.loadFile(path);
            fileInformationPrint.clear();
            fileInformationPrint.appendText(managingFileInformation.printFile());
        }
    }

}