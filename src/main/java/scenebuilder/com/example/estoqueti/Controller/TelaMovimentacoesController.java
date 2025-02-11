package scenebuilder.com.example.estoqueti.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import scenebuilder.com.example.estoqueti.Model.Movimentacoes;
import scenebuilder.com.example.estoqueti.Model.Setor;
import scenebuilder.com.example.estoqueti.Model.UsersPrivacy;
import scenebuilder.com.example.estoqueti.Repository.MovimentacoesRepository;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class TelaMovimentacoesController implements Initializable {

    @FXML
    private TableColumn<Movimentacoes, String> colunaChamado;

    @FXML
    private TableColumn<Movimentacoes, Date> colunaData;

    @FXML
    private TableColumn<Movimentacoes, String> colunaProduto;

    @FXML
    private TableColumn<Movimentacoes, Integer> colunaQuantidade;

    @FXML
    private TableColumn<Movimentacoes, String> colunaSetor;

    @FXML
    private TableColumn<Movimentacoes, String> colunaUsuario;

    @FXML
    private DatePicker filtroDataFim;

    @FXML
    private DatePicker filtroDataInicio;

    @FXML
    private TextField filtroIdChamado;

    @FXML
    private ComboBox<Setor> filtroSetor;

    @FXML
    private ComboBox<UsersPrivacy> filtroUsuario;

    @FXML
    private TableView<Movimentacoes> tabelaMovimentacoes;

    MovimentacoesRepository movimentacoesRepository = new MovimentacoesRepository();
    private Login dadosLogin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colunaUsuario.setCellValueFactory(new PropertyValueFactory<>("nomeUsuario"));
        colunaProduto.setCellValueFactory(new PropertyValueFactory<>("descProduto"));
        colunaSetor.setCellValueFactory(new PropertyValueFactory<>("setor"));
        colunaChamado.setCellValueFactory(new PropertyValueFactory<>("idChamado"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        carregaDados();
        carregarSetoresNoComboBox();
        carregarUsuariosNoComboBox();

    }


    @FXML
    void filtrarMovimentacoes(ActionEvent event) {

        Date dataInicio = filtroDataInicio.getValue() != null ? java.sql.Date.valueOf(filtroDataInicio.getValue()) : null;
        Date dataFim = filtroDataFim.getValue() != null ? java.sql.Date.valueOf(filtroDataFim.getValue()) : null;
        String chamado = filtroIdChamado.getText();
        String setor = filtroSetor.getValue() != null ? filtroSetor.getValue().getNomeSetor() : null;
        String usuario = filtroUsuario.getValue() != null ? filtroUsuario.getValue().getNome() : null;

        // Chama o método de filtro
        List<Movimentacoes> movimentacoesFiltradas = movimentacoesRepository.filtroMovimentacoes(dataInicio, dataFim, chamado, usuario, setor);

        // Atualiza a tabela com os dados filtrados
        ObservableList<Movimentacoes> listaMovimentacoes = FXCollections.observableArrayList(movimentacoesFiltradas);
        tabelaMovimentacoes.setItems(listaMovimentacoes);

    }

    @FXML
    void limparCampos(ActionEvent event) {

        filtroDataInicio.setValue(null); // Limpa o DatePicker
        filtroDataFim.setValue(null);    // Limpa o DatePicker
        filtroIdChamado.clear();         // Limpa o TextField
        filtroSetor.setValue(null);      // Limpa o ComboBox
        filtroUsuario.setValue(null);    // Limpa o ComboBox
        carregaDados();
    }
    @FXML
    void voltaMenuInicial(MouseEvent event) {
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

    public void carregaDados(){
        List<Movimentacoes> movimentacoes = movimentacoesRepository.listaTodasMovimentacoes();
        if (movimentacoes != null && !movimentacoes.isEmpty()) {
            ObservableList<Movimentacoes> listaMovimentacoes = FXCollections.observableArrayList(movimentacoes);
            tabelaMovimentacoes.setItems(listaMovimentacoes);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText("Nenhum dado encontrado");
            alert.show();
        }
    }

    public void carregarSetoresNoComboBox() {

        List<Setor> nomesSetores = movimentacoesRepository.listaSetores();

        if (nomesSetores != null && !nomesSetores.isEmpty()) {
            ObservableList<Setor> listaSetores = FXCollections.observableArrayList(nomesSetores);
            filtroSetor.setItems(listaSetores);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText("Nenhum setor encontrado no banco de dados");
            alert.show();
        }
    }
    public void carregarUsuariosNoComboBox() {

        List<UsersPrivacy> usersPrivacies = movimentacoesRepository.listaNomeUsuarios();

        if (usersPrivacies != null && !usersPrivacies.isEmpty()) {
            ObservableList<UsersPrivacy> listaNomes = FXCollections.observableArrayList(usersPrivacies);
            filtroUsuario.setItems(listaNomes);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText("Nenhum usuário encontrado");
            alert.show();
        }
    }



}
