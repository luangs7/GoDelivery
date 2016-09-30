package aulaso20161.trabalhofinal.model;

/**
 * Created by Dev_Maker on 24/03/2016.
 */
public class UserModel {

    private String id;
    private String nome;
    private String data;
    private int idProduto;
    private int favoritado;
    private String descricao;

    public UserModel() {
    }


    public UserModel(String id, String nome, String data, int idProduto, int favoritado, String descricao) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.idProduto = idProduto;
        this.favoritado = favoritado;
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int isFavoritado() {
        return favoritado;
    }

    public void setFavoritado(int favoritado) {
        this.favoritado = favoritado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
