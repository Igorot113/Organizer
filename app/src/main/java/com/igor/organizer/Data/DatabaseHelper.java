package com.igor.organizer.Data;

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
    public static final String COL_USER = "user";
    public static final String TABELA_USER = "users";
    public static final String COL_USER_ID = "id";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABELA_ITENS + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_TITULO + " TEXT NOT NULL, " +
            COL_DESCRICAO + " TEXT, " +
            COL_USER + " INTEGER NOT NULL, " +
            "FOREIGN KEY(" + COL_USER + ") REFERENCES " + TABELA_USER + "(" + COL_USER_ID + ") ON DELETE CASCADE" +
            ");";
    public static final String CREATE_TABLE_USER = "CREATE TABLE " + TABELA_USER + " ("
            + COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_USERNAME + " TEXT NOT NULL UNIQUE, "
            + COL_PASSWORD + " TEXT);";
    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_ITENS);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_USER);
        onCreate(db);
    }
}
