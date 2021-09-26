package cl.ciisa.IC206IECIREOL.davidbousquetev01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import cl.ciisa.IC206IECIREOL.davidbousquetev01.controllers.AuthController;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextInputLayout tilUsername, tilPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.activity_login_btn_submit);
        tilUsername = findViewById(R.id.activity_login_field_username);
        tilPassword = findViewById(R.id.activity_login_field_password);


        btnLogin.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), "Iniciando sesión", Toast.LENGTH_SHORT).show();

            String username = tilUsername.getEditText().getText().toString();
            String password = tilPassword.getEditText().getText().toString();

            boolean usernameValid = !username.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(username).matches();
            boolean passwordValid = !password.isEmpty();

            if (!usernameValid) {
                tilUsername.setError("El username es inválido");
            } else {
                tilUsername.setError(null);
                tilUsername.setErrorEnabled(false);
            }

            if (!passwordValid) {
                tilPassword.setError("Campo requerido");
            } else {
                tilPassword.setError(null);
                tilPassword.setErrorEnabled(false);
            }

            if (usernameValid && passwordValid) {
                AuthController controller = new AuthController(view.getContext());
                controller.login(username, password);
            } else {
                Toast.makeText(view.getContext(), "Campos inválidos", Toast.LENGTH_SHORT).show();
            }

        });
    }
}