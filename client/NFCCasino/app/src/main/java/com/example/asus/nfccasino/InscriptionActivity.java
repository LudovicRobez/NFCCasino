package com.example.asus.nfccasino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * @author Gregory Vesic
 * @version 21/02/2017
 */
public class InscriptionActivity extends AppCompatActivity {
    /**
     * Méthode onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        /*
         *  Evenements lors du clic sur les différents boutons
         */
        Button btnValider = (Button) findViewById(R.id.btnAdd);   //Appel du Bouton
        btnValider.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
        {
            @Override
            public void onClick(View activity_main)    //Au clic sur le bouton
            {
                Intent intent = new Intent(InscriptionActivity.this, MainActivity.class);  //Lancer l'activité
                startActivity(intent);    //Afficher la vue
            }
        });

        Button btnRetour = (Button) findViewById(R.id.btnRetour);   //Appel du Bouton
        //btnInscription.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
        btnRetour.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
        {
            public void onClick(View activity_main)    //Au clic sur le bouton
            {
                Intent intent = new Intent(InscriptionActivity.this, MainActivity.class);  //Lancer l'activité
                startActivity(intent);    //Afficher la vue
            }
        });
    }
}
