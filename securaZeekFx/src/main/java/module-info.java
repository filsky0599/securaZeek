module com.example.securazeek {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.securazeek to javafx.fxml;
    exports com.example.securazeek;
    exports com.example.securazeek.loadingFiles;
    opens com.example.securazeek.loadingFiles to javafx.fxml;
    exports com.example.securazeek.controller;
    opens com.example.securazeek.controller to javafx.fxml;
    exports com.example.securazeek.objModel;
    opens com.example.securazeek.objModel to javafx.fxml;
    exports com.example.securazeek.exceptions;
    opens com.example.securazeek.exceptions to javafx.fxml;
}