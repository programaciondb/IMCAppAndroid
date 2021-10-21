package cl.ciisa.IC206IECIREOL.davidbousquetev01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

import cl.ciisa.IC206IECIREOL.davidbousquetev01.controllers.AuthController;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.controllers.EvaluationController;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.Evaluation;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.lib.InputValidator;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.User;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.ui.DatePickerFragment;

public class AddEvaluationActivity extends AppCompatActivity {
    private TextInputLayout tilDate, tilWeight;
    private Button btnAdd, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_evaluation);

        tilDate = findViewById(R.id.activity_add_evaluation_field_date);
        tilWeight = findViewById(R.id.activity_add_evaluation_field_weight);
        btnAdd = findViewById(R.id.activity_add_evaluation_btn_submit);
        btnBack = findViewById(R.id.activity_add_evaluation_btn_cancel);

        tilDate.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilDate, new Date());
        });

        btnAdd.setOnClickListener(view -> {
            String weight = tilWeight.getEditText().getText().toString();
            String date = tilDate.getEditText().getText().toString();
            boolean validWeight, validDate;
            double weightDouble = 0;
            Date parsedDate = null;

            InputValidator inputValidator = new InputValidator();

            inputValidator.setStringToValidate(weight);
            inputValidator.setMessage("Ingrese un peso válido");
            inputValidator.setTextInputLayout(tilWeight);
            weightDouble = inputValidator.validateDouble();
            validWeight = weightDouble > 0;

            inputValidator.setStringToValidate(date);
            inputValidator.setMessage("Ingrese una fecha válida");
            inputValidator.setTextInputLayout(tilDate);
            parsedDate = inputValidator.validateDate();
            validDate = parsedDate != null;

            if (validWeight && validDate) {
                AuthController authController = new AuthController(view.getContext());
                User user = authController.getUserSession();
                Evaluation evaluation = new Evaluation(parsedDate, weightDouble, Double.parseDouble(user.getHeight()), user.getId());

                EvaluationController evaluationController = new EvaluationController(view.getContext());
                evaluationController.register(evaluation);
                Toast.makeText(view.getContext(), "Registrar", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(view.getContext(), "Corregir datos", Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(view -> {
            super.onBackPressed();
        });

    }
}