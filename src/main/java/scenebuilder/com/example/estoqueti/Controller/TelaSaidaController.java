package scenebuilder.com.example.estoqueti.Controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import scenebuilder.com.example.estoqueti.Model.Produto;
import scenebuilder.com.example.estoqueti.Model.Setor;
import scenebuilder.com.example.estoqueti.Repository.SaidaRepository;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TelaSaidaController implements Initializable {

    @FXML
    private TableColumn<Produto, Date> colunaDataInventario;

    @FXML
    private TableColumn<Produto, String> coluna_descricao;

    @FXML
    private TableColumn<Produto, Integer> coluna_qtdEstoqueReal;

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
    private ChoiceBox<Setor> setores;

    @FXML
    private TableView<Produto> tabelaProdutos;

    SaidaRepository saidaRepository = new SaidaRepository();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        coluna_descricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        coluna_qtdEstoqueReal.setCellValueFactory(new PropertyValueFactory<>("getQtdEstoqueReal"));
        colunaDataInventario.setCellValueFactory(new PropertyValueFactory<>("dataInventario"));

        tabelaProdutos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                Optional.ofNullable(newValue).ifPresent(produto -> preencherCampos((Produto) produto))
        );//ficar atualizando os campos selecionados na tableViewr
        carregaDados();
        carregarSetoresNoComboBox();
        setDataSaidaHoje();
    }

    public void carregaDados(){

        List<Produto> produtos = saidaRepository.exibeProdutos();
        if (produtos != null && !produtos.isEmpty()) {
            ObservableList<Produto> listaProdutos = FXCollections.observableArrayList(produtos);
            tabelaProdutos.setItems(listaProdutos);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText("Nenhum dado encontrado");
            alert.show();
        }
    }

    public void preencherCampos(Produto produtoSelecionado){

        descricaoProduto.setText(produtoSelecionado.getDescricao());

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

    public void carregarSetoresNoComboBox() {

        List<Setor> nomesSetores = saidaRepository.listaSetores();

        if (nomesSetores != null && !nomesSetores.isEmpty()) {
            ObservableList<Setor> listaSetores = FXCollections.observableArrayList(nomesSetores);
            setores.setItems(listaSetores);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText("Nenhum setor encontrado no banco de dados");
            alert.show();
        }
    }

    private void setDataSaidaHoje() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataHoje = LocalDate.now().format(formatter);
        dataSaida.setText(dataHoje);
    }
    @FXML
    void botaoRealizaSaida(MouseEvent event) {

        if (descricaoProduto.getText().trim().isEmpty() || quantidade.getText().trim().isEmpty() || setores.getValue() == null || idChamado.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText("Preencha todos os campos");
            alert.showAndWait();
        } else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText("Saída realizada com sucesso!");
            alert.showAndWait();

            // Limpa os campos após salvar os dados
            limpaCampos();
        }
    }

    private void limpaCampos() {
        descricaoProduto.clear();
        quantidade.clear();
        idChamado.clear();
        dataSaida.clear();
        setores.setValue(null);
    }



}
