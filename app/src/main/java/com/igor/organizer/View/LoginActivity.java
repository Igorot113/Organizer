package com.igor.organizer.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.igor.organizer.DataUser.UserDAO;
import com.igor.organizer.Modelo.User;
import com.igor.organizer.R;

public class LoginActivity extends AppCompatActivity {

    private EditText textUsername;
    private EditText textPassword;
    private Button btnLogin;
    private Button btnCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telalogin);

        textUsername = findViewById(R.id.LoginEmail);
        textPassword = findViewById(R.id.LoginSenha);
        btnLogin = findViewById(R.id.btnLogar);
        btnCadastro = findViewById(R.id.btnCadastrar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logar();
            }
        });

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });
    }
    private void logar(){
        String username = textUsername.getText().toString();
        String password = textPassword.getText().toString();
        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Por favor, preencha todos os campos. ",Toast.LENGTH_SHORT).show();
            return;
        }
        UserDAO userDAO = new UserDAO(this);
        //boolean loginSuccessful = userDAO.verif(username,password);
        User loggedUser = userDAO.getUserByCredentials(username,password);
        if(loggedUser != null){
            Toast.makeText(this,"Login bem-sucedido!",Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPref = getSharedPreferences("user_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putLong("user_id", loggedUser.getId());
            editor.apply();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this,"usuario ou senha incorretos.",Toast.LENGTH_SHORT).show();
        }
    }
}
