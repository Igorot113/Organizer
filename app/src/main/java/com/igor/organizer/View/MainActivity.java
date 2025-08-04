package com.igor.organizer.View;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.igor.organizer.AdapterItem.Adapter;
import com.igor.organizer.Controller.Controller;
import com.igor.organizer.Modelo.Item;
import com.igor.organizer.R;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button btnAdd;
    Controller controller;
    private int editPosition = -1;
    private final ActivityResultLauncher<Intent> addItemLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if(result.getResultCode() == RESULT_OK && result.getData() != null){
                    String titulo = result.getData().getStringExtra("titulo");
                    String descricao = result.getData().getStringExtra("descricao");
                    controller.Add(new Item(titulo,descricao));
                    enviarNotificacao(titulo,descricao);
                }
            });
    private final ActivityResultLauncher<Intent> editItemLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if(result.getResultCode() == RESULT_OK && result.getData() != null && editPosition != -1){
                    String titulo = result.getData().getStringExtra("titulo");
                    String descricao = result.getData().getStringExtra("descricao");
                    controller.update(editPosition, new Item(titulo, descricao));
                    editPosition = -1;
                }
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "canal_estudo",
                    "Lembretes de Estudo",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("Notificações para lembrar de estudar");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        controller = new Controller(this);
        recyclerView = findViewById(R.id.recyclerView);
        btnAdd = findViewById(R.id.button);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(controller.CreateAdapter());
        controller.getAdapter().setOnItemClickListener(position -> {
            editPosition = position;
            Item item = controller.getList().get(position);
            Intent intent = new Intent(MainActivity.this, EditarItemActivity.class);
            intent.putExtra("titulo", item.getTexto());
            intent.putExtra("descricao", item.getDescricao());
            editItemLauncher.launch(intent);
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();
                Collections.swap(controller.getList(),fromPosition,toPosition);
                Adapter adapter = controller.getAdapter();
                adapter.notifyItemMoved(fromPosition,toPosition);
                adapter.notifyItemChanged(fromPosition);
                adapter.notifyItemChanged(toPosition);
                return true;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Item item = controller.getList().get(position);
                controller.remove(item,position);
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddItemActivity.class);
                addItemLauncher.launch(intent);
            }
        });
    }
    private void enviarNotificacao(String titulo,String descricao){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"canal_estudo")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Estudo "+ titulo)
                .setContentText(descricao)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify((int) System.currentTimeMillis(),builder.build());
    }
}