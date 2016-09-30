package aulaso20161.trabalhofinal.model.request;

import aulaso20161.trabalhofinal.dao.voley.DataResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by anderson on 05/11/2015.
 */
public class BaseRequest extends DataResponse{


    @Expose
    @SerializedName("exception")
    private String exception;

    @Expose
    @SerializedName("result")
    private String result;

    public String getException() {
        if(exception == null)
            return "";
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean success() {
        try {
            return result.equalsIgnoreCase("1");
        }catch (Exception ex) {
            return false;
        }
    }
}
