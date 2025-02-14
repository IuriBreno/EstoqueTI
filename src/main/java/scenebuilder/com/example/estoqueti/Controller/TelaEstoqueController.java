package scenebuilder.com.example.estoqueti.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import scenebuilder.com.example.estoqueti.Model.Estoque;
import scenebuilder.com.example.estoqueti.Model.Login;
import scenebuilder.com.example.estoqueti.Model.Saida;
import scenebuilder.com.example.estoqueti.Repository.EstoqueRepository;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class TelaEstoqueController implements Initializable {

    @FXML
    private TextField dataInventario;

    @FXML
    private TextField descricaoProduto;

    @FXML
    private Button limparCampos;

    @FXML
    private TextField numeroNf;

    @FXML
    private TextField quantidade;

    @FXML
    private Button salvarInventario;

    @FXML
    private TextField valorUnit;


    EstoqueRepository estoqueRepository = new EstoqueRepository();
    @FXML
    void limparCampos(MouseEvent event) {
        descricaoProduto.clear();
        quantidade.clear();
        numeroNf.clear();
        valorUnit.clear();
    }

    @FXML
    void salvarEstoque(MouseEvent event) {

        if (descricaoProduto.getText().trim().isEmpty() || quantidade.getText().trim().isEmpty() || valorUnit.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText("Preencha todos os campos");
            alert.showAndWait();
        } else {
            try {
                if (Integer.parseInt(quantidade.getText()) <= 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informação");
                    alert.setHeaderText("A quantidade precisa ser maior que 0.");
                    alert.showAndWait();
                } else {
                    Estoque estoque = new Estoque();
                    estoque.setDescricaoProduto(descricaoProduto.getText());
                    estoque.setQuantidade(Integer.parseInt(quantidade.getText()));
                    estoque.setValorUnit(Float.parseFloat(valorUnit.getText()));
                    estoque.setValorTotal(Float.parseFloat(valorUnit.getText()));
                    estoque.setEstoqueReal(Integer.parseInt(quantidade.getText()));

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date dataUtil = format.parse(dataInventario.getText()); // Converte a String para java.util.Date
                    java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); // Converte java.util.Date para java.sql.Date
                    estoque.setData(dataSql);
                    if(numeroNf.getText() == null || numeroNf.getText().trim().isEmpty()){
                        estoque.setNumeroNf("NÃO INFORMADO");
                    }else{
                        estoque.setNumeroNf(numeroNf.getText());
                    }


                    estoqueRepository.adicionaProduto(estoque);

                    descricaoProduto.clear();
                    quantidade.clear();
                    numeroNf.clear();
                    valorUnit.clear();


                }
            }catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informação");
                alert.setHeaderText("Quantidade inválida.");
                alert.showAndWait();
            }

           setDataAddEstoqueHoje();

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setDataAddEstoqueHoje();
    }

    @FXML
    void voltarMenuInicial(MouseEvent event) {
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
    private void setDataAddEstoqueHoje() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataHoje = LocalDate.now().format(formatter);
        dataInventario.setText(dataHoje);
    }


}
