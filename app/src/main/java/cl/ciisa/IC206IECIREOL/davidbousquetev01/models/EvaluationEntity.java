package cl.ciisa.IC206IECIREOL.davidbousquetev01.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "evaluations")
public class EvaluationEntity implements IEvaluation {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "date")
    private Date date;

    @ColumnInfo(name = "weight")
    private Double weight;

    @ColumnInfo(name = "imc")
    private Double imc;

    @ColumnInfo(name = "user_id")
    private long userId;

    public EvaluationEntity(long id, Date date, Double weight, Double imc, long userId) {
        this.id = id;
        this.date = date;
        this.weight = weight;
        this.imc = imc;
        this.userId = userId;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public Double getWeight() {
        return weight;
    }

    @Override
    public Double getImc() {
        return imc;
    }

    @Override
    public long getUserId() {
        return userId;
    }
}
