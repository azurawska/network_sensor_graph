package com.example.alexis.graphe_donnees_reseau_capteurs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by alexis on 23/12/2016.
 */
public abstract class DAOBase {

    protected final static int VERSION = 1;

    protected final static String NOM = " /Applications/MAMP/db/sqlite/config.db";

    protected SQLiteDatabase mDb = null;

    protected DatabaseHandler mHandler = null;

    public DAOBase(Context pContext) {

        this.mHandler = new DatabaseHandler(pContext, NOM, null, VERSION);
    }

    public SQLiteDatabase open() {
        mDb = mHandler.getWritableDatabase();
        return mDb;
    }

    public void close() {
        mDb.close();
    }

    public SQLiteDatabase getmDb() {
        return this.mDb;
    }
}