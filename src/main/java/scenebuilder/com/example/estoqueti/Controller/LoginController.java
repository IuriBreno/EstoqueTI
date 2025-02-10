package scenebuilder.com.example.estoqueti.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import scenebuilder.com.example.estoqueti.Model.Usuario;
import scenebuilder.com.example.estoqueti.Repository.LoginRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    @FXML
    private Button bt_login;

    @FXML
    private PasswordField senha;

    @FXML
    private TextField usuario;

    LoginRepository loginRepository = new LoginRepository();
    Usuario login;
    TelaUsuarioController telaUsuarioController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    void Aut_Login(ActionEvent event) {


        if(validaCamposVazios()){
            //String nomeUsuario = usuario.getText().toString();
            //String senhaUsuario = senha.getText();

            Usuario dadosLogin = new Usuario(usuario.getText().toString(),senha.getText());

            try {
                Stage stage = (Stage) usuario.getScene().getWindow(); // Obtém a janela atual
                FXMLLoader loader = new FXMLLoader();

                if (loginRepository.validaLoginAdm(dadosLogin)) {
                    System.out.println("EXISTE ADM. VAMOS CHAMAR AGORA A TELA DO ADM");
                    loader.setLocation(getClass().getResource("/scenebuilder/com/example/estoqueti/acesso_adm.fxml"));
                } else if (loginRepository.validaLoginUser(dadosLogin)) {
                    System.out.println("EXISTE USUÁRIO. VAMOS CHAMAR A TELA DO USUÁRIO");
                    loader.setLocation(getClass().getResource("/scenebuilder/com/example/estoqueti/acesso_user.fxml"));
                } else {
                    System.out.println("NÃO EXISTE USUÁRIO E NEM ADM");
                    return;
                }

                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();


            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

    }


    public boolean validaCamposVazios() {
        if(usuario.getText().toString().trim().isEmpty() || senha.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos obrigatórios");
            alert.setHeaderText("Por favor, preencha todos os campos.");
            alert.showAndWait();
            return false;  // Não continuar o processo de login
        }
        return true;

    }

}
