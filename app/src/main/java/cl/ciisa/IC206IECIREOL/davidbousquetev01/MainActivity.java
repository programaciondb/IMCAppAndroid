package cl.ciisa.IC206IECIREOL.davidbousquetev01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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