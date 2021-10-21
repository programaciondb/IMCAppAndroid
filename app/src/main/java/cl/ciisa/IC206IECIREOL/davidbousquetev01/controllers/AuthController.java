package cl.ciisa.IC206IECIREOL.davidbousquetev01.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.widget.Toast;

import java.util.Date;

import cl.ciisa.IC206IECIREOL.davidbousquetev01.EvaluationsActivity;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.LoginActivity;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.dao.UserDao;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.lib.BCrypt;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.lib.IMCAppDatabase;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.User;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.UserEntity;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.UserMapper;

public class AuthController {
    private final String KEY_USER_ID = "userId";
    private final String KEY_USERNAME = "userName";
    private final String KEY_FIRST_NAME = "userFirstName";
    private final String KEY_LAST_NAME = "userLastName";
    private final String KEY_HEIGHT = "height";


    private UserDao userDao;
    private Context context;
    private SharedPreferences preferences;

    public AuthController(Context context) {
        this.context = context;
        int PRIVATE_MODE = 0;
        String PREF_NAME = "IMCAppPref";
        this.preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        this.userDao = IMCAppDatabase.getInstance(context).userDao();
    }

    private void setUserSession(User user) {
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putLong(KEY_USER_ID, user.getId());
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.putString(KEY_FIRST_NAME, user.getFirstName());
        editor.putString(KEY_LAST_NAME, user.getLastName());
        editor.putString(KEY_HEIGHT, user.getHeight());
        editor.apply();
    }

    public User getUserSession() {
        long id = preferences.getLong(KEY_USER_ID, 0);
        String firstName = preferences.getString(KEY_FIRST_NAME, "");
        String lastName = preferences.getString(KEY_LAST_NAME, "");
        String userName = preferences.getString(KEY_USERNAME, "");
        String height = preferences.getString(KEY_HEIGHT, "");

        User user = new User(userName, firstName, lastName, new Date(), height);
        user.setId(id);
        return user;
    }

    public void checkUserSession() {
        long id = preferences.getLong(KEY_USER_ID, 0);

        final int TIMEOUT = 3000;

        new Handler().postDelayed(() -> {
            if (id != 0) {
                Toast.makeText(context, "Bienvenido de nuevo", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, EvaluationsActivity.class);
                context.startActivity(intent);
            } else {
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            }
            ((Activity) context).finish();
        }, TIMEOUT);
    }

    public void register(User user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);

        UserEntity userEntity = new UserMapper(user).toEntity();


        userDao.insert(userEntity);

        Toast.makeText(context, String.format("Usuario %s registrado", user.getUsername()), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public void login(String username, String password) {
        UserEntity userEntity = userDao.findByUsername(username);
        User user = new UserMapper(userEntity).toBase();

        if (BCrypt.checkpw(password, user.getPassword())) {
            setUserSession(user);
            Toast.makeText(context, String.format("Bienvenido %s", username), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, EvaluationsActivity.class);
            context.startActivity(intent);
            ((Activity) context).finish();
        } else {
            Toast.makeText(context, String.format("La contraseña es incorrecta para %s", username), Toast.LENGTH_SHORT).show();
        }
    }

    public void logout() {
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.clear();
        editor.apply();
        Toast.makeText(context, "Cerrando Sesión", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
        ((Activity) context).finish();
    }
}
