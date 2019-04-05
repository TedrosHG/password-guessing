package com.example.semon.passguessing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MultiPlayer extends AppCompatActivity {

    EditText userguess;
    TextView userguessing,Tcorrect,Tposition,tryhold;
    Editable trying;
    String usersnumber,correct1,position1,myPasswords;
    int parsing,trycount=0,intpassword,oppenentstry=0;
    int guess[]={0,0,0,0};
    int player[]={0,0,0,0};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*BluetoothConnectionService mBluetoothConnection = null;*/
        Intent intent=getIntent();
        String message=intent.getStringExtra("oppenentPassword");
        intpassword=Integer.parseInt(message);
        String message2=intent.getStringExtra("myPassword");
        myPasswords=message2;
        Button b1=findViewById(R.id.Send);
        tryhold = findViewById(R.id.tryholder);
        tryhold.setText("Try: "+trycount);
        /*     changing password into Array....*/
        for (int i = 0; i < 4; i++) {
            if (intpassword > 0) {
                int n2 = intpassword % 10;
                guess[3 - i] = n2;
                intpassword = intpassword / 10;
            }
            else
            {
                intpassword = -1;
                break;
            }
        }
/////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////////////
/*
        final BluetoothConnectionService finalMBluetoothConnection = mBluetoothConnection;*/
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userguess = findViewById(R.id.userinput);
                Tcorrect = findViewById(R.id.correct);
                Tposition = findViewById(R.id.position);
                trying = userguess.getText();
                usersnumber = trying.toString();
                parsing = Integer.parseInt(usersnumber);

                /*   changing the users input into integer array       */
                for (int i = 0; i < 4; i++) {
                    if (parsing > 0) {
                        int n2 = parsing % 10;
                        player[3 - i] = n2;
                        parsing = parsing / 10;
                    }
                    else
                    {
                        parsing = -1;
                        break;
                    }
                }

                /*          sending data to the user interface                   */

                if (parsing==0)
                {
                    int error=0;
                    for (int i = 0; i < 3; i++)
                    {
                        for (int j = i+1; j < 4; j++)
                        {
                            if (player[i]==player[j])
                            {
                                error=1;
                            }
                        }
                    }
                    for (int i = 0; i < 4; i++)
                    {
                        if (player[i]==0)
                        {
                            error=2;
                        }
                    }

                    if (error!=1 && error!=2) {
                        userguessing = findViewById(R.id.userGuess);
                        String forinterface = "";
                        for (int i = 0; i < 4; i++) {
                            forinterface = forinterface + player[i];
                        }
                        userguessing.append(forinterface + "\n");

                        int corre = 0;
                        int posit = 0;
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (player[i] == guess[j]) {
                                    corre += 1;
                                }
                            }
                            if (player[i] == guess[i]) {
                                posit += 1;
                            }
                        }
                        correct1 = "   " + corre;
                        position1 = "   " + posit;
                        Tposition.append(position1 + "\n");
                        Tcorrect.append(correct1 + "\n");
                        userguess.setText("");
                        if (posit != 4)
                        {
                            trycount += 1;
                            tryhold.setText("Try: " + trycount);

                        }
                        else
                        {
                            String oppenentsPassword="";
                            for (int i=0;i<4;i++) {
                                oppenentsPassword += guess[i];
                            }
                            trycount += 1;/*
                            String trycount2=trycount+"";
                            byte[] bytes = trycount2.getBytes(Charset.defaultCharset());*//*
                            finalMBluetoothConnection.write(bytes);*/
                            Intent intent1=new Intent(MultiPlayer.this,ResultPage.class);
                            intent1.putExtra("yourPassword",myPasswords);
                            intent1.putExtra("oppenentsPassword",oppenentsPassword);
                            intent1.putExtra("tryAmount",""+trycount);/*
                            intent1.putExtra("oppenentstryAmount",oppenentstry);*/
                            startActivity(intent1);

                        }
                    }
                    else if (error==1)
                    {
                        Toast.makeText(getApplicationContext(),"Reputation is not allowed",Toast.LENGTH_LONG).show();
                    }
                    else if (error==2)
                    {
                        Toast.makeText(getApplicationContext(),"Zero is not allowed",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"The Password must be 4 digit",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
