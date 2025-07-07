package com.igor.organizadordeestudos.View;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.igor.organizadordeestudos.R;

public class DetalhesActivity extends AppCompatActivity {
    TextView txtTitulo,txtDescricao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Detalhes");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        txtTitulo = findViewById(R.id.txtTitulo);
        txtDescricao = findViewById(R.id.txtDescricao);

        String titulo = getIntent().getStringExtra("titulo");
        String descricao = getIntent().getStringExtra("descricao");

        txtTitulo.setText(titulo);
        txtDescricao.setText(descricao);
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
