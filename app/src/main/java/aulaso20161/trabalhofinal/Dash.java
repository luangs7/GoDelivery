package aulaso20161.trabalhofinal;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import java.util.ArrayList;

import aulaso20161.trabalhofinal.adapter.EntregasAdapter;
import aulaso20161.trabalhofinal.bd.UserDAO;
import aulaso20161.trabalhofinal.dao.local.LocalDbImplement;
import aulaso20161.trabalhofinal.dao.voley.CallListener;
import aulaso20161.trabalhofinal.dao.voley.OnDialogButtonClick;
import aulaso20161.trabalhofinal.dao.webservice.EntregaDao;
import aulaso20161.trabalhofinal.model.BaseModel;
import aulaso20161.trabalhofinal.model.EntregaModel;
import aulaso20161.trabalhofinal.model.UserModel;
import aulaso20161.trabalhofinal.model.request.EntregaRequest;

public class Dash extends AppCompatActivity {
    TextView sincronizar, txtUserName;
    Toolbar toolbar;
    ArrayList<EntregaModel> lista = new ArrayList<>();
    UserDAO dao;
    ArrayList<EntregaModel> atualizados = new ArrayList<>();
    ListView listaPendentes;
    EntregasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        dao =  new UserDAO(getBaseContext());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Home");
            setSupportActionBar(toolbar);
        }
        atualizados = dao.listarAtualizados();


        sincronizar = (TextView) findViewById(R.id.txtSincronize);
        txtUserName = (TextView) findViewById(R.id.txtUserName);

        BaseModel user = new LocalDbImplement<BaseModel>(this).getDefault(BaseModel.class);

        txtUserName.setText(user.getName());
        sincronizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (existeConexao()) {
//                    ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "",
//                            "Sincronizando com o servidor...", false);
//                    timerDelayRemoveDialog(dialog);
                    getdata();
                } else {
                    Toast.makeText(getBaseContext(),"Sem rede! Conecte-se e tente novamente",Toast.LENGTH_SHORT).show();
                }

            }
        });


        listaPendentes = (ListView) findViewById(R.id.listView2);

        Intent it = getIntent();

//        if(it.hasExtra("atualizados")) {
//            atualizados = (ArrayList<EntregaModel>) it.getSerializableExtra("atualizados");
////            if (atualizados.size() > 0) {
////                adapter = new EntregasAdapter(getBaseContext(), atualizados);
////                listaPendentes.setAdapter(adapter);
//            }
        adapter = new EntregasAdapter(getBaseContext(), atualizados);
        listaPendentes.setAdapter(adapter);
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_simple, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }

        if (id == R.id.listar) {
            Intent it = new Intent(getBaseContext(),ListaEntregas.class);
            it.putExtra("atualizados",atualizados);
            startActivity(it);
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    public boolean existeConexao() {
        ConnectivityManager connectivity = (ConnectivityManager)
                this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo netInfo = connectivity.getActiveNetworkInfo();

            // Se não existe nenhum tipo de conexão retorna false
            if (netInfo == null) {
                return false;
            }

            int netType = netInfo.getType();

            // Verifica se a conexão é do tipo WiFi ou Mobile e
            // retorna true se estiver conectado ou false em
            // caso contrário
            if (netType == ConnectivityManager.TYPE_WIFI ||
                    netType == ConnectivityManager.TYPE_MOBILE) {
                return netInfo.isConnected();

            } else {
                return false;
            }
        } else {
            return false;
        }
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

        CallListener<EntregaRequest> callListener = new CallListener<EntregaRequest>(getBaseContext(), "Buscando dados", onDialogButtonClick) {
            @Override
            public void onResponse(EntregaRequest request) {
                super.onResponse(request);
                if (request.success()) {
                    lista.addAll(request.getItens());
                    if(dao.deleteAll()) {
                        for (EntregaModel entrega :
                                lista) {
                            dao.Insere(entrega);
                        }
                        Toast.makeText(getBaseContext(), "Sincronizado com sucesso!", Toast.LENGTH_SHORT).show();
                        lista.clear();
                    }else{
                        Toast.makeText(getBaseContext(), "Houve um erro, tente novamente!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getBaseContext(), request.getException(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        };


        new EntregaDao(getBaseContext()).getEntregas(callListener,atualizados);
        dao.deleteAtualizados();
        atualizados.clear();
        adapter.notifyDataSetChanged();
    }
}
