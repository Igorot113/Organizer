package com.igor.organizer.AdapterItem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.igor.organizer.Modelo.Item;
import com.igor.organizer.R;
import com.igor.organizer.View.DetalhesActivity;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    private List<Item> lista;
    private Context context;
    private OnItemClickListener listener;
    public Adapter(Context context, List<Item> lista){
        this.context = context;
        this.lista = lista;
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewItem,textViewDescricao;
        Button btnVer;
        public ViewHolder(View itemView){
            super(itemView);
            textViewItem = itemView.findViewById(R.id.textViewItem);
            textViewDescricao = itemView.findViewById(R.id.textViewItemDescription);
            btnVer = itemView.findViewById(R.id.btnVerDetalhes);
        }
        public void bind(int position,OnItemClickListener listener){
            itemView.setOnClickListener(v -> {
                if(listener != null){
                    listener.onItemClick(position);
                }
            });
        }
    }
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        Item item = lista.get(position);
        holder.textViewItem.setText((position + 1) + ". " + item.getTexto());
        holder.textViewDescricao.setText(item.getDescricao());
        holder.bind(position,listener);
        holder.btnVer.setOnClickListener(v ->{
            Intent intent = new Intent(context, DetalhesActivity.class);
            intent.putExtra("titulo",item.getTexto());
            intent.putExtra("descricao",item.getDescricao());
            context.startActivity(intent);
        });
    }
    @Override
    public int getItemCount() {
        return lista.size();
    }
}
