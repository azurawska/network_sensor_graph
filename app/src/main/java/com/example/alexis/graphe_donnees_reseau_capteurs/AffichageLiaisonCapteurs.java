package com.example.alexis.graphe_donnees_reseau_capteurs;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by alexis on 30/12/2016.
 */
public class AffichageLiaisonCapteurs extends Activity {

    private CapteurDbAdapter dbHelper;
    private SimpleCursorAdapter dataAdapter_liaison;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_liaisons);
        dbHelper = new CapteurDbAdapter(this);
        dbHelper.open();

        displayListView();

        dbHelper.close();
    }

    /**
     * Méthode permettant d'afficher les liaisons entre capteurs dans une ListView.
     */

    private void displayListView() {

        dbHelper.ajouterLiaison(null, "1", "2");
        dbHelper.ajouterLiaison(null, "1", "3");
        dbHelper.ajouterLiaison(null, "2", "1");
        dbHelper.ajouterLiaison(null, "3", "1");

        Cursor cursor_liaison = dbHelper.selectionnerLaisonsCapteurs();

        String[] columns_liaison = new String[]{CapteurDbAdapter.id_liaison, CapteurDbAdapter.id_emetteur, CapteurDbAdapter.id_receveur};

        int[] to_liaison = new int[]{R.id.Id_liaison, R.id.id_emetteur, R.id.id_recepteur};

        dataAdapter_liaison = new SimpleCursorAdapter(this, R.layout.layout_liaison_capteurs,
                cursor_liaison, columns_liaison, to_liaison, 0);

        ListView listView_capteur = (ListView) findViewById(R.id.listView2);

        listView_capteur.setAdapter(dataAdapter_liaison);

        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AffichageLiaisonCapteurs.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}