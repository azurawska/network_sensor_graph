package com.example.alexis.graphe_donnees_reseau_capteurs;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by alexis on 23/12/2016.
 */
public class CapteurDAO extends DAOBase {

    public final static String ID="ID";
    public final static String x="x";
    public final static String y="y";
    public final static String z="z";
    public final static String RIME_ADRESS="RIME_ADRESS";
    public final static String IP_ADRESS="IP_ADRESS";
    public final static String TIME_="TIME_";
    public final static String DEVIATION_FACTOR="DEVIATION_FACTOR";
    public final static String CPU="CPU";

    public final static String TABLE_NAME="capteur";
    public final static String CREATE_TABLE_CAPTEUR = "CREATE TABLE " + TABLE_NAME + " ("
            + ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            + x + "REAL NOT NULL,"
            + y + "REAL NOT NULL,"
            + z + "REAL NOT NULL,"
            + RIME_ADRESS + "TEXT NOT NULL,"
            + IP_ADRESS + "TEXT NOT NULL,"
            + TIME_ + "INTEGER NOT NULL,"
            + DEVIATION_FACTOR + "REAL NOT NULL,"
            + CPU + "REAL NOT NULL" +
            ");";

    public final static String DROP_TABLE_CAPTEUR = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public CapteurDAO(Context context) {
        super(context);
    }

    public void ajouter(Capteur capteur) {
        ContentValues values = new ContentValues();
        values.put(CapteurDAO.x, capteur.getX());
        values.put(CapteurDAO.y, capteur.getY());
        values.put(CapteurDAO.z, capteur.getZ());
        values.put(CapteurDAO.RIME_ADRESS, capteur.getRimeAdress());
        values.put(CapteurDAO.IP_ADRESS, capteur.getIpAdress());
        values.put(CapteurDAO.TIME_, capteur.getTime());
        values.put(CapteurDAO.DEVIATION_FACTOR, capteur.getDeviationFactor());
        values.put(CapteurDAO.CPU, capteur.getCpu());
        mDb.insert(CapteurDAO.TABLE_NAME, null, values);
    }

    public void modifier(Capteur capteur) {
        ContentValues values = new ContentValues();
        values.put(x, capteur.getX());
        values.put(y, capteur.getY());
        values.put(z, capteur.getZ());
        values.put(RIME_ADRESS, capteur.getRimeAdress());
        values.put(IP_ADRESS, capteur.getIpAdress());
        values.put(TIME_, capteur.getTime());
        values.put(DEVIATION_FACTOR, capteur.getDeviationFactor());
        values.put(CPU, capteur.getCpu());
        mDb.update(TABLE_NAME, values, ID + " =?", new String[] {String.valueOf(capteur.getId())});

    }

    public void supprimer(long id) {

        mDb.delete(TABLE_NAME, ID + " = ?", new String[]{String.valueOf(id)});

    }

    public void selectionner(long id) {

    }
}