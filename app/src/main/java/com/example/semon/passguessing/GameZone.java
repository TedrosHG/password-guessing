package com.example.semon.passguessing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameZone extends AppCompatActivity {
    EditText userguess;
    TextView userguessing,Tcorrect,Tposition,tryhold,levelInfo,level;
    Editable trying;
    String usersnumber,correct1,position1,info="";
    int parsing,trycount,trycount2,levelteller;
    int guess[]={0,0,0,0};
    int player[]={0,0,0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_zone);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Intent intent=getIntent();
        String message=intent.getStringExtra("tryamout");
        String message2=intent.getStringExtra("level");
        trycount=Integer.parseInt(message);
        levelteller=Integer.parseInt(message2);
        trycount2=trycount;
        Button b1=findViewById(R.id.Send);
        tryhold = findViewById(R.id.tryholder);
        level= findViewById(R.id.levelTeller);
        levelInfo= findViewById(R.id.levelInfo);

        if (trycount2==15 || trycount2==12 || trycount2==9 || trycount2==6)
        {
            info=" ** On This Level Zero And Reputation are Not included in the Password";
            levelInfo.setText(info);
        }
        if (trycount2==10)
        {
            info=" ** On This Level Zero is allowed but Reputation is not included in the password";
            levelInfo.setText(info);
        }
        if (trycount2==14)
        {
            info=" ** On This Level Zero not allowed but Reputation is included in the password";
            levelInfo.setText(info);
        }
        if (trycount2==18)
        {
            info=" ** On This Level Zero and Reputation are included in the password";
            levelInfo.setText(info);
        }
        tryhold.setText("Try: "+trycount);
        level.setText("Level: "+levelteller);
        /*     Generating The password using Random.number generater....*/
        if (trycount2==15 || trycount2==12 || trycount2==9 || trycount2==6)
        {
            /* Sending The variables to the password generator */
            passwordGenerator(1,1);
        }
        else if (trycount2==10)
        {
            /* Sending The variables to the password generator */
            passwordGenerator(0,1);
        }
        else if (trycount2==14)
        {
            /* Sending The variables to the password generator */
            passwordGenerator(1,0);
        }
        else if (trycount2==18)
        {
            /* Sending The variables to the password generator */
            passwordGenerator(0,0);
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userguess = findViewById(R.id.userinput);
                Tcorrect = findViewById(R.id.correct);
                Tposition = findViewById(R.id.position);
                trying = userguess.getText();
                usersnumber = trying.toString();

                /*   changing the users input into integer array       */

                int ik=usersnumber.length();
                if (ik==4)
                {
                    int first=Integer.parseInt(usersnumber.substring(0,1));
                    int second=Integer.parseInt(usersnumber.substring(1,2));
                    int third=Integer.parseInt(usersnumber.substring(2,3));
                    int fourth=Integer.parseInt(usersnumber.substring(3));
                    player[0]=first;
                    player[1]=second;
                    player[2]=third;
                    player[3]=fourth;
                    parsing=0;
                }
                else
                {
                    parsing=-1;
                }

                /* checking if the users input has qualified to the requirment of the level, like reputation and zero                  */
                if (parsing==0)
                {
                    int error=0;
                    if (trycount2==15 || trycount2==12 || trycount2==9 || trycount2==6)
                    {
                        /* If it is from level one to level four, it sends a=1 and b=1 to the cheuckError(). Which means zero and reputation is not allowed*/
                        error=cheuckError(1,1);
                    }
                    else if (trycount2==10)
                    {
                        /* If the user is playing in level five=5 , it will send a=0. Which means Zero is allowed, and the player can enter Zero in his guess*/
                        error=cheuckError(0,1);
                    }
                    else if (trycount2==14)
                    {
                        /* If the user is playing in level Six=6 , it will send b=0. Which means reputaiton is allowed, and the player can repeat numbers in her guess*/
                        error=cheuckError(1,0);
                    }
                    else if (trycount2==18)
                    {
                        /* If the user is playing in level Seven=7 , it will send a=0 and b=0. Which means the error function will not return error if the user enters zero of reputation of number in her guess*/
                        error=cheuckError(0,0);
                    }
                    /*  if there is no error in the users input !!!!! here it will start calculating the correct and position variables of the user input*/

                    if (error!=1 && error!=2) {

                        userguessing = findViewById(R.id.userGuess);
                        userguess.setText("");
                        levelInfo.setText("");
                        String forinterface = "";
                        for (int i = 0; i < 4; i++) {
                            forinterface = forinterface + player[i];
                        }
                        userguessing.append(forinterface + "\n");

                        int corre = 0;
                        int posit = 0;
                        int n;
                        for (int i = 0; i < 4; i++) {
                            n=0;
                            /*  This for loop checks For Correct Guess, by comparing it with the Password generated*/
                            for (int j = 0; j < 4; j++) {
                                if (player[j] == guess[i] && n==0) {
                                    corre += 1;
                                    n++;
                                }
                            }
                            /* This if statement Checks if the correct ones are in their exact position or not*/
                            if (player[i] == guess[i]) {
                                posit += 1;
                            }
                        }
                        /*  Displaying the variables into the users interface by appending them to the textView                  */
                        correct1 = "   " + corre;
                        position1 = "   " + posit;
                        Tposition.append(position1 + "\n");
                        Tcorrect.append(correct1 + "\n");
                        /* Until the position is not 4 (if the player is not cracking the password ), it will decrease the amount of trial by one */
                        if (posit != 4)
                        {
                            if (trycount > 0) {
                                trycount -= 1;
                                tryhold.setText("Try: " + trycount);
                                if (trycount == 0) {
                                    /* If the amount of try is Zero it means the Game is over. So it checks if the game is over or not*/
                                    String semere="";
                                    String text="GAME OVER ";
                                    for (int k=0;k<4;k++)
                                    {
                                        semere=semere+guess[k];
                                    }
                                    userguessing.append("\n"+text);
                                    Tcorrect.append("\n PASSWORD : "+semere);
                                    Toast.makeText(getApplicationContext(),"GAME OVER !!!",Toast.LENGTH_LONG).show();
                                }
                            } else {
                                System.exit(0);
                            }
                        }
                        /*  But if the position is 4, This means the player cracked the password, so it will take him to Winner page by transfering some
                         * data. like the password, tryamount left, orginal tryamount*/
                        else
                        {
                            /* Changing the Password into String, in order to send it and display it in the Winners page*/
                            String semere="";
                            for (int k=0;k<4;k++)
                            {
                                semere=semere+guess[k];
                            }
                            Intent intent1=new Intent(GameZone.this,Winner.class);
                            /* It takes trycount2(Which holds the orginal amount of try) which is used to know in what level was the player, by this to send him to the next level*/
                            intent1.putExtra("nextlevel",trycount2+"");
                            /* It takes trycount (which is the amount of try left in the current level of the game, which will be diplayed in the Winners page)*/
                            intent1.putExtra("tryamount",trycount+"");
                            /* Sending the level teller number in to the winner page */
                            intent1.putExtra("levelTeller",levelteller+"");
                            /* It takes semere ( which is the orginal password generated) it will be displayed in the winners page*/
                            intent1.putExtra("passwordis",semere+"");
                            startActivity(intent1);
                        }
                    }
                    /*  If their is any error in the players(Users) input it will display pop up message telling him what was his/hers mistake*/
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
    /* This function generates password using Random Class generator liberary,
     * it accepts two inputs
     *  =====the first one is int a = which is used to tell if zero can be included in the
     *  password. if the value of a=0, this means zero can be generated in the password.
     *  else if a=1, this means zero is not allowed in the password
     *  =====the second one is int b= which is used to tell if reputation is allowed in the
     *  password. if the value of b=0, this means reputation is allowed in the password.
     *  else if b=1, this means reputation is not allowed in the password*/

    public void passwordGenerator(int a, int b)
    {
        int holder;
        int i=0;
        int c;
        Random random=new Random();
        while (i<4){
            int coun=0;
            /* For This Case if a=1( zero is not allowed), so c=9. B/se the end(in Random) must not include 10*/
            if (a==1)
            {
                c=9;
            }
            /* For This Case if a=0( zero is allowed), so c=10. B/se the end(in Random) must include 10*/
            else
            {
                c=10;
            }
            holder=a+ random.nextInt(c);
            for (int j=0;j<4;j++)
            {
                if (guess[j]==holder)
                {
                    coun+=1;
                }
            }
            /* if only reputation is not allowed, it checks for the value of count= which counts the amount of reputation in the
             * generated password. if it is 0, password passed, if not fail( it goes back to generate another digit)*/

            if (b==1)
            {
                if (coun==0)
                {
                    guess[i]=holder;
                    i=i+1;
                }
            }
            else
            {
                guess[i]=holder;
                i=i+1;
            }
        }
    }
    /* This function Cheucks error, errors differ in each level. So, it accepts two variables that can tell what level the player is
     *  =====the first one is int a = which is used to tell if zero can be included in the
     *  users input. if the value of a=0, this means users input can contain zero.
     *  else if a=1, this means zero is not allowed in the users input,if it does it will return error.
     *  =====the second one is int b= which is used to tell if reputation is allowed in the
     *  users input. if the value of b=0, this means the user can repeat numbers in his one trials.
     *  else if b=1, this means reputation is not allowed in the users input*/

    public int cheuckError(int a, int b)
    {
        int error=0;
        /* For This Case Both reputation and zero are not allowed, So it cheucks for both conditions */
        if (a==1&&b==1)
        {
            /* Checking for any kind of reputation*/
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
            /* Checking for any zero in the users input */
            for (int i = 0; i < 4; i++)
            {
                if (player[i]==0)
                {
                    error=2;
                }
            }
        }
        /* For This Case Zero is allowed but reputation is not allowed, So it only checks for reputation*/
        else if (a==0&&b==1)
        {
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
        }
        /* For This Case Zero is not allowed but reputation is allowed, it only checks for Zero*/
        else if (a==1&&b==0)
        {
            for (int i = 0; i < 4; i++)
            {
                if (player[i]==0)
                {
                    error=2;
                }
            }
        }
        /* For This Case Bothe are allowed, so the users input or guess will not have any techiniqual errors*/
        else if (a==0&&b==0)
        {
            error=0;
        }
        return error;
    }

    /* This function is used for the back home button which is found in the upper right corner of the apptheme. it takes
     * the id of the parent when it goes to the child and if it is pressed, it goes one step back.*/
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
