package com.example.alexis.graphe_donnees_reseau_capteurs;

import android.app.Activity;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends Activity {

    private CapteurDbAdapter dbHelper;
    private SimpleCursorAdapter dataAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new CapteurDbAdapter(this);
        dbHelper.open();

        displayListView();
    }

    private void displayListView() {

        dbHelper.creerTableCapteur();

        dbHelper.ajouter(null, "12.0", "13.0", "0.0", "0.0", "127.0.0.1", "237", "1.0", "2.9");
        dbHelper.ajouter(null, "27.5", "30.7", "23", "1.0", "255.255.255.255", "300", "20.0", "4.0");
        dbHelper.ajouter(null, "50.0", "60.0", "70.0", "3.67", "0.0.0.0", "500", "5.0", "1.6");

        Cursor cursor = dbHelper.selectionnerCapteurs();

        String[] columns = new String[] {CapteurDbAdapter.ID, CapteurDbAdapter.x
        , CapteurDbAdapter.y, CapteurDbAdapter.z, CapteurDbAdapter.RIME_ADRESS
        , CapteurDbAdapter.IP_ADRESS, CapteurDbAdapter.TIME_, CapteurDbAdapter.DEVIATION_FACTOR
        , CapteurDbAdapter.CPU};

        int[] to = new int[] {R.id.Id,
        R.id.x,
        R.id.y,
        R.id.z,
        R.id.RIME_ADRESS,
        R.id.IP_ADRESS,
        R.id.TIME_,
        R.id.DEVIATION_FACTOR,
        R.id.CPU};

        dataAdapter = new SimpleCursorAdapter(this, R.layout.layout_capteur_infos,
                cursor, columns, to, 0);

        ListView listView = (ListView) findViewById(R.id.listView1);

        listView.setAdapter(dataAdapter);
    }
}