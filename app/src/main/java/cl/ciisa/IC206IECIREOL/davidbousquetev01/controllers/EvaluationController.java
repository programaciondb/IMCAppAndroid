package cl.ciisa.IC206IECIREOL.davidbousquetev01.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cl.ciisa.IC206IECIREOL.davidbousquetev01.dao.EvaluationDao;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.lib.IMCAppDatabase;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.Evaluation;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.EvaluationEntity;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.EvaluationMapper;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.User;

public class EvaluationController {
    private Context context;
    private EvaluationDao evaluationDao;

    public EvaluationController(Context context) {
        this.context = context;
        this.evaluationDao = IMCAppDatabase.getInstance(context).evaluationDao();
    }

    public void register(Evaluation evaluation) {
        EvaluationMapper evaluationMapper = new EvaluationMapper(evaluation);
        EvaluationEntity newEvaluation = evaluationMapper.toEntity();
        evaluationDao.insert(newEvaluation);
        ((Activity) context).onBackPressed();
    }

    public void delete(long id) {
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    try {
                        evaluationDao.delete(id);
                        ((Activity) context).onBackPressed();
                    } catch(Error error) {
                        error.printStackTrace();
                        Toast.makeText(this.context, "Error al eliminar la evaluación", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setMessage("Estás seguro de eliminar la evaluación?")
                .setPositiveButton("Si", dialogClickListener)
                .setNegativeButton("No", dialogClickListener)
                .show();
    }

    public List<Evaluation> getAll() {
        AuthController authController = new AuthController(context);
        User user = authController.getUserSession();
        List<EvaluationEntity> evaluationEntityList = evaluationDao.findAll(user.getId());
        List<Evaluation> evaluationList = new ArrayList<>();

        for(EvaluationEntity evaluationEntity : evaluationEntityList) {
            EvaluationMapper mapper = new EvaluationMapper(evaluationEntity);
            Evaluation evaluation = mapper.toBase();
            evaluationList.add(evaluation);
        }

        return evaluationList;
    }

    public List<Evaluation> getRange(Date from, Date to) {
        AuthController authController = new AuthController(context);
        User user = authController.getUserSession();
        List<EvaluationEntity> evaluationEntityList = evaluationDao.findByRange(from, to, user.getId());
        List<Evaluation> evaluationList = new ArrayList<>();

        for(EvaluationEntity evaluationEntity : evaluationEntityList) {
            EvaluationMapper mapper = new EvaluationMapper(evaluationEntity);
            Evaluation evaluation = mapper.toBase();
            evaluationList.add(evaluation);
        }

        return evaluationList;
    }
}
