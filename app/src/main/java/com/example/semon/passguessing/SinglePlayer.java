package com.example.semon.passguessing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class SinglePlayer extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CardView cardView=(CardView)findViewById(R.id.levelone);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SinglePlayer.this, GameZone.class);
                intent.putExtra("tryamout","15");
                intent.putExtra("level","0");
                startActivity(intent);
            }
        });
        CardView cardView2=(CardView)findViewById(R.id.leveltwo);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SinglePlayer.this, GameZone.class);
                intent.putExtra("tryamout","12");
                intent.putExtra("level","1");
                startActivity(intent);
            }
        });
        CardView cardView3=(CardView)findViewById(R.id.levelthree);
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SinglePlayer.this, GameZone.class);
                intent.putExtra("tryamout","9");
                intent.putExtra("level","2");
                startActivity(intent);
            }
        });
        CardView cardView4=(CardView)findViewById(R.id.levelfour);
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SinglePlayer.this, GameZone.class);
                intent.putExtra("tryamout","6");
                intent.putExtra("level","3");
                startActivity(intent);
            }
        });
        CardView cardView5=(CardView)findViewById(R.id.levelfive);
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SinglePlayer.this, GameZone.class);
                intent.putExtra("tryamout","10");
                intent.putExtra("level","4");
                startActivity(intent);
            }
        });
        CardView cardView6=(CardView)findViewById(R.id.levelsix);
        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SinglePlayer.this, GameZone.class);
                intent.putExtra("tryamout","14");
                intent.putExtra("level","5");
                startActivity(intent);
            }
        });
        CardView cardView7=(CardView)findViewById(R.id.levelseven);
        cardView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SinglePlayer.this, GameZone.class);
                intent.putExtra("tryamout","18");
                intent.putExtra("level","6");
                startActivity(intent);
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
