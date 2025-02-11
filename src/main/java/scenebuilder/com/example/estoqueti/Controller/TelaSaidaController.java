package scenebuilder.com.example.estoqueti.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TelaSaidaController {

    @FXML
    private TableColumn<?, ?> colunaDataInventario;

    @FXML
    private TableColumn<?, ?> coluna_descricao;

    @FXML
    private TableColumn<?, ?> coluna_qtdEstoqueReal;

    @FXML
    private TextField dataSaida;

    @FXML
    private TextField descricaoProduto;

    @FXML
    private TextField idChamado;

    @FXML
    private TextField quantidade;

    @FXML
    private Button salvarSaida;

    @FXML
    private ChoiceBox<?> setores;

    @FXML
    private TableView<?> tabelaProdutos;

    @FXML
    void botaoRealizaSaida(MouseEvent event) {

    }

    @FXML
    void voltarMenuAdm(MouseEvent event) {

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenebuilder/com/example/estoqueti/acesso_adm.fxml"));
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
