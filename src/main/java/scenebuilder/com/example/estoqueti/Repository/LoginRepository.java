package scenebuilder.com.example.estoqueti.Repository;


import scenebuilder.com.example.estoqueti.Connection.ConnectBD;
import scenebuilder.com.example.estoqueti.Model.Login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginRepository {

//    public boolean validaLoginAdm(Login dadoslogin){
//
//        String sql = "SELECT * FROM administrators WHERE usuario = ? AND senha = ?";
//
//        try{
//            PreparedStatement ps = ConnectBD.getConnection().prepareStatement(sql);
//            ps.setString(1, dadoslogin.getUsuario());
//            ps.setString(2, dadoslogin.getSenha());
//            ResultSet resultSet = ps.executeQuery();
//            dadoslogin.setTipoLogin(1);
//            return resultSet.next();
//
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//
//    }


    public Login validaLoginUser(Login dadosLogin) {
        String sql = "SELECT useradm FROM usuarios WHERE usuario = ? AND senha = ?";

        try (PreparedStatement ps = ConnectBD.getConnection().prepareStatement(sql)) {
            ps.setString(1, dadosLogin.getUsuario());
            ps.setString(2, dadosLogin.getSenha());

            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) { // Verifica se há resultados
                    int admUser = resultSet.getInt("useradm");
                    dadosLogin.setTipoLogin(admUser); // Define o tipo de login com base no valor retornado
                    return dadosLogin;
                } else {
                    return null; // Retorna null se login ou senha estiverem errados
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
