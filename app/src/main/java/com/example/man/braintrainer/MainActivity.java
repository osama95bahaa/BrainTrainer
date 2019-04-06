package com.example.man.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    Button playAgain;
    TextView timer;
    TextView finalScore;
    TextView score;
    TextView correctOrWrong;
    TextView equations;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    int buttonPressedCount = 0;
    int hits =0;
    Random random;
    int rand1;
    int rand2;
    int choiceRand1;
    int choiceRand2;
    int choiceRand3;
    int answer;
    ArrayList<Integer> holdNumbers = new ArrayList<>();


    public void getRandom(){

        random = new Random();
        rand1 = random.nextInt(51);
        rand2 = random.nextInt(51);
        choiceRand1 = random.nextInt(100);
        choiceRand2 = random.nextInt(100);
        choiceRand3 = random.nextInt(100);
        answer = rand1 + rand2;
        holdNumbers = new ArrayList<Integer>();

        holdNumbers.add(choiceRand1);
        holdNumbers.add(choiceRand2);
        holdNumbers.add(choiceRand3);
        holdNumbers.add(answer);
        Collections.shuffle(holdNumbers);


        button0.setText(holdNumbers.get(0) + "");
        button0.setTag(holdNumbers.get(0));
        button1.setText(holdNumbers.get(1) + "");
        button1.setTag(holdNumbers.get(1));
        button2.setText(holdNumbers.get(2)+"");
        button2.setTag(holdNumbers.get(2));
        button3.setText(holdNumbers.get(3)+"");
        button3.setTag(holdNumbers.get(3));

        equations.setText(rand1 + " + " + rand2);

    }

    public void startButton(View view){

        getRandom();

        startButton = (Button) findViewById(R.id.startButtonID);
        startButton.setVisibility(View.INVISIBLE);
        hits =0;
        buttonPressedCount = 0;
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
//        correctOrWrong.setVisibility(View.VISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        finalScore.setVisibility(View.INVISIBLE);

        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        score.setText(hits + "/" + buttonPressedCount);


        new CountDownTimer(20000+100,1000){
            public void onTick(long millisecondRemaining){
                timer.setText(""+ millisecondRemaining/1000 + "S");
            }
            public void onFinish(){
                finalScore.setVisibility(View.VISIBLE);
                finalScore.setText("Final Score : " + hits+ "/" + buttonPressedCount);
                playAgain.setVisibility(View.VISIBLE);
                timer.setText("0S");
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                correctOrWrong.setVisibility(View.INVISIBLE);
            }
        }.start();
    }

    public void buttonPressed(View view){

        Log.i("Pressed Button" , "" + view.getTag());
        buttonPressedCount++;
        correctOrWrong.setVisibility(View.VISIBLE);
        if(view.getTag().equals(answer)){
            correctOrWrong.setText("Correct!");
            hits++;
            score.setText(""+hits + "/" + ""+ buttonPressedCount);
        }
        else{
            correctOrWrong.setText("Wrong!");
            score.setText(""+hits + "/" + ""+ buttonPressedCount);
        }

        getRandom();
    }


    public void playAgain(View view){
        startButton(view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = (TextView) findViewById(R.id.timer);
        playAgain = (Button) findViewById(R.id.playAgainButton);
        playAgain.setVisibility(View.INVISIBLE);
        finalScore = (TextView) findViewById(R.id.finalScore);
        score = (TextView) findViewById(R.id.score);
        correctOrWrong = (TextView) findViewById(R.id.correctOrWrong);
        equations = (TextView) findViewById(R.id.equations);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
    }

}
