package com.example.test_img;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtTaille;
    private EditText txtPoids;
    private EditText txtAge;

    private RadioButton rdbFemme;
    private RadioButton rdbHomme;

    private Button btnCalculIMG;
    private TextView lblResultat;
    private TextView lblInterpretation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtTaille = findViewById(R.id.txtTaille);
        txtPoids = findViewById(R.id.txtPoids);
        txtAge = findViewById(R.id.txtAge);
        rdbFemme = findViewById(R.id.rdbFemme);
        rdbHomme = findViewById(R.id.rdbHomme);
        btnCalculIMG = findViewById(R.id.btnCalculIMG);
        lblResultat = findViewById(R.id.lblResultat);
        lblInterpretation = findViewById(R.id.lblInterpretation);

        btnCalculIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float Taille = Float.parseFloat(txtTaille.getText().toString());
                float Poids = Float.parseFloat(txtPoids.getText().toString());
                int Age = Integer.parseInt(txtAge.getText().toString());

                if (Taille == 0 || Poids == 0 || Age == 0) {
                    Toast.makeText(MainActivity.this, "Les champs sont vides !", Toast.LENGTH_SHORT).show();
                    return;
                }

                double IMC = Poids / ((Taille / 100.0) * (Taille / 100.0));

                double IMG;

                int sexe;
                if (rdbFemme.isChecked()) {
                    sexe = 0;
                } else {
                    sexe = 1;
                }

                if (Age >= 16) {
                    IMG = (1.20 * IMC) + (0.23 * Age) - (10.8 * sexe) - 5.4;
                } else {
                    IMG = (1.51 * IMC) + (0.70 * Age) - (3.6 * sexe) + 1.4;
                }

                String interp = interpIMG(IMG, sexe);
                lblResultat.setText(String.format("Votre IMG est : %.2f", IMG));
                lblInterpretation.setText("Interprétation : " + interp);

            }
        });
    }

    private String interpIMG(double IMG, int sexe) {
        if (sexe == 0) {
            if (IMG < 25) {
                return "Trop maigre";
            } else if (IMG >= 25 && IMG <= 30) {
                return "Pourcentage normal";
            } else {
                return "Trop de graisse";
            }
        } else {
            if (IMG < 15) {
                return "Trop maigre";
            } else if (IMG >= 15 && IMG <= 20) {
                return "Pourcentage normal";
            } else {
                return "Trop de graisse";
            }
        }
    }
}
