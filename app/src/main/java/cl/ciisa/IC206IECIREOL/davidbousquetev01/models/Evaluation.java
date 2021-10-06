package cl.ciisa.IC206IECIREOL.davidbousquetev01.models;

import java.io.Serializable;
import java.util.Date;

public class Evaluation implements Serializable {
    private long id;
    private Date date;
    private Double weight;
    private Double imc;

    public Evaluation(Date date, Double weight, Double imc) {
        this.date = date;
        this.weight = weight;
        this.imc = imc;
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
}
