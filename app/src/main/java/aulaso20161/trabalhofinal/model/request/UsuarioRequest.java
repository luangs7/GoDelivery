package aulaso20161.trabalhofinal.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import aulaso20161.trabalhofinal.model.BaseModel;

/**
 * Created by Dev_Maker on 04/04/2016.
 */
public class UsuarioRequest extends BaseRequest{

    @Expose
    @SerializedName("content")
    public List<BaseModel> itens;

    public List<BaseModel> getItens() {
        return itens;
    }

    public void setItens(List<BaseModel> itens) {
        this.itens = itens;
    }
}
