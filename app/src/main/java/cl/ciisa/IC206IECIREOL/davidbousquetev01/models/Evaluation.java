package cl.ciisa.IC206IECIREOL.davidbousquetev01.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Evaluation implements Serializable, IEvaluation {
    private long id;
    private Date date;
    private Double weight;
    private Double imc;
    private long userId;

    public Evaluation(Date date, Double weight, Double height, long userId) {
        this.date = date;
        this.weight = weight;
        this.imc = calculateImc(height);
        this.userId = userId;
    }

    private double calculateImc(double height){
        return weight / (height * height);
    }

    public String getStringDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getImc() {
        return imc;
    }

    public void setImc(Double imc) {
        this.imc = imc;
    }

    @Override
    public long getUserId() {
        return userId;
    }
}
