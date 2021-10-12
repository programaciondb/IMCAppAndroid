package cl.ciisa.IC206IECIREOL.davidbousquetev01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cl.ciisa.IC206IECIREOL.davidbousquetev01.controllers.AuthController;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.Evaluation;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.User;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.ui.DatePickerFragment;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.ui.EvaluationAdapter;

public class EvaluationsActivity extends AppCompatActivity {
    //    private final String DATE_PATTERN = "yyyy-MM-dd";
    private TextInputLayout tilDateFrom, tilDateUntil;
    private TextView tvTitle;
    private ListView listViewAllEvaluations;
    private Button btnLogout;
    private Button btnAdd;
    private AuthController authController;

    private List<Evaluation> evaluationsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluations);

        authController = new AuthController(this);

        tilDateFrom = findViewById(R.id.activity_evaluations_field_date_from);
        tilDateUntil = findViewById(R.id.activity_evaluations_field_date_until);
        listViewAllEvaluations = findViewById(R.id.activity_evaluations_lv_all_evaluations);
        btnLogout = findViewById(R.id.activity_evaluations_btn_logout);
        btnAdd = findViewById(R.id.activity_evaluations_btn_add);
        tvTitle = findViewById(R.id.activity_evaluations_tv_title);

        User user = authController.getUserSession();

        if(!user.getUsername().isEmpty()){
            tvTitle.setText(String.format("Evaluaciones de %s", user.getUsername()));
        }

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

        listViewAllEvaluations.setOnItemClickListener((adapterView, view, index, id) -> {
            Evaluation evaluation = evaluationsList.get(index);

            Intent intent = new Intent(view.getContext(), DetailActivity.class);
            intent.putExtra("evaluation", evaluation);
            view.getContext().startActivity(intent);
        });

        tilDateFrom.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilDateFrom, new Date());
        });
        tilDateUntil.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilDateUntil, new Date());
        });

        btnLogout.setOnClickListener(view -> { authController.logout(); });

        btnAdd.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), AddEvaluationActivity.class);
            startActivity(i);
        });

    }
}