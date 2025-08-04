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
    public Controller(Context context){
        dao = new ItemDAO(context);
        list = dao.listar();
        adapter = new Adapter(context, list);
    }
    public void Add(Item item){
        dao.inserir(item);
        list.clear();
        list.addAll(dao.listar());
        adapter.notifyDataSetChanged();
    }
    public void remove(Item item,int position){
        dao.remover(item);
        list.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemChanged(position, list.size() - position);
    }
    public void update(int position,Item novoitem){
        Item antigo = list.get(position);
        dao.atualizar(antigo,novoitem);
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
