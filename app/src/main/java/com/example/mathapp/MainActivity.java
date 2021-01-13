package com.example.mathapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button startBtn;
    private TextView mathTextView;
    private Button button, button1, button2, button3, playAgainBtn;
    private TextView resultTextView, pointsTextView, timeLeftTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;

        timeLeftTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainBtn.setVisibility(View.INVISIBLE);


        new CountDownTimer(31000, 1000) {

            @Override
            public void onTick(long l) {
                timeLeftTextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                playAgainBtn.setVisibility(View.VISIBLE);
                timeLeftTextView.setText("0s");
                resultTextView.setText("Your score" + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            }

        }.start();

    }

    public void generateQuestion() {
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        mathTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        for(int i = 0; i<4; i++){
            if(i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                incorrectAnswer = rand.nextInt(4);

                while(incorrectAnswer == a+b){
                    incorrectAnswer = rand.nextInt(4);
                }

                answers.add(incorrectAnswer);
            }
        }

        button.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }



    public void start(View view) {
        startBtn.setVisibility(View.GONE);

    }


    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            score++;
            resultTextView.setText("Correct");
        } else {
            resultTextView.setText("Wrong");
        }

        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();


        playAgain((findViewById(R.id.playAgainBtn)));







    }







   private void initializeView() {
        startBtn = findViewById(R.id.startBtn);
        mathTextView = findViewById(R.id.mathTextView);
        button = findViewById(R.id.button);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
       resultTextView = findViewById(R.id.resultTextView);
       pointsTextView = findViewById(R.id.pointsTextView);
       timeLeftTextView = findViewById(R.id.timeLeftTextView);
       playAgainBtn = findViewById(R.id.playAgainBtn);

    }


}