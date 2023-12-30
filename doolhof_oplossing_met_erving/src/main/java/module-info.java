module be.inf1.doolhof {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens be.inf1.doolhof to javafx.fxml;
    exports be.inf1.doolhof;
}
