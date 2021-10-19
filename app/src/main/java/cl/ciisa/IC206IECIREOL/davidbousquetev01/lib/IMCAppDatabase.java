package cl.ciisa.IC206IECIREOL.davidbousquetev01.lib;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import cl.ciisa.IC206IECIREOL.davidbousquetev01.dao.EvaluationDao;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.dao.UserDao;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.EvaluationEntity;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.UserEntity;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.utils.Converters;

@Database(entities = {UserEntity.class, EvaluationEntity.class}, version = 2)
@TypeConverters({Converters.class})
public abstract class IMCAppDatabase extends RoomDatabase {
    private static final String DB_NAME = "todo_app_db";
    private static IMCAppDatabase instance;

    public static synchronized IMCAppDatabase getInstance(Context ctx) {
        if (instance == null) {
            instance = Room.databaseBuilder(ctx.getApplicationContext(), IMCAppDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract UserDao userDao();
    public abstract EvaluationDao evaluationDao();
}