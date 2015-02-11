package com.example.pc.fichafutbolsqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Pc on 08/02/2015.
 */
public class Activity5 extends Activity {
    EditText dnix;
    EditText nomb;
    EditText apex;
    EditText sexi;
    EditText categ;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity5);
        dnix=(EditText)findViewById(R.id.dni);
        nomb=(EditText)findViewById(R.id.nom);
        apex=(EditText)findViewById(R.id.ape);
        sexi=(EditText)findViewById(R.id.sexo);
        categ=(EditText)findViewById(R.id.categ);
        Button cancel=(Button)findViewById(R.id.cancelar);
        Button acept=(Button)findViewById(R.id.aceptar);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Activity5.this,MainActivity.class);
                startActivity(i);

            }
        });
        acept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Activity5.this,Consultar.class);
                i.putExtra("dni",dnix.getText().toString());
                i.putExtra("nombre",nomb.getText().toString());
                i.putExtra("apellidos",apex.getText().toString());
                i.putExtra("sexo",sexi.getText().toString());
                i.putExtra("categoria",categ.getText().toString());
                startActivity(i);
            }
        });


    }
}
