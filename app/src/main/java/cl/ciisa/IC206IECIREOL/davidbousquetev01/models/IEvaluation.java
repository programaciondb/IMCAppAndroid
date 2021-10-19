package cl.ciisa.IC206IECIREOL.davidbousquetev01.models;

import java.util.Date;

public interface IEvaluation {
    long getId();
    Date getDate();
    Double getWeight();
    Double getImc();
    long getUserId();
}
