package aulaso20161.trabalhofinal.dao.webservice;


import android.content.Context;

import com.android.volley.Request;
import com.google.gson.Gson;

import aulaso20161.trabalhofinal.dao.custom.CustomPostResquest;
import aulaso20161.trabalhofinal.dao.custom.CustomResquest;
import aulaso20161.trabalhofinal.dao.local.LocalDbImplement;
import aulaso20161.trabalhofinal.dao.voley.CallListener;
import aulaso20161.trabalhofinal.dao.voley.GerenicAbstractDaoImp;
import aulaso20161.trabalhofinal.model.BaseModel;
import aulaso20161.trabalhofinal.model.EntregaModel;
import aulaso20161.trabalhofinal.model.request.EntregaRequest;
import aulaso20161.trabalhofinal.model.request.UsuarioRequest;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by luan on 05/04/2016
 */
public class EntregaDao extends GerenicAbstractDaoImp {

    public EntregaDao(Context context) {
        super(context);
    }


    public void getEntregas(CallListener callListener, ArrayList<EntregaModel> atualizados) {
        BaseModel user = new LocalDbImplement<BaseModel>(context).getDefault(BaseModel.class);

        String url = serverUrl + "webservices/sincronizar.php?idUser="+user.getId();

        HashMap<String, String> map = new HashMap<>();

        map.put("Atualizados", new Gson().toJson(atualizados));

       CustomResquest request = new CustomResquest(EntregaRequest.class, Request.Method.POST, url, map, callListener, callListener);
       addRequest(request);
    }

    public void getVisao(CallListener callListener, ArrayList<BaseModel> dependencia) {
//        String url = serverUrl + "getters/getVisao.php";
//        HashMap<String, String> map = new HashMap<>();
//
//        map.put("dependencia", new Gson().toJson(dependencia));
//
   //     CustomResquest request = new CustomResquest(EntregaRequest.class, Request.Method.GET, url, null, callListener, callListener);
//        addRequest(request);
    }

    public void login(CallListener callListener, String pass, String email){
        HashMap<String, String> map = new HashMap<>();
        map.put("pass",pass);
        map.put("email",email);

        String url = serverUrl + "safe/login.php";

        CustomPostResquest request = new CustomPostResquest(UsuarioRequest.class, Request.Method.POST, url, map, callListener, callListener);
        addRequest(request);
    }


}
