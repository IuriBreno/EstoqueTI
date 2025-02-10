package scenebuilder.com.example.estoqueti.Controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import scenebuilder.com.example.estoqueti.Model.Produto;
import scenebuilder.com.example.estoqueti.Repository.ProdutoRepository;

import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TelaVisualizaEstoqueController implements Initializable {

    @FXML
    private TableColumn<Produto, Date> coluna_dataInventario;

    @FXML
    private TableColumn<Produto, String> coluna_descricao;

    @FXML
    private TableColumn<Produto, String> coluna_numeroNf;

    @FXML
    private TableColumn<Produto, Integer> coluna_qtdEstoqueReal;

    @FXML
    private TableColumn<Produto, Float> coluna_valorTotalProduto;

    @FXML
    private TableColumn<Produto, Float> coluna_valorUnitario;

    @FXML
    private TextField dataInventario;

    @FXML
    private TextField descricaoProduto;

    @FXML
    private TextField numeroNf;

    @FXML
    private TextField qtdEstoqueRealProduto;

    @FXML
    private TextField qtdEstoqueTotalProduto;

    @FXML
    private TableView<Produto> tabelaProdutos;

    @FXML
    private TextField valorTotalEstoqueProduto;

    private boolean modeEdit = false;// Indica se estamos editando um estudante

    ProdutoRepository produtoRepository = new ProdutoRepository();
    Produto produto = new Produto();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        coluna_descricao.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDescricao()));
        coluna_valorUnitario.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getValorUnit()));
        coluna_qtdEstoqueReal.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getGetQtdEstoqueReal()));
        coluna_valorTotalProduto.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getValorTotalEstoqueProduto()));
        coluna_dataInventario.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getDataInventario()));
        coluna_numeroNf.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNumeroNf()));


        tabelaProdutos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                Optional.ofNullable(newValue).ifPresent(produto -> preencherCampos((Produto) produto))
        );//ficar atualizando os campos selecionados na tableViewr
        carregaDados();

    }

    public void carregaDados(){

        List<Produto> produtos = produtoRepository.listaProdutos();
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

        modeEdit = true;
        descricaoProduto.setText(produtoSelecionado.getDescricao());
        dataInventario.setText(produtoSelecionado.getDataInventario().toString());
        numeroNf.setText(produtoSelecionado.getNumeroNf());
        qtdEstoqueTotalProduto.setText(String.valueOf(produtoSelecionado.getQtdEstoque()));
        qtdEstoqueRealProduto.setText(String.valueOf(produtoSelecionado.getGetQtdEstoqueReal()));
        valorTotalEstoqueProduto.setText(String.valueOf(produtoSelecionado.getValorTotalEstoqueProduto()));
        produto = produtoSelecionado;
    }

    @FXML
    void voltaMenuInicial(MouseEvent event) {

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

    @FXML
    void editarEstoque(ActionEvent event) {

    }



}
