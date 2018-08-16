package com.marcus.fahrkosten;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity
{
    private void pruefeEingabe()
    {
        try
        {
            EditText kostenProTag = findViewById(R.id.eingabeKostenProTagID);
            EditText gefahreneKm = findViewById(R.id.eingabeEinfacheFahrtID);
            EditText preisProLiter = findViewById(R.id.eingabePreisProLiterID);
            EditText verbrauch = findViewById(R.id.eingabeVerbrauchID);

            double kpt = Double.parseDouble(String.valueOf(kostenProTag.getText()));
            double gkm = Double.parseDouble(String.valueOf(gefahreneKm.getText()));
            double ppl = Double.parseDouble(String.valueOf(preisProLiter.getText()));
            double ver = Double.parseDouble(String.valueOf(verbrauch.getText()));

            if(kpt>=0 && kpt<=100 && gkm>0 && gkm<5000 && ppl>0 && ppl<3 && ver>3 && ver<30)
            {
                berechneKosten();
            }
        }
        catch(NumberFormatException e)
        {
            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setTitle("Wert außerhalb");
            msg.setMessage("Bitte Wertebereiche beachten (siehe Intro)");
            msg.setCancelable(true);

            AlertDialog msgDialog = msg.create();
            msgDialog.show();
        }
    }

    private void berechneKosten()
    {
        EditText kostenProTag = findViewById(R.id.eingabeKostenProTagID);
        EditText gefahreneKm = findViewById(R.id.eingabeEinfacheFahrtID);
        EditText preisProLiter = findViewById(R.id.eingabePreisProLiterID);
        EditText verbrauch = findViewById(R.id.eingabeVerbrauchID);

        double kpt = Double.parseDouble(String.valueOf(kostenProTag.getText()));
        double gkm = Double.parseDouble(String.valueOf(gefahreneKm.getText()));
        double ppl = Double.parseDouble(String.valueOf(preisProLiter.getText()));
        double ver = Double.parseDouble(String.valueOf(verbrauch.getText()));

        double kosten = (((ver*gkm/100)*ppl)+kpt);
        double kostenHinUndZurueck = (2*((ver*gkm/100)*ppl)+kpt);

        TextView kostenAnzeige = findViewById(R.id.ausgabeKostenEinfacheFahrtID);
        TextView kostenAnzeigeHinUndZurueck = findViewById(R.id.ausgabeKostenHinUndZurueckID);

        NumberFormat formatter = NumberFormat.getInstance();
        formatter.setMaximumFractionDigits(2);

        kostenAnzeige.setText(formatter.format(kosten));
        kostenAnzeigeHinUndZurueck.setText(formatter.format(kostenHinUndZurueck));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonBerechne = findViewById(R.id.buttonBerechneID);
        buttonBerechne.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                pruefeEingabe();
            }
        });
    }
}
