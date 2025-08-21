package com.igor.organizer.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.igor.organizer.Modelo.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private SQLiteDatabase db;

    public ItemDAO(Context context){
        DatabaseHelper helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }
    public long inserir(Item item,long userId){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_TITULO,item.getTexto());
        values.put(DatabaseHelper.COL_DESCRICAO,item.getDescricao());
        values.put(DatabaseHelper.COL_USER,userId);
        return db.insert(DatabaseHelper.TABELA_ITENS,null,values);
    }
    public List<Item> listarPorUsuario(long userId){
        List<Item> itens = new ArrayList<>();
        String selection = DatabaseHelper.COL_USER + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};
        Cursor cursor = db.query(DatabaseHelper.TABELA_ITENS,
                null, selection, selectionArgs, null, null, DatabaseHelper.COL_ID + " DESC");
        while (cursor.moveToNext()){
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_ID));
            String titulo = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_TITULO));
            String descricao = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_DESCRICAO));
            Item item = new Item(titulo, descricao);
            item.setId(itemId);
            itens.add(item);
        }
        cursor.close();
        return itens;
    }

    public int atualizar(Item item){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_TITULO, item.getTexto());
        values.put(DatabaseHelper.COL_DESCRICAO, item.getDescricao());
        return db.update(DatabaseHelper.TABELA_ITENS, values,
                DatabaseHelper.COL_ID + " = ?", new String[]{String.valueOf(item.getId())});
    }

    public int remover(Item item){
        return db.delete(DatabaseHelper.TABELA_ITENS,
                DatabaseHelper.COL_ID + " = ?", new String[]{String.valueOf(item.getId())});
    }
}