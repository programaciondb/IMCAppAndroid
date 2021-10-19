package cl.ciisa.IC206IECIREOL.davidbousquetev01.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.EvaluationEntity;

@Dao
public interface EvaluationDao {
    @Query("SELECT id, date, weight, imc, user_id FROM evaluations WHERE user_id = :userId")
    List<EvaluationEntity> findAll(long userId);

    @Query("SELECT id, date, weight, imc, user_id FROM evaluations WHERE user_id = :userId AND date BETWEEN :from AND :to")
    List<EvaluationEntity> findByRange (Date from, Date to, long userId);

    @Insert
    long insert(EvaluationEntity evaluation);

    @Query("DELETE FROM evaluations WHERE id = :id")
    void delete(long id);

}
