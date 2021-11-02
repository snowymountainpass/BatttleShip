module com.codecool.battleship {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires validatorfx;

    opens com.codecool.battleship to javafx.fxml;
    exports com.codecool.battleship;
}