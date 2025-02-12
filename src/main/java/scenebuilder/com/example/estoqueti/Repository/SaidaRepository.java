package scenebuilder.com.example.estoqueti.Repository;

import scenebuilder.com.example.estoqueti.Connection.ConnectBD;
import scenebuilder.com.example.estoqueti.Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SaidaRepository {


    public List<Produto> exibeProdutos(){

        List<Produto> resultsBD = new ArrayList<>();


        String sql = "SELECT * FROM produtos";

        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Produto produto = new Produto();
                produto.setDescricao(rs.getString("descricao"));
                produto.GetQtdEstoqueReal(rs.getInt("qtdEstoqueReal"));
                produto.setDataInventario(rs.getDate("dataInventario"));


                resultsBD.add(produto);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
           return resultsBD;
    }

    public List<Setor> listaSetores(){

        List<Setor> resultsBD = new ArrayList<>();

        String sql = "SELECT * FROM setor";

        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Setor setor = new Setor();
                setor.setIdSetor(rs.getInt("idSetor"));
                setor.setNomeSetor(rs.getString("nome"));



                resultsBD.add(setor);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultsBD;

    }

    public void registraSaida(Movimentacoes movimentacao, int idProduto){

        List<Movimentacoes> resultsBD = new ArrayList<>();
    }

}


