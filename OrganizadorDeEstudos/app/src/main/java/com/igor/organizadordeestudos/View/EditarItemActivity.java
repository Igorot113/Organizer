package com.igor.organizadordeestudos.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.igor.organizadordeestudos.R;

public class EditarItemActivity extends AppCompatActivity {

    EditText editTitulo,editDescricao;
    Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_item);

        editTitulo = findViewById(R.id.editTitulo);
        editDescricao = findViewById(R.id.editDescricao);
        btnSalvar = findViewById(R.id.btnSalvar);

        String titulo = getIntent().getStringExtra("titulo");
        String descricao = getIntent().getStringExtra("descricao");

        editTitulo.setText(titulo);
        editDescricao.setText(descricao);

        btnSalvar.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("titulo", editTitulo.getText().toString());
            resultIntent.putExtra("descricao", editDescricao.getText().toString());
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });
    }
}
