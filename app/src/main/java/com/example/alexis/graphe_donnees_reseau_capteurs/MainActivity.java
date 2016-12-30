package com.example.alexis.graphe_donnees_reseau_capteurs;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {

    private CapteurDbAdapter dbHelper;
    private SimpleCursorAdapter dataAdapter_capteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new CapteurDbAdapter(this);
        dbHelper.open();

        displayListView();

        dbHelper.close();
    }

    /**
     * Méthode permettant d'afficher les données des capteurs dans la ListView.
     */

    private void displayListView() {

        dbHelper.creerTables();

        //Les données sont ajoutées en brut.

        dbHelper.ajouter(null, "12.0", "13.0", "0.0", "0.0", "127.0.0.1", "237", "1.0", "2.9");
        dbHelper.ajouter(null, "27.5", "30.7", "23", "1.0", "255.255.255.255", "300", "20.0", "4.0");
        dbHelper.ajouter(null, "50.0", "60.0", "70.0", "3.67", "0.0.0.0", "500", "5.0", "1.6");

        Cursor cursor_capteur = dbHelper.selectionnerCapteurs();

        String[] columns_capteur = new String[] {CapteurDbAdapter.ID, CapteurDbAdapter.x
        , CapteurDbAdapter.y, CapteurDbAdapter.z, CapteurDbAdapter.RIME_ADRESS
        , CapteurDbAdapter.IP_ADRESS, CapteurDbAdapter.TIME_, CapteurDbAdapter.DEVIATION_FACTOR
        , CapteurDbAdapter.CPU}; //Tableau contenant toutes les caractéristiques d'un capteur

        int[] to_capteur = new int[] {R.id.Id,
        R.id.x,
        R.id.y,
        R.id.z,
        R.id.RIME_ADRESS,
        R.id.IP_ADRESS,
        R.id.TIME_,
        R.id.DEVIATION_FACTOR,
        R.id.CPU}; //Tableau contenant tous les identifiants des informations des capteurs contenus dans le fichier R (ressources)

        dataAdapter_capteur = new SimpleCursorAdapter(this, R.layout.layout_capteur_infos,
                cursor_capteur, columns_capteur, to_capteur, 0);

        ListView listView_capteur = (ListView) findViewById(R.id.listView1);

        listView_capteur.setAdapter(dataAdapter_capteur);

        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AffichageLiaisonCapteurs.class); //Charge la nouvelle activity à afficher
                startActivity(intent); //Permet d'afficher la nouvelle activity après le clic du bouton
            }
        });
    }
}