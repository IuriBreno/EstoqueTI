package scenebuilder.com.example.estoqueti.Repository;

import scenebuilder.com.example.estoqueti.Connection.ConnectBD;
import scenebuilder.com.example.estoqueti.Model.Movimentacoes;
import scenebuilder.com.example.estoqueti.Model.Setor;
import scenebuilder.com.example.estoqueti.Model.UsersPrivacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovimentacoesRepository {

    public List<Movimentacoes> listaTodasMovimentacoes(){

        List<Movimentacoes> resultsBD = new ArrayList<>();


        String sql = "SELECT * FROM movimentacoes";

        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Movimentacoes movimentacoes = new Movimentacoes();
                movimentacoes.setIdMovimentacao(rs.getInt("idmovimentacoes"));
                movimentacoes.setNomeUsuario(rs.getString("nomeUsuario"));
                movimentacoes.setDescProduto(rs.getString("descricaoProduto"));
                movimentacoes.setSetor(rs.getString("setor"));
                movimentacoes.setIdChamado(rs.getString("idChamadoGLPI"));
                movimentacoes.setQuantidade(rs.getInt("qtdProduto"));


                LocalDate data = rs.getDate("data").toLocalDate();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String dataFormatada = data.format(formatter);
                movimentacoes.setData(dataFormatada);



                resultsBD.add(movimentacoes);


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

    public List<UsersPrivacy> listaNomeUsuarios(){

        List<UsersPrivacy> resultsBD = new ArrayList<>();

        String sql = "SELECT * FROM usuarios";

        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                UsersPrivacy usersPrivacy = new UsersPrivacy();
                usersPrivacy.setNome(rs.getString("nome"));

                resultsBD.add(usersPrivacy);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultsBD;

    }

    public List<Movimentacoes> filtroMovimentacoes(Date dataInicio, Date dataFim, String chamado, String usuario, String setor) {

        List<Movimentacoes> movimentacoesFiltradas = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT * FROM movimentacoes where 1=1");

        if (dataInicio != null) {
            sql.append(" AND data >= ?");
        }
        if (dataFim != null) {
            sql.append(" AND data <= ?");
        }
        if (chamado != null && !chamado.isEmpty()) {
            sql.append(" AND idChamadoGLPI LIKE ?");
        }
        if (usuario != null && !usuario.isEmpty()) {
            sql.append(" AND nomeUsuario LIKE ?");
        }
        if (setor != null && !setor.isEmpty()) {
            sql.append(" AND setor LIKE ?");
        }

        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            // Preenche os parâmetros dinamicamente
            int index = 1;
            if (dataInicio != null) {
                stmt.setDate(index++, java.sql.Date.valueOf(String.valueOf(dataInicio)));
            }
            if (dataFim != null) {
                stmt.setDate(index++, java.sql.Date.valueOf(String.valueOf(dataFim)));
            }
            if (chamado != null && !chamado.isEmpty()) {
                stmt.setString(index++, "%" + chamado + "%");
            }
            if (usuario != null && !usuario.isEmpty()) {
                stmt.setString(index++, "%" + usuario + "%");
            }
            if (setor != null && !setor.isEmpty()) {
                stmt.setString(index++, "%" + setor + "%");
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Movimentacoes movimentacao = new Movimentacoes();
                movimentacao.setIdMovimentacao(rs.getInt("idmovimentacoes"));
                movimentacao.setNomeUsuario(rs.getString("nomeUsuario"));
                movimentacao.setDescProduto(rs.getString("descricaoProduto"));
                movimentacao.setSetor(rs.getString("setor"));
                movimentacao.setIdChamado(rs.getString("idChamadoGLPI"));
                movimentacao.setQuantidade(rs.getInt("qtdProduto"));
                LocalDate data = rs.getDate("data").toLocalDate();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String dataFormatada = data.format(formatter);
                movimentacao.setData(dataFormatada);
                movimentacoesFiltradas.add(movimentacao);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movimentacoesFiltradas;
    }

}
