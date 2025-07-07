package com.igor.organizadordeestudos.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "estudos.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABELA_ITENS = "itens";
    public static final String COL_ID = "id";
    public static final String COL_TITULO = "titulo";
    public static final String COL_DESCRICAO = "descricao";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABELA_ITENS + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_TITULO + " TEXT NOT NULL, " +
            COL_DESCRICAO + " TEXT);";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_ITENS);
        onCreate(db);
    }
}
