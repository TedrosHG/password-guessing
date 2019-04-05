package com.example.semon.passguessing;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = findViewById(R.id.single);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SinglePlayer.class);
                startActivity(intent);
            }
        });
        Button b2 = findViewById(R.id.multi);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Bluetooth.class);
                startActivity(intent);
            }
        });
        Button b3 = findViewById(R.id.rule);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Rules.class);
                startActivity(intent);
            }
        });
        Button b4=findViewById(R.id.quit);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.share,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.share:
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String sharebody="Your Body Here";
                String sharesub="Your Subject Here";
                myIntent.putExtra(Intent.EXTRA_TEXT,sharebody);
                myIntent.putExtra(Intent.EXTRA_SUBJECT,sharesub);
                startActivity(Intent.createChooser(myIntent,"Share using"));
                break;
            case R.id.aboutus:
                Intent intent = new Intent(MainActivity.this, AboutUs.class);
                startActivity(intent);
                break;
            case R.id.howtoplay:
                Intent intent1 = new Intent(MainActivity.this, Rules.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Do You Want to Exit?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }
}
