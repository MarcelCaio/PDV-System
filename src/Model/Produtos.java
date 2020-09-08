/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author marce
 */
public class Produtos {
    
    private int id;
    private String codBarras;
    private String descricao;
    private float estoque;
    private String unidade;
    private double precoCompra;
    private float lucroPercen;
    private double lucroReal;
    private double precoVenda;
    private int ncm;
    private int ipi;
    private int icms;
    private float frete;
    private double precoPromo;
    private float percenPromo;
    private String validadePromo;
    private Fornecedores fornecedores;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getEstoque() {
        return estoque;
    }

    public void setEstoque(float estoque) {
        this.estoque = estoque;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public float getLucroPercen() {
        return lucroPercen;
    }

    public void setLucroPercen(float lucroPercen) {
        this.lucroPercen = lucroPercen;
    }

    public double getLucroReal() {
        return lucroReal;
    }

    public void setLucroReal(double lucroReal) {
        this.lucroReal = lucroReal;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getNcm() {
        return ncm;
    }

    public void setNcm(int ncm) {
        this.ncm = ncm;
    }

    public int getIpi() {
        return ipi;
    }

    public void setIpi(int ipi) {
        this.ipi = ipi;
    }

    public int getIcms() {
        return icms;
    }

    public void setIcms(int icms) {
        this.icms = icms;
    }

    public float getFrete() {
        return frete;
    }

    public void setFrete(float frete) {
        this.frete = frete;
    }

    public double getPrecoPromo() {
        return precoPromo;
    }

    public void setPrecoPromo(double precoPromo) {
        this.precoPromo = precoPromo;
    }

    public float getPercenPromo() {
        return percenPromo;
    }

    public void setPercenPromo(float percenPromo) {
        this.percenPromo = percenPromo;
    }

    public String getValidadePromo() {
        return validadePromo;
    }

    public void setValidadePromo(String validadePromo) {
        this.validadePromo = validadePromo;
    }

    public Fornecedores getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(Fornecedores fornecedores) {
        this.fornecedores = fornecedores;
    }
    
    
}
