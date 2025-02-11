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
import scenebuilder.com.example.estoqueti.Model.Login;
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

    TelaUsuarioController telaUsuarioController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    void Aut_Login(ActionEvent event) {
        if (validaCamposVazios()) {
            Login dadosLogin = new Login();
            dadosLogin.setUsuario(usuario.getText());
            dadosLogin.setSenha(senha.getText());

            try {
                Login loginRetornado = loginRepository.validaLoginUser(dadosLogin);

                if (loginRetornado != null) {
                    Login.setUsuarioLogado(loginRetornado);

                    Stage stage = (Stage) usuario.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader();

                    if (loginRetornado.getTipoLogin() == 1) {
                        loader.setLocation(getClass().getResource("/scenebuilder/com/example/estoqueti/acesso_adm.fxml"));
                    } else {
                        loader.setLocation(getClass().getResource("/scenebuilder/com/example/estoqueti/acesso_user.fxml"));
                    }

                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    System.out.println("Usuário ou senha incorretos.");
                }
            } catch (Exception e) {
                System.out.println("Erro ao realizar o login: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Por favor, preencha todos os campos!");
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
