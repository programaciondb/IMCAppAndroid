package cl.ciisa.IC206IECIREOL.davidbousquetev01.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "users", indices = {@Index(value = "username", unique = true)})
public class UserEntity implements IUser {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "username")
    private String username;
    @ColumnInfo(name = "firstName")
    private String firstName;
    @ColumnInfo(name = "lastName")
    private String lastName;
    @ColumnInfo(name = "birthday")
    private Date birthday;
    @ColumnInfo(name = "height")
    private String height;
    @ColumnInfo(name = "password")
    private String password;

    public UserEntity(long id, String username, String firstName, String lastName, Date birthday, String height, String password) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.height = height;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getHeight() {
        return height;
    }

    public String getPassword() {
        return password;
    }

}
