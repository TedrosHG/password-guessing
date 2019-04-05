package com.example.semon.passguessing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Winner extends AppCompatActivity {

    int tryone,trytwo,levelTeller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        Intent intent=getIntent();
        String message=intent.getStringExtra("nextlevel");
        String message2=intent.getStringExtra("tryamount");
        String message3=intent.getStringExtra("passwordis");
        String message4=intent.getStringExtra("levelTeller");
        tryone=Integer.parseInt(message);
        trytwo=Integer.parseInt(message2);
        levelTeller=Integer.parseInt(message4);
        TextView textview=findViewById(R.id.tryamount);
        TextView textview2=findViewById(R.id.passwordis);
        textview.setText("you found the password in " + (tryone-trytwo+1)+" tries");
        textview2.append(message3);
        Button b1=findViewById(R.id.playagain);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Winner.this, GameZone.class);
                intent.putExtra("tryamout",tryone+"");
                intent.putExtra("level",levelTeller+"");
                startActivity(intent);
            }
        });
        Button b2=findViewById(R.id.nextlevel);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* If the players orginal try was 15,12,9, it means it needs to sub -3 to go to the next level....     */
                int va=0;
                if (tryone>6) /* It includes 18,15,14,12,10,9 */
                {
                    va = tryone % 3; /*  Only Those 15,12,9*/
                    if (va == 0) { /* To go to next level it only substracts 3 from the orginal try */
                        if (tryone==18)
                        {
                                Toast.makeText(getApplicationContext(),"Their is no Next level for now",Toast.LENGTH_LONG).show();
                        }
                        else
                            {
                                Intent intent = new Intent(Winner.this, GameZone.class);
                                intent.putExtra("tryamout", (tryone - 3) + "");
                                intent.putExtra("level",(levelTeller+1)+"");
                                startActivity(intent);
                            }
                    }
                    else  /* The try amount is 18,14,10 ... In this case it adds 4 try to the orginal try to go to the next level*/
                    {
                        if (tryone!=18) /* if the try amount is different from 18, which means if it not the last level... go to next level */
                        {
                            Intent intent = new Intent(Winner.this, GameZone.class);
                            intent.putExtra("tryamout", (tryone + 4) + "");
                            intent.putExtra("level",(levelTeller+1)+"");
                            startActivity(intent);
                        }

                    }
                }
                else if (tryone==6)  /* If the try amount is 6, it add 4 try to go to level five */
                {
                    Intent intent = new Intent(Winner.this, GameZone.class);
                    intent.putExtra("tryamout", (tryone + 4) + "");
                    intent.putExtra("level",(levelTeller+1)+"");
                    startActivity(intent);
                }
            }
        });
        Button b3=findViewById(R.id.homepage);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Winner.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
