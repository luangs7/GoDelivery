package aulaso20161.trabalhofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import aulaso20161.trabalhofinal.adapter.EntregasAdapter;
import aulaso20161.trabalhofinal.bd.UserDAO;
import aulaso20161.trabalhofinal.model.EntregaModel;

public class ListaEntregas extends AppCompatActivity {
    Toolbar toolbar;
    ListView listView;
    EntregasAdapter adapter;
    UserDAO dao;
    ArrayList<EntregaModel> entregas = new ArrayList<>();
    ArrayList<EntregaModel> atualizados = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_entregas);

        dao = new UserDAO(this);
        listView = (ListView) findViewById(R.id.lista);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Lista de Entregas");
            toolbar.setNavigationIcon(R.drawable.back);
            setSupportActionBar(toolbar);
        }
        entregas = dao.listarAll();
        adapter = new EntregasAdapter(getBaseContext(),entregas);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EntregaModel entrega = (EntregaModel) parent.getItemAtPosition(position);
                Intent it = new Intent(ListaEntregas.this,DetalhePedido.class);
                it.putExtra("entrega",entrega);
                it.putExtra("atualizados",atualizados);
                startActivity(it);
            }
        });

        Intent it = getIntent();
        if(it.hasExtra("atualizados")) {
            atualizados = (ArrayList<EntregaModel>) it.getSerializableExtra("atualizados");
        }

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_simple, menu);
//        return true;
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void detalhar(View view) {
        startActivity(new Intent(this, DetalhePedido.class));
    }
}
