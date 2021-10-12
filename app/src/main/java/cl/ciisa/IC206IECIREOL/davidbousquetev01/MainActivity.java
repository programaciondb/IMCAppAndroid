package cl.ciisa.IC206IECIREOL.davidbousquetev01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cl.ciisa.IC206IECIREOL.davidbousquetev01.controllers.AuthController;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.User;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnSignUp;
    private AuthController authController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        authController = new AuthController(this);

        authController.checkUserSession();

        btnLogin = findViewById(R.id.activity_main_btn_login);
        btnSignUp = findViewById(R.id.activity_main_btn_signup);

        btnLogin.setOnClickListener(view -> {
//            Toast.makeText(view.getContext(), "Cargando LogIn", Toast.LENGTH_SHORT);
            Intent i = new Intent(view.getContext(), LoginActivity.class);
            startActivity(i);
        });
        btnSignUp.setOnClickListener(view -> {
//            Toast.makeText(view.getContext(), "Cargando Registro", Toast.LENGTH_SHORT);
            Intent i = new Intent(view.getContext(), SignUpActivity.class);
            startActivity(i);
        });


    }
}