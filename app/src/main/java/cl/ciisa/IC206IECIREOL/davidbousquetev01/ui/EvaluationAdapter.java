package cl.ciisa.IC206IECIREOL.davidbousquetev01.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cl.ciisa.IC206IECIREOL.davidbousquetev01.R;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.Evaluation;

public class EvaluationAdapter extends BaseAdapter {
    private Context context;
    private List<Evaluation> evaluationList;

    public EvaluationAdapter(Context context, List<Evaluation> evaluationList) {
        this.context = context;
        this.evaluationList = evaluationList;
    }

    @Override
    public int getCount(){
        return evaluationList.size();
    }
    @Override
    public Object getItem(int i) {
        return evaluationList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return evaluationList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);

        view = inflater.inflate(R.layout.item_evaluation, null);

        Evaluation evaluation = evaluationList.get(i);

        TextView tvId = view.findViewById(R.id.item_evaluation_tv_id);
        TextView tvDate = view.findViewById(R.id.item_evaluation_tv_date);
        TextView tvWeight = view.findViewById(R.id.item_evaluation_tv_weight);
        TextView tvImc = view.findViewById(R.id.item_evaluation_tv_imc);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(evaluation.getDate());

        tvId.setText("ID: " + Long.toString(evaluation.getId()));
        tvDate.setText("Fecha: " + dateStr);
        tvWeight.setText("Peso:" + Double.toString((evaluation.getWeight())));
        tvImc.setText("IMC: " + Double.toString(evaluation.getImc()));

        return view;
    }

}
