package scenebuilder.com.example.estoqueti.Repository;

import javafx.scene.control.Alert;
import scenebuilder.com.example.estoqueti.Connection.ConnectBD;
import scenebuilder.com.example.estoqueti.Model.Estoque;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EstoqueRepository {

    public void adicionaProduto(Estoque estoque){

        try {
            String selectProdutos = "SELECT descricao from produtos WHERE descricao = ?";
            PreparedStatement select = ConnectBD.getConnection().prepareStatement(selectProdutos);
            select.setString(1, estoque.getDescricaoProduto());

            ResultSet resultSet = select.executeQuery();

            if (resultSet.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERRO:");
                alert.setHeaderText("Produto já cadastrado no sistema.");
                alert.showAndWait();
                return;
            } else {
                try {
                    String sql = "INSERT INTO produtos (descricao, qtdEstoque, valorUnit, valorTotalEstoque, qtdEstoqueReal, dataInventario, numeroNf) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement ps = ConnectBD.getConnection().prepareStatement(sql);
                    ps.setString(1, estoque.getDescricaoProduto());
                    ps.setInt(2, estoque.getQuantidade());
                    ps.setFloat(3, estoque.getValorUnit());
                    ps.setFloat(4, estoque.getValorTotal());
                    ps.setInt(5, estoque.getEstoqueReal());
                    ps.setDate(6, new java.sql.Date(estoque.getData().getTime()));
                    ps.setString(7, estoque.getNumeroNf());
                    ps.executeUpdate();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informação");
                    alert.setHeaderText("Produto cadastrado com sucesso.");
                    alert.showAndWait();
                } catch (Exception inserc) {
                    System.out.println("ERRO NA INSERÇÃO DE UM PRODUTO: " + inserc.getMessage());
                }
            }

            }catch (Exception e){
            System.out.println("ERRO NA VERIFICAÇÃO DE UM PRODUTO: " + e.getMessage());
        }
    }
}
