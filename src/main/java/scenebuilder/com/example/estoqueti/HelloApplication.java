package scenebuilder.com.example.estoqueti;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    Stage janela;

    @Override
    public void start(Stage stage) throws IOException {
        janela = stage;
        Parent tela = FXMLLoader.load(getClass().getResource("login.fxml"));

        Scene scene = new Scene(tela);
        janela.setScene(scene);
        janela.show();
    }


}