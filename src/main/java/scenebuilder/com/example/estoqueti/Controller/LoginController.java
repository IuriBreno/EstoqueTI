package scenebuilder.com.example.estoqueti.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import scenebuilder.com.example.estoqueti.Model.Login;
import scenebuilder.com.example.estoqueti.Repository.LoginRepository;

import javax.swing.*;
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
    Login login;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    void Aut_Login(ActionEvent event) {


        if(validaCamposVazios()){
            String nomeUsuario = usuario.getText().toString();
//          String senhaUsuario = senha.getText();

            Login dadosLogin = new Login(usuario.getText().toString(),senha.getText());


            if (loginRepository.validaLoginAdm(dadosLogin)) {//verifica se o método retorno algo verdadeiro
                System.out.println("EXISTE ADM. VAMOS CHAMAR AGORA A TELA DO ADM");
//            //se não existir adm com esse usuario ou senha, vai verificar se existe na tabela de usuarios
            } else if (loginRepository.validaLoginUser(dadosLogin)) {
                System.out.println("EXISTE USUARIO. VAMOS CHAMAR AGORA A TELA DO USUARIO NORMAL");
            }else{
                System.out.println("NÃO EXISTE USUARIO E NEM ADM");
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
