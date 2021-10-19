package cl.ciisa.IC206IECIREOL.davidbousquetev01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import cl.ciisa.IC206IECIREOL.davidbousquetev01.controllers.EvaluationController;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.Evaluation;

public class DetailActivity extends AppCompatActivity {
    private TextView textViewId, textViewDate, textViewImc, textViewWeight;
    private Button btnDelete, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Evaluation evaluation = (Evaluation) getIntent().getSerializableExtra("evaluation");

        textViewId = findViewById(R.id.activity_detail_tv_id);
        textViewDate = findViewById(R.id.activity_detail_tv_date);
        textViewImc = findViewById(R.id.activity_detail_tv_imc);
        textViewWeight = findViewById(R.id.activity_detail_tv_weight);

        btnBack = findViewById(R.id.activity_detail_btn_back);
        btnDelete = findViewById(R.id.activity_detail_btn_delete);

        textViewId.setText("ID " + evaluation.getId());
        textViewDate.setText("Fecha: " + evaluation.getStringDate());
        textViewImc.setText("IMC: "+ evaluation.getImc());
        textViewWeight.setText("Peso: "+ evaluation.getWeight());

        btnDelete.setOnClickListener(view -> {
            EvaluationController evaluationController = new EvaluationController(view.getContext());
            evaluationController.delete(evaluation.getId());
        });

        btnBack.setOnClickListener(view -> {
            super.onBackPressed();
        });


    }
}