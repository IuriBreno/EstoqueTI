module scenebuilder.com.example.estoqueti {
    requires javafx.controls;
    requires javafx.fxml;

    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens scenebuilder.com.example.estoqueti to javafx.fxml;
    exports scenebuilder.com.example.estoqueti;
}