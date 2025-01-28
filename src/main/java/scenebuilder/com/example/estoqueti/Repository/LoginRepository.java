package scenebuilder.com.example.estoqueti.Repository;


import scenebuilder.com.example.estoqueti.Connection.ConnectBD;
import scenebuilder.com.example.estoqueti.Model.Login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginRepository {

    public boolean validaLoginAdm(Login dadoslogin){

        String sql = "SELECT * FROM administrators WHERE usuario = ? AND senha = ?";

        try{
            PreparedStatement ps = ConnectBD.getConnection().prepareStatement(sql);
            ps.setString(1, dadoslogin.getUsuario());
            ps.setString(2, dadoslogin.getSenha());

            ResultSet resultSet = ps.executeQuery();

            return resultSet.next();

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public boolean validaLoginUser(Login dadoslogin) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?";

        try{
            PreparedStatement ps = ConnectBD.getConnection().prepareStatement(sql);
            ps.setString(1, dadoslogin.getUsuario());
            ps.setString(2, dadoslogin.getSenha());

            ResultSet resultSet = ps.executeQuery();

            return resultSet.next();

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
