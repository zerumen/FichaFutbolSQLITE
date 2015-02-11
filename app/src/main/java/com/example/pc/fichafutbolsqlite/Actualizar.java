package com.example.pc.fichafutbolsqlite;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Pc on 07/02/2015.
 */
public class Actualizar extends Activity {
    private EditText nom;
    private EditText ape;
    private EditText dnix;
    private EditText sex;
    private EditText cate;
    SQLiteConfig jug;
    SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actualizar);

        nom=(EditText)findViewById(R.id.editText5);
        ape=(EditText)findViewById(R.id.editText6);
        dnix=(EditText)findViewById(R.id.editText7);
        sex=(EditText)findViewById(R.id.editText8);
        cate=(EditText)findViewById(R.id.editText9);

        Button cancel=(Button)findViewById(R.id.button6);
        Button guar=(Button)findViewById(R.id.button5);

        jug=new SQLiteConfig(this,"DBJugadores",null,1);
        db=jug.getWritableDatabase();

        final String nif2=getIntent().getStringExtra("dni");
        Cursor c=db.rawQuery("SELECT * FROM Jugadores where dni='"+nif2+"'",null);
        if(c.moveToNext()){
            String nomb=c.getString(0);
            String apex=c.getString(1);
            String dn=c.getString(2);
            String sexi=c.getString(3);
            String categ=c.getString(4);

            nom.append(nomb);
            ape.append(apex);
            dnix.append(dn);
            sex.append(sexi);
            cate.append(categ);
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Actualizar.this,MainActivity.class);
                startActivity(i);
            }
        });
        guar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(Actualizar.this);
                builder.setMessage("¿Desea Actualizar el Registro?")
                        .setTitle("Advertencia")
                        .setCancelable(false)
                        .setNegativeButton("Cancelar",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                })
                        .setPositiveButton("Continuar",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ContentValues registro=new ContentValues();
                                        registro.put("nombre",nom.getText().toString());
                                        registro.put("apellidos",ape.getText().toString());
                                        registro.put("dni",dnix.getText().toString());
                                        registro.put("sexo",sex.getText().toString());
                                        registro.put("categoria",cate.getText().toString());
                                        db.update("Jugadores",registro,"dni='"+nif2+"'",null);
                                        Toast.makeText(Actualizar.this,"Registro Actualizado",Toast.LENGTH_LONG).show();
                                        Intent i=new Intent(Actualizar.this,MainActivity.class);
                                        startActivity(i);
                                    }
                                });
                AlertDialog alert=builder.create();
                alert.show();
            }
        });

    }

}
