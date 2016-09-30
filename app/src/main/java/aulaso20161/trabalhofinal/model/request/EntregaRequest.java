package aulaso20161.trabalhofinal.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import aulaso20161.trabalhofinal.model.EntregaModel;

/**
 * Created by Dev_Maker on 11/04/2016.
 */
public class EntregaRequest extends BaseRequest{

    @Expose
    @SerializedName("content")
    public List<EntregaModel> itens;

    public List<EntregaModel> getItens() {
        return itens;
    }

    public void setItens(List<EntregaModel> itens) {
        this.itens = itens;
    }

}
