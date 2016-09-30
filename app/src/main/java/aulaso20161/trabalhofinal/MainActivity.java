package aulaso20161.trabalhofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import java.util.ArrayList;

import aulaso20161.trabalhofinal.dao.local.LocalDbImplement;
import aulaso20161.trabalhofinal.dao.voley.CallListener;
import aulaso20161.trabalhofinal.dao.voley.OnDialogButtonClick;
import aulaso20161.trabalhofinal.dao.webservice.EntregaDao;
import aulaso20161.trabalhofinal.model.BaseModel;
import aulaso20161.trabalhofinal.model.EntregaModel;
import aulaso20161.trabalhofinal.model.UserModel;
import aulaso20161.trabalhofinal.model.request.BaseRequest;
import aulaso20161.trabalhofinal.model.request.UsuarioRequest;

public class MainActivity extends AppCompatActivity {

    EditText User;
    EditText Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User = (EditText) findViewById(R.id.user);
        Pass = (EditText) findViewById(R.id.senha);



    }

    public void logar(View view) {
        getdata();
    }



    public void getdata(){

        OnDialogButtonClick onDialogButtonClick = new OnDialogButtonClick() {
            @Override
            public void onPositiveClick() {
                getdata();
            }

            @Override
            public void onNegativeClick() {

            }
        };

        CallListener<UsuarioRequest> callListener = new CallListener<UsuarioRequest>(this, "Buscando dados", onDialogButtonClick) {
            @Override
            public void onResponse(UsuarioRequest request) {
                super.onResponse(request);
                if (request.success()) {
                    new LocalDbImplement<BaseModel>(MainActivity.this).save(request.getItens().get(0));
                    Intent intent = new Intent(getApplication(), Dash.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else{
                    Toast.makeText(getBaseContext(), "Erro no login, confira seus dados!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        };


        new EntregaDao(getBaseContext()).login(callListener, Pass.getText().toString(), User.getText().toString());
    }

}
