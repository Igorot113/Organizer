package com.igor.organizer.Controller;

import android.content.Context;

import com.igor.organizer.AdapterItem.Adapter;
import com.igor.organizer.Data.ItemDAO;
import com.igor.organizer.Modelo.Item;

import java.util.List;

public class Controller {
    private final ItemDAO dao;
    private final Adapter adapter;
    private final List<Item> list;
    private final long userId;
    public Controller(Context context,long userId){
        this.userId = userId;
        dao = new ItemDAO(context);
        list = dao.listarPorUsuario(userId);
        adapter = new Adapter(context, list);
    }
    public void Add(Item item){
        long idGerado = dao.inserir(item, userId);
        item.setId(idGerado);
        list.add(0, item);
        //adapter.notifyItemInserted(0);
        adapter.notifyDataSetChanged();
    }
    public void remove(Item item,int position){
        dao.remover(item);
        list.remove(position);
        adapter.notifyItemRemoved(position);
        //adapter.notifyItemChanged(position, list.size() - position);
    }
    public void update(int position,Item novoitem){
        //Item antigo = list.get(position);
        dao.atualizar(novoitem);
        list.set(position,novoitem);
        adapter.notifyItemChanged(position);
    }
    public Adapter CreateAdapter(){
        return adapter;
    }
    public Adapter getAdapter(){
        return adapter;
    }
    public List<Item> getList(){
        return list;
    }
}
