package cl.ciisa.IC206IECIREOL.davidbousquetev01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cl.ciisa.IC206IECIREOL.davidbousquetev01.controllers.AuthController;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.controllers.EvaluationController;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.Evaluation;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.lib.InputValidator;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.ui.DatePickerFragment;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.ui.EvaluationAdapter;

public class EvaluationsActivity extends AppCompatActivity {
    private final String DATE_PATTERN = "yyyy-MM-dd";
    private TextInputLayout tilDateFrom, tilDateUntil;
    private TextView tvTitle, tvClearFilter;
    private ListView listViewAllEvaluations;
    private Button btnLogout, btnAdd, btnSearch;
    private AuthController authController;
    private EvaluationController evaluationController;

    private List<Evaluation> evaluationsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluations);

        authController = new AuthController(this);
        evaluationController = new EvaluationController(this);

        tilDateFrom = findViewById(R.id.activity_evaluations_field_date_from);
        tilDateUntil = findViewById(R.id.activity_evaluations_field_date_until);
        listViewAllEvaluations = findViewById(R.id.activity_evaluations_lv_all_evaluations);
        btnLogout = findViewById(R.id.activity_evaluations_btn_logout);
        btnAdd = findViewById(R.id.activity_evaluations_btn_add);
        btnSearch = findViewById(R.id.activity_evaluations_btn_search);
        tvTitle = findViewById(R.id.activity_evaluations_tv_title);
        tvClearFilter = findViewById(R.id.activity_evaluations_tv_clear_filter);

        evaluationsList = evaluationController.getAll();

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

        btnSearch.setOnClickListener(view -> {
            String fromStr = tilDateFrom.getEditText().getText().toString();
            String untilStr = tilDateUntil.getEditText().getText().toString();

            InputValidator inputValidator = new InputValidator();

            inputValidator.setTextInputLayout(tilDateFrom);
            inputValidator.setMessage("Ingrese una fecha");
            inputValidator.setStringToValidate(fromStr);
            Date fromDate = inputValidator.validateDate();

            inputValidator.setTextInputLayout(tilDateUntil);
            inputValidator.setStringToValidate(untilStr);
            boolean validDates = inputValidator.validateDateGreaterThan(fromDate);

            if (validDates) {
                SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_PATTERN);
                try {
                    Date from = dateFormatter.parse(fromStr);
                    Date to = dateFormatter.parse(untilStr);

                    List<Evaluation> evaluationRangeList = evaluationController.getRange(from, to);
                    EvaluationAdapter rangeAdapter = new EvaluationAdapter(this, evaluationRangeList);

                    listViewAllEvaluations.setAdapter(rangeAdapter);

                    listViewAllEvaluations.setOnItemClickListener(((adapterView, rangeView, index, id) -> {
                        Evaluation evaluation = evaluationRangeList.get(index);

                        Intent i = new Intent(rangeView.getContext(), DetailActivity.class);
                        i.putExtra("evaluation", evaluation);
                        rangeView.getContext().startActivity(i);
                    }));

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        tvClearFilter.setOnClickListener(view -> {
            tilDateFrom.getEditText().setText("");
            tilDateUntil.getEditText().setText("");
            listViewAllEvaluations.setAdapter(adapter);
        });



        btnLogout.setOnClickListener(view -> { authController.logout(); });

        btnAdd.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), AddEvaluationActivity.class);
            startActivity(i);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Evaluation> evaluationList = evaluationController.getAll();
        EvaluationAdapter adapter = new EvaluationAdapter(this, evaluationList);

        listViewAllEvaluations.setAdapter(adapter);

        listViewAllEvaluations.setOnItemClickListener(((adapterView, view, index, id) -> {
            Evaluation evaluation = evaluationList.get(index);

            Intent i = new Intent(view.getContext(), DetailActivity.class);
            i.putExtra("evaluation", evaluation);
            view.getContext().startActivity(i);
        }));
    }
}