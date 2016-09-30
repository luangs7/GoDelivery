package aulaso20161.trabalhofinal.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.Toast;

import aulaso20161.trabalhofinal.model.BaseModel;
import aulaso20161.trabalhofinal.model.EntregaModel;
import aulaso20161.trabalhofinal.model.UserModel;

import java.util.ArrayList;

/**
 * Created by Dev_Maker on 24/03/2016.
 */
public class UserDAO {
    private SQLiteDatabase bd;
    private Context context;

    public UserDAO(Context context) {
        this.context = context;
        BDCore auxBd = new BDCore(context);
        bd = auxBd.getWritableDatabase();

    }

    public void Editar(String id, Integer status){
        ContentValues valores = new ContentValues();
        valores.put("status", status);

        bd.update("Entregas", valores, "_id = ?", new String[]{"" + id});

    }

    public void Insere(EntregaModel entrega){
        //for (EntregaModel entrega:entregas) {
            ContentValues valores = new ContentValues();
            valores.put("idEntrega",entrega.getIdEntrega());
            valores.put("endereco",entrega.getEndereco());
            valores.put("descricao",entrega.getDescricao());
            valores.put("url",entrega.getFoto());
            valores.put("status",entrega.getStatus());
            valores.put("data",entrega.getData());

            try {
                bd.insert("Entregas", null, valores);
            } catch (Exception e) {
                Log.e("Erro insert", e.getMessage());
                Toast.makeText(context, "Erro ao sincronizar", Toast.LENGTH_SHORT).show();
            }
       // }

    }

    public boolean InsereAtualizado(EntregaModel entrega){
        //for (EntregaModel entrega:entregas) {
        ContentValues valores = new ContentValues();
        valores.put("endereco",entrega.getEndereco());
        valores.put("descricao",entrega.getDescricao());
        valores.put("url",entrega.getFoto());
        valores.put("status",entrega.getStatus());
        valores.put("data",entrega.getData());
        valores.put("idEntrega",entrega.getIdEntrega());

        try {
            SQLiteStatement s = bd.compileStatement("select count(*) from Atualizados where idEntrega = " + entrega.getIdEntrega());
            long count = s.simpleQueryForLong();

//           Cursor cursor = bd.rawQuery("SELECT * FROM Atualizados WHERE idEntrega = " + entrega.getIdEntrega(), null);
            if(count > 0) {
                bd.update("Atualizados", valores, "idEntrega = ?", new String[]{entrega.getIdEntrega()});
            }
            else {
                bd.insert("Atualizados", null, valores);
            }
            return true;
        } catch (Exception e) {
            Log.e("Erro insert", e.getMessage());
            Toast.makeText(context, "Erro ao sincronizar", Toast.LENGTH_SHORT).show();
            return false;
        }
        // }

    }

    public boolean deleteAll(){
        try{
            bd.delete("Entregas", null, null);

            return true;
        }catch (Exception e) {
            Log.e("Erro insert", e.getMessage());
            return false;
        }
    }

    public boolean deleteAtualizados(){
        try{
            bd.delete("Atualizados", null, null);

            return true;
        }catch (Exception e) {
            Log.e("Erro insert", e.getMessage());
            return false;
        }
    }


    public ArrayList<EntregaModel> listarAll(){
        String[] colunas = new String[]{"_id", "idEntrega", "endereco","descricao","url","status","data"};
//        "nome LIKE '%"+nome+"%'"
          //Cursor cursor = bd.rawQuery("select * from Entregas", null);
        Cursor cursor = bd.query("Entregas", colunas, null, null, null, null, "endereco");
        ArrayList<EntregaModel> list = new ArrayList<EntregaModel>();

        try {
        if (cursor.moveToFirst()) {
            do {

                EntregaModel entrega = new EntregaModel();
                entrega.setId(cursor.getString(0));
                entrega.setIdEntrega(cursor.getString(1));
                entrega.setEndereco(cursor.getString(2));
                entrega.setDescricao(cursor.getString(3));
                entrega.setFoto(cursor.getString(4));
                entrega.setStatus(cursor.getString(5));
                entrega.setData(cursor.getString(6));
                list.add(entrega);

            } while (cursor.moveToNext());
        }

    }catch (Exception e){
        System.out.println(e);
    }
        return list;
    }


    public ArrayList<EntregaModel> listarAtualizados(){
        String[] colunas = new String[]{"_id", "idEntrega", "endereco","descricao","url","status","data"};
//        "nome LIKE '%"+nome+"%'"
        //Cursor cursor = bd.rawQuery("select * from Entregas", null);
        Cursor cursor = bd.query("Atualizados", colunas, null, null, null, null, "endereco");
        ArrayList<EntregaModel> list = new ArrayList<EntregaModel>();

        try {
            if (cursor.moveToFirst()) {
                do {

                    EntregaModel entrega = new EntregaModel();
                    entrega.setId(cursor.getString(0));
                    entrega.setIdEntrega(cursor.getString(1));
                    entrega.setEndereco(cursor.getString(2));
                    entrega.setDescricao(cursor.getString(3));
                    entrega.setFoto(cursor.getString(4));
                    entrega.setStatus(cursor.getString(5));
                    entrega.setData(cursor.getString(6));
                    list.add(entrega);

                } while (cursor.moveToNext());
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }


}
