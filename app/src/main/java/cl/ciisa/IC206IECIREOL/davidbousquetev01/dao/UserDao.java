package cl.ciisa.IC206IECIREOL.davidbousquetev01.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.UserEntity;


@Dao
public interface UserDao {
    @Query("SELECT id, username, firstName, lastName, birthday, height, password FROM users WHERE username = :username LIMIT 1")
    UserEntity findByUsername (String username);

    @Insert
    long insert(UserEntity user);
}
