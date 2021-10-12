package cl.ciisa.IC206IECIREOL.davidbousquetev01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cl.ciisa.IC206IECIREOL.davidbousquetev01.controllers.AuthController;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.dao.UserDao;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.lib.IMCAppDatabase;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.User;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.UserEntity;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.ui.DatePickerFragment;

public class SignUpActivity extends AppCompatActivity {
    private final String DATE_PATTERN = "yyyy-MM-dd";
    private TextInputLayout tilUsername, tilBirthday, tilFirstName, tilLastName, tilHeight, tilPassword;
    private Button btnSignUp;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        tilFirstName = findViewById(R.id.activity_signup_field_firstname);
        tilLastName = findViewById(R.id.activity_signup_field_lastname);
        tilUsername = findViewById(R.id.activity_signup_field_username);
        tilPassword = findViewById(R.id.activity_signup_field_password);
        tilBirthday = findViewById(R.id.activity_signup_field_birthdate);
        tilHeight = findViewById(R.id.activity_signup_field_height);

        tilBirthday.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilBirthday, new Date());
        });

        btnSignUp = findViewById(R.id.activity_signup_btn_submit);

        btnSignUp.setOnClickListener(view -> {
            String firstName = tilFirstName.getEditText().getText().toString().trim();
            String lastName = tilLastName.getEditText().getText().toString().trim();
            String username = tilUsername.getEditText().getText().toString().trim();
            String password = tilPassword.getEditText().getText().toString().trim();
            String birthday = tilBirthday.getEditText().getText().toString().trim();
            String height = tilHeight.getEditText().getText().toString().trim();

            double heightDouble;

            boolean formValid = false;

            boolean firstNameValid = !firstName.isEmpty();
            boolean lastNameValid = !lastName.isEmpty();
            boolean usernameValid = !username.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(username).matches();
            boolean passwordValid = !password.isEmpty() && password.length() > 5;
            boolean heightValid = !height.isEmpty();
            boolean birthdayValid = !birthday.isEmpty();

            String usernameError = usernameValid ? "" : "El username debe ser un email válido";

            if (usernameValid){
                userDao = IMCAppDatabase.getInstance(view.getContext()).userDao();
                UserEntity userEntity = userDao.findByUsername(username);
                if (userEntity != null && userEntity.getId() > 0) {
                    usernameValid = false;
                    usernameError = "Nombre de usuario ya existe";
                }
            }


            if (!firstNameValid) {
                tilFirstName.setError("Ingrese primer nombre");
            } else {
                tilFirstName.setError(null);
                tilFirstName.setErrorEnabled(false);
            }
            if (!lastNameValid) {
                tilLastName.setError("Ingrese apellido");
            } else {
                tilLastName.setError(null);
                tilLastName.setErrorEnabled(false);
            }
            if (!usernameValid) {
                tilUsername.setError(usernameError);
            } else {
                tilUsername.setError(null);
                tilUsername.setErrorEnabled(false);
            }

            if (!passwordValid){
                tilPassword.setError("Ingresa una contraseña de al menos 6 caracteres");
            } else {
                tilPassword.setError(null);
                tilPassword.setErrorEnabled(false);
            }

            if (!heightValid){
                tilHeight.setError("Ingrese altura");
            } else {
                tilHeight.setError(null);
                tilHeight.setErrorEnabled(false);
            }

            if (!birthdayValid){
                tilBirthday.setError("Ingrese Fecha de Nacimiento");
            } else {
                tilBirthday.setError(null);
                tilBirthday.setErrorEnabled(false);
            }


            SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_PATTERN);

            Date birthdayDate = null;
            try {
                birthdayDate = dateFormatter.parse(birthday);
                birthdayValid = true;
            } catch (ParseException e) {
                e.printStackTrace();
                birthdayValid = false;
            }

            if (!birthdayValid){
                tilBirthday.setError("Ingrese una Fecha de Nacimiento válida");
            } else {
                tilBirthday.setError(null);
                tilBirthday.setErrorEnabled(false);
            }

            try {
                heightDouble = Double.parseDouble(height);
                heightValid = heightDouble > 0;
            } catch (NumberFormatException e){
                heightValid = false;
                e.printStackTrace();
            }

            if (!heightValid){
                tilHeight.setError("Ingrese altura válida");
            } else {
                tilHeight.setError(null);
                tilHeight.setErrorEnabled(false);
            }

            formValid = usernameValid && firstNameValid && lastNameValid && birthdayValid && heightValid && passwordValid;


            if (formValid){
                User user = new User(username, firstName, lastName, birthdayDate, height);
                user.setPassword(password);

                AuthController controller = new AuthController(view.getContext());

                controller.register(user);
            }



        });
    }
}