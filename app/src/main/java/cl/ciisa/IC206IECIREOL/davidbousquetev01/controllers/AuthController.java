package cl.ciisa.IC206IECIREOL.davidbousquetev01.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import cl.ciisa.IC206IECIREOL.davidbousquetev01.EvaluationsActivity;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.LoginActivity;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.models.User;

public class AuthController {
    private Context context;

    public AuthController(Context context){
        this.context = context;
    }

    public void register(User user){
        Toast.makeText(context, String.format("Usuario %s registrado.", user.getUsername()), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(context, LoginActivity.class);
        context.startActivity(i);
    }

    public void login (String username, String password) {
        if (password.equals("123456")) {
            Toast.makeText(context, String.format("Bienvenido %s", username), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, EvaluationsActivity.class);
            context.startActivity(i);
            ((Activity) context).finish();
        } else {
            Toast.makeText(context, String.format("La contraseña es incorrecta para el usuario %s", username), Toast.LENGTH_SHORT).show();
        }
    }
}
