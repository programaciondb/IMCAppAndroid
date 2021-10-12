package cl.ciisa.IC206IECIREOL.davidbousquetev01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

import cl.ciisa.IC206IECIREOL.davidbousquetev01.ui.DatePickerFragment;

public class AddEvaluationActivity extends AppCompatActivity {
    private TextInputLayout tilDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_evaluation);

        tilDate = findViewById(R.id.activity_add_evaluation_field_date);

        tilDate.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilDate, new Date());
        });
    }
}