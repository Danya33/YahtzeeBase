package com.cpt.ics3u.yahtzeebase;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
private ArrayList<ImageView> dice;
private int[] diceImage;
private Random rnd=new Random();
int rollcount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dice=new ArrayList<ImageView>();
        dice.add((ImageView)findViewById(R.id.dice1));
        dice.add((ImageView)findViewById(R.id.dice2));
        dice.add((ImageView)findViewById(R.id.dice3));
        dice.add((ImageView)findViewById(R.id.dice4));
        dice.add((ImageView)findViewById(R.id.dice5));
        diceImage=new int[6];
        diceImage[0]=R.drawable.dice1;
        diceImage[1]=R.drawable.dice2;
        diceImage[2]=R.drawable.dice3;
        diceImage[3]=R.drawable.dice4;
        diceImage[4]=R.drawable.dice5;
        diceImage[5]=R.drawable.dice6;

        rollDice(dice);
    }

    public void Roll(View view) {
        ArrayList<ImageView> d=new ArrayList<ImageView>();
        for (int x=0;x<dice.size();x++){
            if (dice.get(x).getImageAlpha()==255){
                d.add(dice.get(x));
            }
        }
        rollDice(d);
    }

    public void rollDice(ArrayList<ImageView> d){
        for (int x=0;x<d.size();x++){
            int value=rnd.nextInt(6);
            d.get(x).setTag(value+1);
            d.get(x).setImageResource(diceImage[value]);
        }
        rollcount++;
        if (rollcount==3) {
            checkDice();
            Button rollButton=findViewById(R.id.btnRoll);
            rollButton.setEnabled(false);
        }
    }

    public void diceClick(View view) {
        ImageView d=(ImageView) view;
        if (d.getImageAlpha()==255){
            d.setImageAlpha(128);
        }else{
            d.setImageAlpha(255);
        }
    }

    public void checkDice(){
        int[] values={0,0,0,0,0,0};
        for (int x=0;x<dice.size();x++){
            int result=(int)dice.get(x).getTag();
            result--;
            values[result]++;
            Arrays.sort(values);
        }
        Toast.makeText(this,"Ones: "+values[0],Toast.LENGTH_SHORT).show();
    }
}
