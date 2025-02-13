package scenebuilder.com.example.estoqueti.Controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import scenebuilder.com.example.estoqueti.Model.Login;
import scenebuilder.com.example.estoqueti.Model.Produto;
import scenebuilder.com.example.estoqueti.Model.Saida;
import scenebuilder.com.example.estoqueti.Model.Setor;
import scenebuilder.com.example.estoqueti.Repository.SaidaRepository;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    private String nomeUsuario;
    int qtdEstoqueBanco;
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

        qtdEstoqueBanco = produtoSelecionado.getGetQtdEstoqueReal();

    }


    @FXML
    void voltarMenuAdm(MouseEvent event) {
        try {
            Login usuarioLogado = Login.getUsuarioLogado();
            if (usuarioLogado == null) {
                System.out.println("Erro: Nenhum usuário autenticado.");
                return;
            }

            FXMLLoader loader = new FXMLLoader();
            if (usuarioLogado.getTipoLogin() == 1) {
                loader.setLocation(getClass().getResource("/scenebuilder/com/example/estoqueti/acesso_adm.fxml"));
            } else {
                loader.setLocation(getClass().getResource("/scenebuilder/com/example/estoqueti/acesso_user.fxml"));
            }

            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println("Erro ao voltar para o menu inicial: " + e.getMessage());
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
            try{
                if(Integer.parseInt(quantidade.getText()) > qtdEstoqueBanco){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informação");
                    alert.setHeaderText("A quantidade de estoque é insuficiente.");
                    alert.showAndWait();

                }else{
                    Saida saida = new Saida();
                    saida.setDescProduto(descricaoProduto.getText());
                    saida.setNomeSetor(setores.getValue().getNomeSetor());
                    saida.setQuantidade(Integer.parseInt(quantidade.getText()));
                    saida.setChamado(idChamado.getText());
                    String usuarioLogado = Login.getUsuarioLogado().getUsuario();
                    saida.setNomeUsuario(usuarioLogado);

                    java.sql.Date data = java.sql.Date.valueOf(dataSaida.getText());
                    saida.setDataSaida(data);

                    saidaRepository.registraSaida(saida, descricaoProduto.getText());




                }
            }catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informação");
                alert.setHeaderText("Quantidade inválida.");
                alert.showAndWait();
            }


            carregaDados();
            carregarSetoresNoComboBox();
            setDataSaidaHoje();
            limpaCampos();

        }
    }

    private void limpaCampos() {
        descricaoProduto.clear();
        quantidade.clear();
        idChamado.clear();
        setores.setValue(null);
    }



}
