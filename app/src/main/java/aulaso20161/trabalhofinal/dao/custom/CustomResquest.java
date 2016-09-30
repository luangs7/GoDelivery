package aulaso20161.trabalhofinal.dao.custom;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import aulaso20161.trabalhofinal.dao.voley.DefaultRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by anderson on 05/11/2015.
 */
public class CustomResquest<T> extends DefaultRequest {
    public CustomResquest(Class<T> type, String url, Map<String, String> params, Response.Listener<T> reponseListener, Response.ErrorListener errorListener) {
        super(type,url, params, reponseListener, errorListener);
    }

    public CustomResquest(Class<T> type,Context context, String url, Map<String, String> params, Response.Listener<T> reponseListener, Response.ErrorListener errorListener) {
        super(type,context, url, params, reponseListener, errorListener);
    }

    public CustomResquest(Class<T> type,int method, String url, Map<String, String> params, Response.Listener<T> reponseListener, Response.ErrorListener errorListener) {
        super(type,method, url, params, reponseListener, errorListener);
    }

    public CustomResquest(Class<T> type,Context context, int method, String url, Map<String, String> params, Response.Listener<T> reponseListener, Response.ErrorListener errorListener) {
        super(type,context, method, url, params, reponseListener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<>();
        //headers.put("Accept", "application/json");
        //headers.put("Content-Type", "application/json; charset=UTF-8");
        //headers.put("charset", "UTF-8");
        //headers.put("token","djs&&iofjsaiofjsaiofjasdoij90!&*@#8971jdjoidjsoiau");
        return headers;
    }
}
