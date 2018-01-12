package com.example.osvaldoairon.petshopapp.Rep;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by osvaldoairon on 12/01/2018.
 */

public class SQLPet extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "petshop";
    private static final int VERSAO_BANCO = 1;

    public  static final String NOME_TABELA = "petshop_table";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_ESPECIE = "ESPECIE";
    public static final String COLUNA_RACA = "raca";
    public static final String COLUNA_SEXO = "sexo";
    public static final String COLUNA_NASCIMENTO = "nascimento";
    public static final String COLUNA_INFORMACAO = "informacao";
    public static final String COLUNA_FOTO = "fotos";


    public SQLPet(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS " + NOME_TABELA + " ( " + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + COLUNA_NOME + " TEXT NOT NULL , " +
                COLUNA_ESPECIE + " TEXT NOT NULL , " + COLUNA_RACA + " TEXT NOT NULL , " + COLUNA_SEXO + " TEXT NOT NULL ," +
        COLUNA_NASCIMENTO + " TEXT NOT NULL ," + COLUNA_INFORMACAO + " TEXT NOT NULL , " + COLUNA_FOTO + " blob )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
