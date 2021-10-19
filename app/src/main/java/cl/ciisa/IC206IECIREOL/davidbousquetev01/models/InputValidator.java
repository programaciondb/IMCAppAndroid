package cl.ciisa.IC206IECIREOL.davidbousquetev01.models;

import android.content.Context;
import android.util.Patterns;

import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cl.ciisa.IC206IECIREOL.davidbousquetev01.dao.UserDao;
import cl.ciisa.IC206IECIREOL.davidbousquetev01.lib.IMCAppDatabase;

public class InputValidator {
    private final String DATE_PATTERN = "yyyy-MM-dd";
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_PATTERN);
    TextInputLayout textInputLayout;
    String stringToValidate;
    String message;

    public void setTextInputLayout(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
    }

    public void setStringToValidate(String stringToValidate) {
        this.stringToValidate = stringToValidate;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean validateEmptyString() {
        if (!stringToValidate.isEmpty()) {
            disableError();
            return true;
        } else {
            textInputLayout.setError(message);
            return false;
        }

    }

    public boolean validateUsernameSignUp(Context context) {
        return validateEmptyString() && validateEmail() && checkIfUsernameIsAvailable(context);
    }

    public boolean validateUsernameLogin(Context context) {
        if (validateEmptyString() && validateEmail() && checkIfUsernameExists(context)) {
            disableError();
            return true;
        } else {
            message = "Usuario inválido";
            textInputLayout.setError(message);
            return false;
        }
    }

    private boolean validateEmail() {
        if (validateEmptyString() && Patterns.EMAIL_ADDRESS.matcher(stringToValidate).matches()) {
            disableError();
            return true;
        }
        textInputLayout.setError(message);
        return false;
    }

    public boolean validatePassword() {
        if (validateEmptyString() && stringToValidate.length() > 5) {
            disableError();
            return true;
        }
        textInputLayout.setError(message);
        return false;
    }

    private boolean checkIfUsernameExists(Context context) {
        UserDao userDao = IMCAppDatabase.getInstance(context).userDao();
        UserEntity userEntity = userDao.findByUsername(stringToValidate);
        if (userEntity != null && userEntity.getId() > 0) {
            return true;
        }
        return false;
    }

    private boolean checkIfUsernameIsAvailable(Context context) {
        if (checkIfUsernameExists(context)) {
            message = "Nombre de usuario ya existe";
            textInputLayout.setError(message);
            return false;
        } else {
            disableError();
            return true;
        }
    }

    public boolean validateDateGreaterThan(Date earlierDate) {
        if (validateEmptyString()) {
            Date dateToValidate = null;
            try {
                dateToValidate = dateFormatter.parse(stringToValidate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (dateToValidate != null && earlierDate != null && dateToValidate.after(earlierDate)) {
                disableError();
                return true;
            }
        }

        message = "Rango de fechas inválido";
        textInputLayout.setError(message);
        return false;
    }

    public double validateDouble() {
        double parsedDouble = 0;

        if (validateEmptyString()) {
            try {
                parsedDouble = Double.parseDouble(stringToValidate);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            if (parsedDouble > 0) {
                disableError();
            } else {
                textInputLayout.setError(message);
            }
        }

        return parsedDouble;
    }

    public Date validateDate() {
        Date parsedDate = null;

        if (validateEmptyString()) {
            try {
                parsedDate = dateFormatter.parse(stringToValidate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (parsedDate != null) {
                disableError();
            } else {
                textInputLayout.setError(message);
            }
        }

        return parsedDate;
    }

    private void disableError() {
        textInputLayout.setError(null);
        textInputLayout.setErrorEnabled(false);
    }
}
