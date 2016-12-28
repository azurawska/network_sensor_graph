package com.example.alexis.graphe_donnees_reseau_capteurs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;

/**
 * Created by alexis on 23/12/2016.
 */
public class CapteurDbAdapter {

    public final static String ID="_id";
    public final static String x="x";
    public final static String y="y";
    public final static String z="z";
    public final static String RIME_ADRESS="RIME_ADRESS";
    public final static String IP_ADRESS="IP_ADRESS";
    public final static String TIME_="TIME_";
    public final static String DEVIATION_FACTOR="DEVIATION_FACTOR";
    public final static String CPU="CPU";

    public final static String id_liaison="_id";
    public final static String id_emetteur="id_emetteur";
    public static final String id_receveur="id_receveur";

    private final static String TAG = "CapteurDbAdapter";

    private final static String TABLE_NAME_capteur="capteur";

    private final static String CREATE_TABLE_CAPTEUR = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_capteur + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + x + " REAL NOT NULL,"
            + y + " REAL NOT NULL,"
            + z + " REAL NOT NULL,"
            + RIME_ADRESS + " TEXT NOT NULL,"
            + IP_ADRESS + " TEXT NOT NULL,"
            + TIME_ + " INTEGER NOT NULL,"
            + DEVIATION_FACTOR + " REAL NOT NULL,"
            + CPU + " REAL NOT NULL" +
            ");";

    private final static String DROP_TABLE_CAPTEUR = "DROP TABLE IF EXISTS " + TABLE_NAME_capteur;

    private final static String TABLE_NAME_liaison="liaison";

    private final static String CREATE_TABLE_LIAISON = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_liaison + " ("
            + id_liaison + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + id_emetteur + " INTEGER, "
            + id_receveur + " INTEGER, "
            + "FOREIGN KEY("+id_emetteur+") REFERENCES " + TABLE_NAME_capteur+"("+ID+"), "
            + "FOREIGN KEY("+id_receveur+") REFERENCES " + TABLE_NAME_capteur+"("+ID+"));";

    private final static String DROP_TABLE_LAISION = "DROP TABLE IF EXISTS " + TABLE_NAME_liaison;

    private final static int VERSION=1;

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase db=null;

    private final Context mCtx;

    private final static String DB_NAME="/data/user/0/com.example.alexis.graphe_donnees_reseau_capteurs/databases/capteurs";

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DB_NAME, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.w(TAG, CREATE_TABLE_CAPTEUR);
            sqLiteDatabase.execSQL(CREATE_TABLE_CAPTEUR);
            sqLiteDatabase.execSQL(CREATE_TABLE_LIAISON);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                            + newVersion + ", which will destroy all old data");
            sqLiteDatabase.execSQL(DROP_TABLE_LAISION);
            sqLiteDatabase.execSQL(DROP_TABLE_CAPTEUR);
            onCreate(sqLiteDatabase);
        }
    }

    public CapteurDbAdapter(Context context) {
        this.mCtx=context;
    }

    public void open() throws SQLException {

        /*String dbPath = DB_NAME;

        mDbHelper = new DatabaseHelper(mCtx);
        mDb = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);*/

        String dbFileName = "capteurs";

        mDbHelper = new DatabaseHelper(mCtx);

        try {
            File dbFile = mCtx.getDatabasePath(dbFileName);
            db = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
        } catch (Exception e) {
            String databasePath = mCtx.getFilesDir().getPath() + "/" + dbFileName;
            File databaseFile = new File(databasePath);
            db = SQLiteDatabase.openOrCreateDatabase(databaseFile, null);
        }
    }

    public void close() {
        if(mDbHelper!=null) {
            mDbHelper.close();
        }
    }

    public Cursor selectionnerCapteurs() {

        Cursor mCursor = db.query(TABLE_NAME_capteur, new String[]{ID, x, y, z, RIME_ADRESS, IP_ADRESS, TIME_, DEVIATION_FACTOR, CPU}, null, null, null, null, null);

        if(mCursor!=null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor selectionnerLaisonsCapteurs() {
        Cursor mCursor = db.query(TABLE_NAME_liaison, new String[]{id_liaison, id_emetteur, id_receveur}, null, null, null, null, null);

        if(mCursor!=null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public void ajouter(String id, String X, String Y, String Z, String rimeAdress, String ipAdress, String time, String deviationFactor, String cpu) {
        ContentValues values = new ContentValues();
        values.put(ID, id);
        values.put(x, X);
        values.put(y, Y);
        values.put(z, Z);
        values.put(RIME_ADRESS, rimeAdress);
        values.put(IP_ADRESS, ipAdress);
        values.put(TIME_, time);
        values.put(DEVIATION_FACTOR, deviationFactor);
        values.put(CPU, cpu);
        db.insert(TABLE_NAME_capteur, null, values);
    }

    public void ajouterLiaison(String idLiaison, String idEmetteur, String idReceveur) {
        ContentValues values = new ContentValues();
        values.put(id_liaison, idLiaison);
        values.put(id_emetteur, idEmetteur);
        values.put(id_receveur, idReceveur);
        db.insert(TABLE_NAME_liaison, null, values);
    }

    public void creerTables(){

        db.execSQL(DROP_TABLE_LAISION);
        db.execSQL(DROP_TABLE_CAPTEUR);
        db.execSQL(CREATE_TABLE_CAPTEUR);
        db.execSQL(CREATE_TABLE_LIAISON);
    }
}