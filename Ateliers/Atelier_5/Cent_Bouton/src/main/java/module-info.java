module com.example.cent_bouton {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cent_bouton to javafx.fxml;
    exports com.example.cent_bouton;
}