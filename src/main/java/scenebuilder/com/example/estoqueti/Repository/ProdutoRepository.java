package scenebuilder.com.example.estoqueti.Repository;

import scenebuilder.com.example.estoqueti.Connection.ConnectBD;
import scenebuilder.com.example.estoqueti.Model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {


    public List<Produto> listaProdutos(){

    List<Produto> resultsBD = new ArrayList<>();


    String sql = "SELECT * FROM produtos";

        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("idprodutos"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setQtdEstoque(rs.getInt("qtdEstoque"));
                produto.setValorUnit(rs.getFloat("valorUnit"));
                produto.setValorTotalEstoqueProduto(rs.getFloat("valorTotalEstoque"));
                produto.setGetQtdEstoqueReal(rs.getInt("qtdEstoqueReal"));
                produto.setDataInventario(rs.getDate("dataInventario"));
                produto.setNumeroNf(rs.getString("numeroNf"));

                resultsBD.add(produto);
                System.out.println(produto.getDescricao());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultsBD;


    }
}
