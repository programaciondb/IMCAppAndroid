package cl.ciisa.IC206IECIREOL.davidbousquetev01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.Evaluation;

public class DetailActivity extends AppCompatActivity {
    private TextView textViewId;
    private TextView textViewDate;
    private TextView textViewImc;
    private TextView textViewWeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Evaluation evaluation = (Evaluation) getIntent().getSerializableExtra("evaluation");

        textViewId = findViewById(R.id.activity_detail_tv_id);
        textViewDate = findViewById(R.id.activity_detail_tv_date);
        textViewImc = findViewById(R.id.activity_detail_tv_imc);
        textViewWeight = findViewById(R.id.activity_detail_tv_weight);

        textViewId.setText("ID " + Long.toString(evaluation.getId()));
        textViewDate.setText("Fecha: " + evaluation.getDate().toString());
        textViewImc.setText("IMC: "+ evaluation.getImc().toString());
        textViewWeight.setText("Peso: "+ evaluation.getWeight().toString());


    }
}