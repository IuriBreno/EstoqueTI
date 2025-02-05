package scenebuilder.com.example.estoqueti.Controller;

import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TelaAdmController {

//    private static Stage tela;
//
//    public void start(Stage tela){
//        TelaAdmController.tela = tela;
//    //Carrega essa tela iniciada
//    }
//
//    public static Stage getTela(){
//        return tela;
//    }
//
//    Stage stage = TelaAdmController.getTela();


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

}
