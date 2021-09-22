package cl.ciisa.IC206IECIREOL.davidbousquetev01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.activity_login_btn_submit);

        btnLogin.setOnClickListener(view -> {
            // Toast.makeText(view.getContext(), "Cargando Evaluaciones", Toast.LENGTH_SHORT);
            Intent i = new Intent(view.getContext(), EvaluationsActivity.class);
            startActivity(i);
        });
    }
}