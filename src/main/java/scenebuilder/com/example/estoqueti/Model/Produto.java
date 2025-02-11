package scenebuilder.com.example.estoqueti.Model;

import java.util.Date;

public class Produto {


    private int idProduto;
    private String descricao;
    private Float valorUnit;
    private Float valorTotalEstoqueProduto;
    private Integer qtdEstoque;
    private Integer getQtdEstoqueReal;
    private Date dataInventario;
    private String numeroNf;

//    public Produto(int idProduto, String descricao, BigDecimal valorUnit, int qtdEstoque, int getQtdEstoqueReal, Date dataInventario, String numeroNf) {
//        this.idProduto = idProduto;
//        this.descricao = descricao;
//        this.valorUnit = valorUnit;
//        this.qtdEstoque = qtdEstoque;
//        this.getQtdEstoqueReal = getQtdEstoqueReal;
//        this.dataInventario = dataInventario;
//        this.numeroNf = numeroNf;
//    }


    public float getValorTotalEstoqueProduto() {
        return valorTotalEstoqueProduto;
    }

    public void setValorTotalEstoqueProduto(Float valorTotalEstoqueProduto) {
        this.valorTotalEstoqueProduto = valorTotalEstoqueProduto;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public void GetQtdEstoqueReal(Integer getQtdEstoqueReal) {
        this.getQtdEstoqueReal = getQtdEstoqueReal;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(float valorUnit) {
        this.valorUnit = valorUnit;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public int getGetQtdEstoqueReal() {
        return getQtdEstoqueReal;
    }

    public void setQtdEstoqueReal(int getQtdEstoqueReal) {
        this.getQtdEstoqueReal = getQtdEstoqueReal;
    }

    public Date getDataInventario() {
        return dataInventario;
    }

    public void setDataInventario(Date dataInventario) {
        this.dataInventario = dataInventario;
    }

    public String getNumeroNf() {
        return numeroNf;
    }

    public void setNumeroNf(String numeroNf) {
        this.numeroNf = numeroNf;
    }
}
