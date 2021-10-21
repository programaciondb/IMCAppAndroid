package cl.ciisa.IC206IECIREOL.davidbousquetev01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import cl.ciisa.IC206IECIREOL.davidbousquetev01.controllers.AuthController;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.lib.InputValidator;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin, btnSignUp;
    private TextInputLayout tilUsername, tilPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.activity_login_btn_submit);
        btnSignUp = findViewById(R.id.activity_login_btn_signup);
        tilUsername = findViewById(R.id.activity_login_field_username);
        tilPassword = findViewById(R.id.activity_login_field_password);


        btnLogin.setOnClickListener(view -> {
            String username = tilUsername.getEditText().getText().toString();
            String password = tilPassword.getEditText().getText().toString();

            InputValidator inputValidator = new InputValidator();

            inputValidator.setStringToValidate(username);
            inputValidator.setTextInputLayout(tilUsername);
            inputValidator.setMessage("El username debe ser un email válido");
            boolean usernameValid = inputValidator.validateUsernameLogin(view.getContext());

            inputValidator.setStringToValidate(password);
            inputValidator.setTextInputLayout(tilPassword);
            inputValidator.setMessage("Ingrese password correcto");
            boolean passwordValid = inputValidator.validatePassword();


            if (usernameValid && passwordValid) {
                Toast.makeText(view.getContext(), "Iniciando sesión", Toast.LENGTH_SHORT).show();
                AuthController controller = new AuthController(view.getContext());
                controller.login(username, password);
            } else {
                Toast.makeText(view.getContext(), "Campos inválidos", Toast.LENGTH_SHORT).show();
            }

        });

        btnSignUp.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), SignUpActivity.class);
            startActivity(i);
        });
    }
}