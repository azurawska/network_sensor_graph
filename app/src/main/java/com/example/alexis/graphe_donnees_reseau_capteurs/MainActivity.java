package com.example.alexis.graphe_donnees_reseau_capteurs;

import android.app.Activity;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends Activity {

    private CapteurDbAdapter dbHelper;
    private SimpleCursorAdapter dataAdapter_capteur;
    private SimpleCursorAdapter dataAdapter_liaison;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new CapteurDbAdapter(this);
        dbHelper.open();

        displayListView();
    }

    private void displayListView() {

        dbHelper.creerTables();

        dbHelper.ajouter(null, "12.0", "13.0", "0.0", "0.0", "127.0.0.1", "237", "1.0", "2.9");
        dbHelper.ajouter(null, "27.5", "30.7", "23", "1.0", "255.255.255.255", "300", "20.0", "4.0");
        dbHelper.ajouter(null, "50.0", "60.0", "70.0", "3.67", "0.0.0.0", "500", "5.0", "1.6");

        Cursor cursor_capteur = dbHelper.selectionnerCapteurs();

        String[] columns_capteur = new String[] {CapteurDbAdapter.ID, CapteurDbAdapter.x
        , CapteurDbAdapter.y, CapteurDbAdapter.z, CapteurDbAdapter.RIME_ADRESS
        , CapteurDbAdapter.IP_ADRESS, CapteurDbAdapter.TIME_, CapteurDbAdapter.DEVIATION_FACTOR
        , CapteurDbAdapter.CPU};

        int[] to_capteur = new int[] {R.id.Id,
        R.id.x,
        R.id.y,
        R.id.z,
        R.id.RIME_ADRESS,
        R.id.IP_ADRESS,
        R.id.TIME_,
        R.id.DEVIATION_FACTOR,
        R.id.CPU};

        dbHelper.ajouterLiaison(null, "1", "2");
        dbHelper.ajouterLiaison(null, "1", "3");
        dbHelper.ajouterLiaison(null, "2", "1");
        dbHelper.ajouterLiaison(null, "3", "1");

        Cursor cursor_liaison = dbHelper.selectionnerLaisonsCapteurs();

        String[] columns_liaison = new String[]{CapteurDbAdapter.id_liaison, CapteurDbAdapter.id_emetteur, CapteurDbAdapter.id_receveur};

        int[] to_liaison = new int[]{R.id.Id_liaison, R.id.id_emetteur, R.id.id_recepteur};

        dataAdapter_capteur = new SimpleCursorAdapter(this, R.layout.layout_liaison_capteurs,
                cursor_capteur, columns_capteur, to_capteur, 0);

        dataAdapter_liaison = new SimpleCursorAdapter(this, R.layout.layout_liaison_capteurs,
                cursor_liaison, columns_liaison, to_liaison, 0);

        ListView listView_capteur = (ListView) findViewById(R.id.listView1);

        listView_capteur.setAdapter(dataAdapter_capteur);

        ListView listView_liaison = (ListView) findViewById(R.id.listview2);

        listView_liaison.setAdapter(dataAdapter_liaison);
    }
}