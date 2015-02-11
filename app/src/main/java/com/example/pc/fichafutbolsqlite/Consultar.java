package com.example.pc.fichafutbolsqlite;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Pc on 08/02/2015.
 */
public class Consultar extends Activity {
    private TextView txtResul;
    private Button volver;
    SQLiteConfig jug;
    SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultar);
        txtResul=(TextView)findViewById(R.id.resultado);
        volver=(Button)findViewById(R.id.button12);
        jug=new SQLiteConfig(this,"DBJugadores",null,1);
        db=jug.getWritableDatabase();

        final String nif2=getIntent().getStringExtra("dni");
        final String nomb=getIntent().getStringExtra("nombre");
        final String apes=getIntent().getStringExtra("apellidos");
        final String se=getIntent().getStringExtra("sexo");
        final String ca=getIntent().getStringExtra("categoria");
        String cad="";

        if(nif2!=null){
            Cursor c=db.rawQuery("SELECT * FROM Jugadores WHERE dni='"+nif2+"'",null);
            if(c.moveToNext()) {
                String non = c.getString(0);
                String apex = c.getString(1);
                String dnix = c.getString(2);
                String sexi = c.getString(3);
                String cate = c.getString(4);

                txtResul.append(non,"||",apex,"||",dnix,"||",sexi,"||",cate);

            }

            }
    }

}
