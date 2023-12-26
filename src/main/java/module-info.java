module com.anshul.pogoso_jwt {
    requires javafx.controls;
    requires javafx.fxml;
    requires jjwt.api;


    opens com.anshul.pogoso_jwt to javafx.fxml;
    exports com.anshul.pogoso_jwt;
}