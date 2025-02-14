package scenebuilder.com.example.estoqueti.Model;

import java.sql.Date;

public class Estoque {

    private String descricaoProduto;
    private int quantidade;
    private int estoqueReal;
    private Date data;
    private float valorUnit;
    private float valorTotal;
    private String numeroNf;

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getEstoqueReal() {
        return quantidade;
    }

    public void setEstoqueReal(int estoqueReal) {
        this.estoqueReal = quantidade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public float getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(float valorUnit) {
        this.valorUnit = valorUnit;
    }

    public float getValorTotal() {
        return valorTotal = valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorUnit * quantidade;
    }

    public String getNumeroNf() {
        return numeroNf;
    }

    public void setNumeroNf(String numeroNf) {
        this.numeroNf = numeroNf;
    }
}
