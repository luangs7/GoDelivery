package aulaso20161.trabalhofinal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EntregaModel implements Serializable{

   @SerializedName("id")
   @Expose
   private String id;
   @SerializedName("endereco")
   @Expose
   private String endereco;
   @SerializedName("descricao")
   @Expose
   private String descricao;
   @SerializedName("status")
   @Expose
   private String status;
   @SerializedName("foto")
   @Expose
   private String foto;
   @SerializedName("idEntrega")
   @Expose
   private String idEntrega;
   @SerializedName("idFuncionario")
   @Expose
   private String idFuncionario;
   @SerializedName("data")
   @Expose
   private String data;

   /**
    *
    * @return
    * The id
    */
   public String getId() {
      return id;
   }

   /**
    *
    * @param id
    * The id
    */
   public void setId(String id) {
      this.id = id;
   }

   /**
    *
    * @return
    * The endereco
    */
   public String getEndereco() {
      return endereco;
   }

   /**
    *
    * @param endereco
    * The endereco
    */
   public void setEndereco(String endereco) {
      this.endereco = endereco;
   }

   /**
    *
    * @return
    * The descricao
    */
   public String getDescricao() {
      return descricao;
   }

   /**
    *
    * @param descricao
    * The descricao
    */
   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }

   /**
    *
    * @return
    * The status
    */
   public String getStatus() {
      return status;
   }

   /**
    *
    * @param status
    * The status
    */
   public void setStatus(String status) {
      this.status = status;
   }

   /**
    *
    * @return
    * The foto
    */
   public String getFoto() {
      return foto;
   }

   /**
    *
    * @param foto
    * The foto
    */
   public void setFoto(String foto) {
      this.foto = foto;
   }

   /**
    *
    * @return
    * The idEntrega
    */
   public String getIdEntrega() {
      return idEntrega;
   }

   /**
    *
    * @param idEntrega
    * The idEntrega
    */
   public void setIdEntrega(String idEntrega) {
      this.idEntrega = idEntrega;
   }

   /**
    *
    * @return
    * The idFuncionario
    */
   public String getIdFuncionario() {
      return idFuncionario;
   }

   /**
    *
    * @param idFuncionario
    * The idFuncionario
    */
   public void setIdFuncionario(String idFuncionario) {
      this.idFuncionario = idFuncionario;
   }

   /**
    *
    * @return
    * The data
    */
   public String getData() {
      return data;
   }

   /**
    *
    * @param data
    * The data
    */
   public void setData(String data) {
      this.data = data;
   }

}
