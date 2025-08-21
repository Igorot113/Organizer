package com.igor.organizer.View;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.igor.organizer.DataUser.UserDAO;
import com.igor.organizer.Modelo.User;
import com.igor.organizer.R;

public class CadastroActivity extends AppCompatActivity {

    private EditText textUsername;
    private EditText textPassword;
    private Button btnCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        textUsername = findViewById(R.id.LoginEmail);
        textPassword = findViewById(R.id.LoginSenha);
        btnCadastro = findViewById(R.id.btnCadastrar);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar();
            }
        });
    }
    private void cadastrar(){
        String username = textUsername.getText().toString();
        String password = textPassword.getText().toString();

        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        User newUser = new User(username,password);

        UserDAO userDAO = new UserDAO(this);
        long id = userDAO.inserir(newUser);
        if(id>0){
            Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();
            finish();
        }else{
            Toast.makeText(this, "Erro ao cadastrar usuário.", Toast.LENGTH_LONG).show();
        }
    }
}
