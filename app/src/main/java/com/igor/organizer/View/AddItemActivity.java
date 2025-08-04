package com.igor.organizer.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.igor.organizer.R;

public class AddItemActivity extends AppCompatActivity {
    EditText editTextTitle, editTextDescription;
    Button btnConfirmar;
    String modo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.add_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editTextTitle = findViewById(R.id.editTextText);
        editTextDescription = findViewById(R.id.editTextText2);
        btnConfirmar = findViewById(R.id.button2);
        String tituloOriginal = getIntent().getStringExtra("titulo");
        String descricaoOriginal = getIntent().getStringExtra("descricao");
        modo = getIntent().getStringExtra("modo");

        if("editar".equals(modo)){
            editTextTitle.setText(tituloOriginal);
            editTextDescription.setText(descricaoOriginal);
        }else{
            modo = "novo";
        }

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = editTextTitle.getText().toString();
                String descricao = editTextDescription.getText().toString();
                boolean isValid = true;
                if(titulo.isEmpty()){
                    editTextTitle.setError("Digite um título");
                    isValid = false;
                }
                if(descricao.isEmpty()){
                    editTextDescription.setError("Digite uma descrição");
                    isValid = false;
                }
                if(!isValid){
                    Toast.makeText(AddItemActivity.this,"Preencha todos os campos",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent resultIntent = new Intent();
                resultIntent.putExtra("titulo",titulo);
                resultIntent.putExtra("descricao",descricao);
                resultIntent.putExtra("modo",modo);
                setResult(Activity.RESULT_OK,resultIntent);
                finish();
            }
        });
    }
}
