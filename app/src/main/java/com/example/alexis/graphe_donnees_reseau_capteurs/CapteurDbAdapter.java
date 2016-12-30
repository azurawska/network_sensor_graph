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

    private final static String TAG = "CapteurDbAdapter"; //Nom enregistré pour les logs

    private final static String TABLE_NAME_capteur="capteur"; //Nom de la table contenant les données des différents capteurs

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

    private final static String TABLE_NAME_liaison="liaison"; //Nom de la table contenant les liaisons entre les différents capteurs

    //Il est important de noter que la création de la table liaison, ainsi que sa destruction, auraient dû être gérées par une autre classe Adapter mais nous avons voulu simplifier, les liaisons concernant des capteurs.
    //De plus, nous voulions afficher les liaisons des capteurs dans la même view que les informations de ceux-ci (c'est d'ailleurs pour ça que nous avons adopté cette solution).

    private final static String CREATE_TABLE_LIAISON = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_liaison + " ("
            + id_liaison + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + id_emetteur + " INTEGER, "
            + id_receveur + " INTEGER, "
            + "FOREIGN KEY("+id_emetteur+") REFERENCES " + TABLE_NAME_capteur+"("+ID+"), "
            + "FOREIGN KEY("+id_receveur+") REFERENCES " + TABLE_NAME_capteur+"("+ID+"));";

    private final static String DROP_TABLE_LAISION = "DROP TABLE IF EXISTS " + TABLE_NAME_liaison;

    private final static int VERSION=1; //Version de la base de données

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase db=null;

    private final Context mCtx;

    private final static String DB_NAME="/data/user/0/com.example.alexis.graphe_donnees_reseau_capteurs/databases/capteurs"; //Base de données contenues dans l'émulateur ou le smartphone au lancement de l'application

    /**
     * Classe interne permettant de créer la base de données et sélectionner les données à afficher. Cette classe fait appel aux attributs définis dans la classe CapteurDbAdaptater.
     */

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

    /**
     * Méthode permettant d'ouvrir le fichier de données représentant la base de données contenant les tables capteur et liaison afin d'ouvrir la base de données ou la créer si elle n'existe pas.
     * @throws SQLException
     */

    public void open() throws SQLException {

        String dbFileName = "capteurs"; //Nom de la base de données

        mDbHelper = new DatabaseHelper(mCtx);

        try {
            File dbFile = mCtx.getDatabasePath(dbFileName); //Permet de récupérer le chemin d'accès du fichier passé en paramètre
            db = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
        } catch (Exception e) {
            String databasePath = mCtx.getFilesDir().getPath() + "/" + dbFileName;
            File databaseFile = new File(databasePath);
            db = SQLiteDatabase.openOrCreateDatabase(databaseFile, null);
        }
    }

    /**
     * Méthode permettant de fermer l'accès à la base de données.
     */

    public void close() {
        if(mDbHelper!=null) {
            mDbHelper.close();
        }
    }

    /**
     * Sélectionne toutes les données des capteurs contenues dans la table capteur.
     * @return La liste des données de chaque capteur.
     */

    public Cursor selectionnerCapteurs() {

        Cursor mCursor = db.query(TABLE_NAME_capteur, new String[]{ID, x, y, z, RIME_ADRESS, IP_ADRESS, TIME_, DEVIATION_FACTOR, CPU}, null, null, null, null, null);

        if(mCursor!=null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    /**
     * Sélectionne toutes les liaisons entre capteurs
     * @return La liste des liaisons entre capteurs.
     */

    public Cursor selectionnerLaisonsCapteurs() {
        Cursor mCursor = db.query(TABLE_NAME_liaison, new String[]{id_liaison, id_emetteur, id_receveur}, null, null, null, null, null);

        if(mCursor!=null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    /**
     * Permet d'ajouter un nouveau capteur à la table capteur.
     * Il est à noter qu'il aurait pu être plus simple de passer une variable Capteur en paramètre et de faire appel à ses getters.
     * Toutefois, cela nous obligeait à créer une classe de type CapteurDAO.
     * @param id
     * @param X
     * @param Y
     * @param Z
     * @param rimeAdress
     * @param ipAdress
     * @param time
     * @param deviationFactor
     * @param cpu
     */

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

    /**
     * Permet d'ajouter une nouvelle liaison entre deux capteurs. On aurait pu faire la même chose concernant le DAO, comme expliqué au-dessus.
     * @param idLiaison
     * @param idEmetteur
     * @param idReceveur
     */

    public void ajouterLiaison(String idLiaison, String idEmetteur, String idReceveur) {
        ContentValues values = new ContentValues();
        values.put(id_liaison, idLiaison);
        values.put(id_emetteur, idEmetteur);
        values.put(id_receveur, idReceveur);
        db.insert(TABLE_NAME_liaison, null, values);
    }

    /**
     * Permet de créer les tables de la base de données. On commence par détruire les deux tables pour les recréer ensuite.
     * On commence par détruire la table liaison qui possède des clés étrangères référençant la table capteur.
     * Ensuite, on détruit la table capteur.
     * Après, on recrée la table capteur qui n'a aucune clé étrangère (on commence toujours par les tables sans clé étrangère lors de la création de la base).
     * Pour finir, on crée la table liaison contenant les références vers la table capteur.
     */

    public void creerTables(){

        db.execSQL(DROP_TABLE_LAISION);
        db.execSQL(DROP_TABLE_CAPTEUR);
        db.execSQL(CREATE_TABLE_CAPTEUR);
        db.execSQL(CREATE_TABLE_LIAISON);
    }
}