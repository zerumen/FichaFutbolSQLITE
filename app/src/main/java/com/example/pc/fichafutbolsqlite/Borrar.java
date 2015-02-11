package com.example.pc.fichafutbolsqlite;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Pc on 07/02/2015.
 */
public class Borrar extends Activity {
    private TextView nom;
    private TextView ape;
    private TextView nif;
    private TextView sex;
    private TextView categ;
    SQLiteConfig jug;
    SQLiteDatabase db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrar);

        nom=(TextView)findViewById(R.id.nombre);
        ape=(TextView)findViewById(R.id.apellidos);
        nif=(TextView)findViewById(R.id.dni);
        sex=(TextView)findViewById(R.id.sexo);
        categ=(TextView)findViewById(R.id.categoria);
        Button cancel=(Button)findViewById(R.id.button10);
        Button del=(Button)findViewById(R.id.button9);
        jug=new SQLiteConfig(this,"DBJugadores",null,1);
        db=jug.getWritableDatabase();

        final String nif2=getIntent().getStringExtra("dni");
        Cursor c=db.rawQuery("SELECT * FROM Jugadores WHERE dni='"+nif2+"'",null);
        if(c.moveToNext()){
            String nomb=c.getString(0);
            String apex=c.getString(1);
            String dnix=c.getString(2);
            String sexi=c.getString(3);
            String cate=c.getString(4);

            nom.append(nomb);
            ape.append(apex);
            nif.append(dnix);
            sex.append(sexi);
            categ.append(cate);
        }
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Borrar.this,MainActivity.class);
                startActivity(i);
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(Borrar.this);
                builder.setMessage("¿Desea Eliminar el Registro?")
                        .setTitle("Advertencia")
                        .setCancelable(false)
                        .setNegativeButton("Cancelar",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                })
                        .setPositiveButton("Eliminar",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    db.delete("Jugadores","dni='"+nif2+"'",null);
                                        Toast.makeText(Borrar.this,"Registro Eliminado!",Toast.LENGTH_LONG).show();
                                        Intent i=new Intent(Borrar.this,MainActivity.class);
                                        startActivity(i);
                                    }
                                });
                AlertDialog alert= builder.create();
                alert.show();
            }
        });
    }
}
