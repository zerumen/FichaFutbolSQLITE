package com.example.pc.fichafutbolsqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Pc on 07/02/2015.
 */
public class Activity3 extends Activity {
    EditText dnix;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3);
        dnix=(EditText)findViewById(R.id.editText4);
        Button consult=(Button)findViewById(R.id.button4);
        consult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Activity3.this,Actualizar.class);
                i.putExtra("dni",dnix.getText().toString());
                startActivity(i);
            }
        });
    }
}

