module scenebuilder.com.example.estoqueti {
    requires javafx.controls;
    requires javafx.fxml;

    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;
    requires jdk.compiler;

    opens scenebuilder.com.example.estoqueti to javafx.fxml;
    exports scenebuilder.com.example.estoqueti;
    exports scenebuilder.com.example.estoqueti.Controller;
    opens scenebuilder.com.example.estoqueti.Controller to javafx.fxml;
    exports scenebuilder.com.example.estoqueti.Model;
    opens scenebuilder.com.example.estoqueti.Model to javafx.fxml;

}