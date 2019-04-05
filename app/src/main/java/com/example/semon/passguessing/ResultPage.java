package com.example.semon.passguessing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultPage extends AppCompatActivity {

    TextView oneik,two,three;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);/*
        BluetoothConnectionService mBluetoothConnection = null;*/
        Intent intent=getIntent();
        final String yourPassword=intent.getStringExtra("yourPassword");
        final String tryamounts=intent.getStringExtra("tryAmount");
        final String oppenetsPassword=intent.getStringExtra("oppenentsPassword");

        oneik=(TextView) findViewById(R.id.yourpassword);
        two=(TextView) findViewById(R.id.oppenentspassword);
        three=(TextView) findViewById(R.id.tryamount);

        oneik.setText("Your Password is: "+yourPassword);
        two.setText("Your Opponents Password is:"+oppenetsPassword);
        three.setText("You Cracked The Password in : "+tryamounts+" tries");

        Button b1=findViewById(R.id.home);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(ResultPage.this,MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}
