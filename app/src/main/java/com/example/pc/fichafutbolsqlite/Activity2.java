package com.example.pc.fichafutbolsqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.net.ContentHandler;

/**
 * Created by Pc on 03/02/2015.
 */
public class Activity2 extends Activity {
    EditText nom;
    EditText ape;
    EditText dni;
    RadioButton sexo;
    Spinner categoria;
    Button enviar;
    Button cancelar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        SQLiteConfig jbd=new SQLiteConfig(this,"DBJugadores",null,1);
        final SQLiteDatabase bd=jbd.getWritableDatabase();

        nom=(EditText)findViewById(R.id.editText);
        ape=(EditText)findViewById(R.id.editText2);
        dni=(EditText)findViewById(R.id.editText3);
        sexo=(RadioButton)findViewById(R.id.sexo);
        categoria=(Spinner)findViewById(R.id.spinner);
        enviar=(Button)findViewById(R.id.button2);
        cancelar=(Button)findViewById(R.id.button3);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues registro=new ContentValues();
                if(nom.getText().length()==0){
                    Toast.makeText(Activity2.this,"Nombre debe rellenarse",Toast.LENGTH_LONG).show();
                }else{
                    registro.put("nombre",nom.getText().toString());
                    if(ape.getText().length()==0){
                      Toast.makeText(Activity2.this,"Apellidos debe rellenarse",Toast.LENGTH_LONG).show();
                    }else{
                        registro.put("apellidos",ape.getText().toString());
                        if(dni.getText().length()==0){
                            Toast.makeText(Activity2.this,"DNI debe rellenarse",Toast.LENGTH_LONG).show();
                        }else{
                            registro.put("dni",dni.getText().toString());
                            registro.put("sexo",sexo.getText().toString());
                            registro.put("categoria",categoria.getSelectedItem().toString());
                            bd.insert("Jugadores",null,registro);
                            Toast.makeText(Activity2.this,"DATO INSERTADO",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Activity2.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
