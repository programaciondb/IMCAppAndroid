package cl.ciisa.IC206IECIREOL.davidbousquetev01.models;

import java.util.Date;

interface IUser {
    long getId();

    String getUsername();

    String getFirstName();

    String getLastName();

    Date getBirthday();

    String getHeight();

    String getPassword();
}
