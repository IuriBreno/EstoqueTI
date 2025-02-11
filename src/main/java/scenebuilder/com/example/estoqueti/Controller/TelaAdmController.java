package scenebuilder.com.example.estoqueti.Controller;

import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import scenebuilder.com.example.estoqueti.Model.Login;

public class TelaAdmController {

    Login dadosLogin = new Login();

    @FXML
    void imageClick_VisualizaEstoque(MouseEvent event) {

    try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenebuilder/com/example/estoqueti/visualiza_estoque.fxml"));
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
    void imageClick_Movimentacoes(MouseEvent event) {

        try{
            dadosLogin.setTipoLogin(1);
            System.out.println("entrando na tela adm controller com stauts de:" + dadosLogin.getTipoLogin());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenebuilder/com/example/estoqueti/movimentacoes.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
            System.out.println("sainda da tela adm controller com stauts de:" + dadosLogin.getTipoLogin());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
