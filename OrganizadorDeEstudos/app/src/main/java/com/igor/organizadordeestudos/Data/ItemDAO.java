package com.igor.organizadordeestudos.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.igor.organizadordeestudos.Modelo.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private SQLiteDatabase db;

    public ItemDAO(Context context){
        DatabaseHelper helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public long inserir(Item item){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_TITULO,item.getTexto());
        values.put(DatabaseHelper.COL_DESCRICAO,item.getDescricao());
        return db.insert(DatabaseHelper.TABELA_ITENS,null,values);
    }
    public List<Item> listar(){
        List<Item> itens = new ArrayList<>();
        Cursor cursor = db.query(DatabaseHelper.TABELA_ITENS,
                null,null,null,null,null,DatabaseHelper.COL_ID + " DESC");
        while (cursor.moveToNext()){
            String titulo = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_TITULO));
            String descricao = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_DESCRICAO));
            itens.add(new Item(titulo,descricao));
        }
        cursor.close();
        return itens;
    }
    public int atualizar(Item antigo,Item novo){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_TITULO,novo.getTexto());
        values.put(DatabaseHelper.COL_DESCRICAO,novo.getDescricao());
        return db.update(DatabaseHelper.TABELA_ITENS,values,
                DatabaseHelper.COL_TITULO + " = ? AND " + DatabaseHelper.COL_DESCRICAO + " = ?",
                new String[]{antigo.getTexto(), antigo.getDescricao()});
    }
    public int remover(Item item){
        return db.delete(DatabaseHelper.TABELA_ITENS,
                DatabaseHelper.COL_TITULO + " = ? AND " + DatabaseHelper.COL_DESCRICAO + " = ?",
                new String[]{item.getTexto(), item.getDescricao()});
    }
}
