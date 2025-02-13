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
                produto.GetQtdEstoqueReal(rs.getInt("qtdEstoqueReal"));
                produto.setDataInventario(rs.getDate("dataInventario"));
                produto.setNumeroNf(rs.getString("numeroNf"));

                resultsBD.add(produto);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultsBD;


    }

    public void editarProduto(Produto produto, int idProduto){

        try {
            String sql = "UPDATE produtos SET descricao=?, qtdEstoque=?, valorUnit=?, valorTotalEstoque=?, qtdEstoqueReal=?, dataInventario=?, numeroNf=? WHERE idprodutos=?";

            PreparedStatement ps = ConnectBD.getConnection().prepareStatement(sql);

            ps.setString(1, produto.getDescricao());
            ps.setInt(2, produto.getQtdEstoque());
            ps.setFloat(3, produto.getValorUnit());
            ps.setFloat(4, produto.getValorTotalEstoqueProduto());
            ps.setInt(5, produto.getGetQtdEstoqueReal());
            ps.setDate(6, new java.sql.Date(produto.getDataInventario().getTime()));
            ps.setString(7, produto.getNumeroNf());
            ps.setInt(8, produto.getIdProduto());

            ps.executeUpdate();


        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }


    }
}
