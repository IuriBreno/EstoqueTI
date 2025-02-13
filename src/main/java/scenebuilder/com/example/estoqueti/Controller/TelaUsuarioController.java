package scenebuilder.com.example.estoqueti.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import scenebuilder.com.example.estoqueti.Model.Login;

public class TelaUsuarioController {
    Login dadosLogin = new Login();

    @FXML
    void movimentacoes(MouseEvent event) {

        try{
            dadosLogin.setTipoLogin(0);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenebuilder/com/example/estoqueti/movimentacoes.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void imageClick_RealizaSaida(MouseEvent event) {

        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenebuilder/com/example/estoqueti/realiza_saida.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();



        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
