package cl.ciisa.IC206IECIREOL.davidbousquetev01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSignUp = findViewById(R.id.activity_signup_btn_submit);

        btnSignUp.setOnClickListener(view -> {
            //Toast.makeText(view.getContext(), "Registrado correctamente", Toast.LENGTH_SHORT);
            Intent i = new Intent(view.getContext(), AddEvaluationActivity.class);
            startActivity(i);
        });
    }
}