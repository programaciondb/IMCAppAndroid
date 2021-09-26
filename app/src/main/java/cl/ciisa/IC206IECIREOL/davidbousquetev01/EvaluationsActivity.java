package cl.ciisa.IC206IECIREOL.davidbousquetev01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.Evaluation;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.ui.DatePickerFragment;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.ui.EvaluationAdapter;

public class EvaluationsActivity extends AppCompatActivity {
    //    private final String DATE_PATTERN = "yyyy-MM-dd";
    private TextInputLayout tilDateFrom, tilDateUntil;
    private ListView listViewAllEvaluations;
    private Button btnLogout;

    private List<Evaluation> evaluationsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluations);

        tilDateFrom = findViewById(R.id.activity_evaluations_field_date_from);
        tilDateUntil = findViewById(R.id.activity_evaluations_field_date_until);
        listViewAllEvaluations = findViewById(R.id.activity_evaluations_lv_all_evaluations);
        btnLogout = findViewById(R.id.activity_evaluations_btn_logout);

        for (int x = 1; x <= 10; ++x) {
            Date evaluationDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(evaluationDate);
            calendar.add(Calendar.DATE, -x);
            evaluationDate = calendar.getTime();
            Double weight = (80 + (x * 0.3));
            Double imc = weight / (1.82 * 1.82);
            Evaluation newEvaluation = new Evaluation(evaluationDate, weight, imc);
            newEvaluation.setId(x);
            evaluationsList.add(newEvaluation);
        }

        EvaluationAdapter adapter = new EvaluationAdapter(this, evaluationsList);

        listViewAllEvaluations.setAdapter(adapter);

        tilDateFrom.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilDateFrom, new Date());
        });
        tilDateUntil.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilDateUntil, new Date());
        });

        btnLogout.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), "Cerrando Sesión", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(view.getContext(), LoginActivity.class);
            startActivity(i);
            finish();
        });

    }
}