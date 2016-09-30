package aulaso20161.trabalhofinal.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Dev_Maker on 24/03/2016.
 */
public class BDCore extends SQLiteOpenHelper {

    public static final String Nome_BD = "User";
    private static final int VERSAO_BD = 1;
    private static final String SQLCreate = "create table Entregas (_id integer primary key autoincrement, idEntrega integer, endereco text not null, descricao text not null, url text not null, status integer, data text not null);";
    private static final String SQLCreate2 = "create table Atualizados (_id integer primary key autoincrement, idEntrega integer, endereco text not null, descricao text not null, url text not null, status integer, data text not null);";

    public BDCore(Context context) {
        super(context, Nome_BD, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(SQLCreate);
            db.execSQL(SQLCreate2);
        }catch (Exception e){
            Log.e("Erro de DB","DB",e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.execSQL("DROP TABLE Entregas");
            db.execSQL("DROP TABLE Atualizados");
            db.execSQL(SQLCreate);
            db.execSQL(SQLCreate2);
        }catch (Exception e){
            Log.e("Erro de DB","DB",e);
        }

    }
}

