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

    public void registraSaida(Saida saida, String descricaoProduto){

        try {
            String sql = "INSERT INTO movimentacoes (nomeUsuario, descricaoProduto, setor, idChamadoGLPI, data, qtdProduto) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = ConnectBD.getConnection().prepareStatement(sql);
            ps.setString(1, saida.getNomeUsuario());
            ps.setString(2, saida.getDescProduto());
            ps.setString(3, saida.getNomeSetor());
            ps.setString(4, saida.getChamado());
            ps.setDate(5, new java.sql.Date(saida.getDataSaida().getTime()));
            ps.setInt(6,saida.getQuantidade());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("ERRO NA INSERÇÃO DA MOVIMENTAÇÃO: " + e.getMessage());

        }

        try {
            String sqlSelect = "SELECT qtdEstoqueReal FROM produtos WHERE descricao = ?";
            PreparedStatement ps1 = ConnectBD.getConnection().prepareStatement(sqlSelect);
            ps1.setString(1, descricaoProduto);
            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {
                System.out.println("VAI TIRAR");
                int quantidadeAtual = rs.getInt("qtdEstoqueReal");
                int novaQuantidade = quantidadeAtual - saida.getQuantidade();

                try {
                    String sqlUpdate = "UPDATE produtos SET qtdEstoqueReal = ? WHERE descricao = ?";
                    PreparedStatement sqlUpdate1 = ConnectBD.getConnection().prepareStatement(sqlUpdate);
                    sqlUpdate1.setInt(1, novaQuantidade);
                    sqlUpdate1.setString(2, descricaoProduto);

                    int rowsUpdated = sqlUpdate1.executeUpdate();

                } catch (Exception e) {
                    System.out.println("ERRO NA ALTERAÇÃO DO PRODUTO: " + e.getMessage());
                    e.printStackTrace();  // Para imprimir o erro completo
                }
            } else {
                System.out.println("Produto não encontrado no BD.");
            }
        } catch (Exception e) {
            System.out.println("ERRO NO SELECT PARA PEGAR A QUANTIDADE REAL: " + e.getMessage());
        }





    }



}




