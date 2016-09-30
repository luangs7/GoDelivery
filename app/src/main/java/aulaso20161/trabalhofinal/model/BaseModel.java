package aulaso20161.trabalhofinal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by luan on 23/06/2016.
 */
public class BaseModel implements Serializable{
    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("nome")
    private String name;

    @Expose
    @SerializedName("login")
    private String sigla;

    public BaseModel(String id, String name, String sigla) {
        this.id = id;
        this.name = name;
        this.sigla = sigla;
    }

    public BaseModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
