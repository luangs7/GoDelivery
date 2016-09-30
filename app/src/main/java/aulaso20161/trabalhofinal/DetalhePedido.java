package aulaso20161.trabalhofinal;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import aulaso20161.trabalhofinal.bd.UserDAO;
import aulaso20161.trabalhofinal.model.EntregaModel;

public class DetalhePedido extends AppCompatActivity {
    ImageView image;
    Toolbar toolbar;
    TextView txtnome, txtendereco;
    Spinner status;
    Button btnEdit, btnExit;
    ArrayList<EntregaModel> atualizados = new ArrayList<>();
    UserDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_pedido);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Detalhes");
            toolbar.setNavigationIcon(R.drawable.back);
            setSupportActionBar(toolbar);
        }

        txtnome = (TextView) findViewById(R.id.txtnome);
        txtendereco = (TextView) findViewById(R.id.txtendereco);
        status = (Spinner) findViewById(R.id.statusSpinner);
        btnEdit = (Button) findViewById(R.id.edtButton);
        btnExit = (Button) findViewById(R.id.btnexit);

        List<String> list = new ArrayList<>();
        list.add("A ser entregue");
        list.add("Tentativa sem sucesso");
        list.add("Entregue");

        dao = new UserDAO(this);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,list);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        status.setAdapter(dataAdapter);


        Intent it = getIntent();
        final EntregaModel entrega = (EntregaModel) it.getSerializableExtra("entrega");

        txtendereco.setText(entrega.getEndereco());
        txtnome.setText(entrega.getDescricao());
        status.setSelection(Integer.parseInt(entrega.getStatus()));

        image = (ImageView) findViewById(R.id.img);

        if(it.hasExtra("atualizados")) {
            atualizados = (ArrayList<EntregaModel>) it.getSerializableExtra("atualizados");
        }


        //picasso é uma lib para imagem. Faz o cache da imagem para o celular e grava offline.
        Picasso
                .with(this)
                .load(entrega.getFoto())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .fit()
                .centerInside()
                .into(image, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        // Try again online if cache failed
                        Picasso.with(getBaseContext())
                                .load(Uri.parse(entrega.getFoto()))
                                .placeholder(R.drawable.placeholder)
                                .error(R.drawable.placeholder)
                                .fit()
                                .centerInside()
                                        .into(image);
                    }


                });


        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                entrega.setStatus(String.valueOf(position));
                Log.e("spinner","spinner");
                }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Iterator<EntregaModel> iterator = atualizados.iterator();
//                if (iterator.hasNext()) {
//                    String idEntrega = iterator.next().getIdEntrega();
//                    if (idEntrega.equalsIgnoreCase(entrega.getIdEntrega())) {
//                            iterator.remove();
//                            dao.InsereAtualizado(entrega);
//                        } else {
//                            dao.InsereAtualizado(entrega);
//                        }
//                }else{
//                    dao.InsereAtualizado(entrega);
//                }
                if(dao.InsereAtualizado(entrega)) {
                    Intent it = new Intent(getBaseContext(), Dash.class);
                    it.putExtra("atualizados", atualizados);
                    Toast.makeText(getBaseContext(), "Editado com sucesso!", Toast.LENGTH_SHORT).show();
                    startActivity(it);
                }else
                    Toast.makeText(getBaseContext(), "Erro ao editar!", Toast.LENGTH_SHORT).show();
            }
        });
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



}
