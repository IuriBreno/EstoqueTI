package scenebuilder.com.example.estoqueti.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaUsuarioController {

    Stage janela;


    public void start(Stage stage) throws IOException {
        janela = stage;
        Parent tela = FXMLLoader.load(getClass().getResource("acesso_user.fxml"));

        Scene scene = new Scene(tela);
        janela.setScene(scene);
        janela.show();
    }
}
