package com.example.asus.nfccasino;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Gregory Vesic
 * @version 21/02/2017
 */
public class ProfilActivity extends AppCompatActivity {
    User user;
    CreditCard creditCard;
    Payment payment;

    /**
     * Méthode onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        user = getIntent().getExtras().getParcelable("user");
        creditCard = new CreditCard();
        payment = new Payment();
        //user.cryptData();

        TextView txtLogin = (TextView) findViewById(R.id.txtFirstname);
        //txtLogin.setText(user.getEmail());

        TextView txtBalance = (TextView) findViewById(R.id.txtBalance);
        txtBalance.setText(String.valueOf(user.getBalance()) + " €");

        Button btnAddCard = (Button) findViewById(R.id.btnAddCard);
        btnAddCard.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
        {
            @Override
            public void onClick(View activity_main)    //Au clic sur le bouton
            {
                Intent intent = new Intent(ProfilActivity.this, AddCardActivity.class);  //Lancer l'activité
                intent.putExtra("user", user); // Envoyer l'activité
                intent.putExtra("creditCard", creditCard); // Envoyer l'activité
                startActivity(intent);    //Afficher la vue
                finish();
            }

        });

        Button btnDelCard = (Button) findViewById(R.id.btnDelCard);
        btnDelCard.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
        {
            @Override
            public void onClick(View activity_main)    //Au clic sur le bouton
            {
                Intent intent = new Intent(ProfilActivity.this, DelCardActivity.class);  //Lancer l'activité
                intent.putExtra("user", user); // Envoyer l'activité
                intent.putExtra("creditCard", creditCard); // Envoyer l'activité
                startActivity(intent);    //Afficher la vue
                finish();
            }

        });

        Button btnCredit = (Button) findViewById(R.id.btnAddCredit);
        btnCredit.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
        {
            @Override
            public void onClick(View activity_main)    //Au clic sur le bouton
            {
                Intent intent = new Intent(ProfilActivity.this, CreditActivity.class);  //Lancer l'activité
                intent.putExtra("user", user); // Envoyer l'activité
                intent.putExtra("creditCard", creditCard); // Envoyer l'activité
                startActivity(intent);    //Afficher la vue
                finish();
            }

        });

        final EditText editAmount = (EditText) findViewById(R.id.editAmount);
        Button btnAddPayment = (Button) findViewById(R.id.btnAddPayment);
        btnAddPayment.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
        {
            @Override
            public void onClick(View activity_main)    //Au clic sur le bouton
            {
                if (Double.valueOf(editAmount.getText().toString()) > user.getBalance()) {
                    Context context = getApplicationContext();
                    CharSequence text = "Erreur: votre solde ne vous autorise cette somme.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else if (Double.valueOf(editAmount.getText().toString()) == 0.0) {
                    Context context = getApplicationContext();
                    CharSequence text = "Erreur: veuillez saisir un autre montant.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    payment.setAmount(Double.valueOf(editAmount.getText().toString()));
                    Intent intent = new Intent(ProfilActivity.this, AddPaymentActivity.class);  //Lancer l'activité
                    intent.putExtra("user", user); // Envoyer l'activité
                    intent.putExtra("payment", payment); // Envoyer l'activité
                    startActivity(intent);    //Afficher la vue
                    finish();
                }
            }

        });

        Button back = (Button) findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
        {
            @Override
            public void onClick(View activity_main)    //Au clic sur le bouton
            {
                Intent intent = new Intent(ProfilActivity.this, MainActivity.class);  //Lancer l'activité
                //intent.putExtra("user", user); // Envoyer l'activité
                startActivity(intent);    //Afficher la vue
                finish();
            }

        });
    }
}

