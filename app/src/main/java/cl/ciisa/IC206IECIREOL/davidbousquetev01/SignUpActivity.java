package cl.ciisa.IC206IECIREOL.davidbousquetev01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

import cl.ciisa.IC206IECIREOL.davidbousquetev01.controllers.AuthController;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.dao.UserDao;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.InputValidator;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.User;
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
            Date birthdayDate = null;
            boolean formValid = false;

            InputValidator inputValidator = new InputValidator();

            inputValidator.setStringToValidate(firstName);
            inputValidator.setTextInputLayout(tilFirstName);
            inputValidator.setMessage("Ingrese primer nombre");
            boolean firstNameValid = inputValidator.validateEmptyString();

            inputValidator.setStringToValidate(lastName);
            inputValidator.setTextInputLayout(tilLastName);
            inputValidator.setMessage("Ingrese apellido");
            boolean lastNameValid = inputValidator.validateEmptyString();

            inputValidator.setStringToValidate(username);
            inputValidator.setTextInputLayout(tilUsername);
            inputValidator.setMessage("El username debe ser un email válido");
            boolean usernameValid = inputValidator.validateUsernameSignUp(view.getContext());

            inputValidator.setStringToValidate(password);
            inputValidator.setTextInputLayout(tilPassword);
            inputValidator.setMessage("Ingresa una contraseña de al menos 6 caracteres");
            boolean passwordValid = inputValidator.validatePassword();

            inputValidator.setStringToValidate(height);
            inputValidator.setTextInputLayout(tilHeight);
            inputValidator.setMessage("Ingrese altura válida");
            heightDouble = inputValidator.validateDouble();
            boolean heightValid = heightDouble > 0;

            inputValidator.setStringToValidate(birthday);
            inputValidator.setTextInputLayout(tilBirthday);
            inputValidator.setMessage("Ingrese Fecha de Nacimiento");
            birthdayDate = inputValidator.validateDate();
            boolean birthdayValid = birthdayDate != null;

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