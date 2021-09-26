package cl.ciisa.IC206IECIREOL.davidbousquetev01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cl.ciisa.IC206IECIREOL.davidbousquetev01.controllers.AuthController;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.User;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.ui.DatePickerFragment;

public class SignUpActivity extends AppCompatActivity {
    private final String DATE_PATTERN = "yyyy-MM-dd";
    private TextInputLayout tilUsername, tilBirthday, tilFirstName, tilLastName, tilHeight, tilPassword;
    private Button btnSignUp;

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
            String firstName = tilFirstName.getEditText().getText().toString();
            String lastName = tilLastName.getEditText().getText().toString();
            String username = tilUsername.getEditText().getText().toString();
            String password = tilPassword.getEditText().getText().toString();
            String birthday = tilBirthday.getEditText().getText().toString();
            String height = tilHeight.getEditText().getText().toString();

            SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_PATTERN);

            Date birthdayDate = null;
            try {
                birthdayDate = dateFormatter.parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            User user = new User(username, firstName, lastName, birthdayDate, height);
            user.setPassword(password);

            AuthController controller = new AuthController(view.getContext());

            controller.register(user);

        });
    }
}