package com.example.alexis.graphe_donnees_reseau_capteurs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by alexis on 30/11/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

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

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE_CAPTEUR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        Log.w(DatabaseHandler.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        sqLiteDatabase.execSQL(DROP_TABLE_CAPTEUR);
        onCreate(sqLiteDatabase);
    }
}