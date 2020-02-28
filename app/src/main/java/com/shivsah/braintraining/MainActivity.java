package com.shivsah.braintraining;

//import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random rand = new Random();
    boolean isActive = false, isTimerOn =false, isGameOn = false;
    CountDownTimer countDownTimer;
    int correctAns,totalQuestion = 1,correctQuestion =0;
    TextView leftNum,rightNum,operator,result,timeLeft,score;
    Button ans1 ,ans2,ans3,ans4,start;

    public void start(View view){
        if(!isActive) {
            isActive = true;
            isGameOn = true;
            start.setText(R.string.stop);
            result.setText(R.string.message);
            correctAns = generateQuestion();
        }else{

            endGame();
        }
    }

    public void submit(View view){
        if(isGameOn) {
            totalQuestion++;
            Button ansButton = (Button) view;
            int subAns = Integer.parseInt(ansButton.getText().toString());
            if (subAns != correctAns) {
                result.setText(R.string.wrong);

            } else {
                correctQuestion++;
                result.setText(R.string.correct);
            }
            correctAns = generateQuestion();
        }
    }

    @SuppressLint("SetTextI18n")
    private int generateQuestion(){
        score.setText(correctQuestion + " / " + totalQuestion);
        //timeLeft.setText("20");
        if(isTimerOn){
            countDownTimer.cancel();
            isTimerOn = false;
        }
        countDown();
        int leftNum =0,rightNum=0,operator,answer =0;
        String op ="";
        operator = rand.nextInt(3);

        switch(operator){
            case 0:
                op = "+";
                leftNum = rand.nextInt(1000)+1;
                rightNum = rand.nextInt(1000)+1;
                answer = leftNum + rightNum;
                break;
            case 1:
                op = "-";
                leftNum = rand.nextInt(1000)+1;
                rightNum = rand.nextInt(1000)+1;
                answer = leftNum - rightNum;
                break;
            case 2:
                op = "*";
                leftNum = rand.nextInt(20)+1;
                rightNum = rand.nextInt(10)+1;
                answer = leftNum * rightNum;
                break;

        }
        this.leftNum.setText(Integer.toString(leftNum));
        this.rightNum.setText(Integer.toString(rightNum));
        this.operator.setText(op);


        //answer generator

        int currOption = rand.nextInt(4);
        int error = rand.nextInt(17)+3;
        switch (currOption){
            case 0:
                ans1.setText(Integer.toString(answer));
                ans2.setText(Integer.toString(answer+error));
                ans3.setText(Integer.toString(answer-error));
                ans4.setText(Integer.toString(answer+error*2));
                break;
            case 1:
                ans1.setText(Integer.toString(answer+error));
                ans2.setText(Integer.toString(answer));
                ans3.setText(Integer.toString(answer*error));
                ans4.setText(Integer.toString(answer-error));
                break;
            case 2:
                ans1.setText(Integer.toString(answer*error));
                ans2.setText(Integer.toString(answer-error));
                ans3.setText(Integer.toString(answer));
                ans4.setText(Integer.toString(answer+error));
                break;
            case 3:
                ans1.setText(Integer.toString(answer-error));
                ans2.setText(Integer.toString(answer*error));
                ans3.setText(Integer.toString(answer+error));
                ans4.setText(Integer.toString(answer));
                break;
        }

        return answer;
    }
    private void countDown() {
        if (!isTimerOn) {
            isTimerOn = true;
            countDownTimer = new CountDownTimer(21000, 1000 + 100) {
                @SuppressLint("SetTextI18n")
                @Override
                public void onTick(long millisUntilFinished) {
                    timeLeft.setText(Integer.toString((int) millisUntilFinished / 1000));
                }

                @Override
                public void onFinish() {
                    endGame();

                }
            }.start();
        }
    }

    @SuppressLint("SetTextI18n")
    private void endGame(){
        result.setText("Congrats! you got " + correctQuestion + " answer correct out of "+(totalQuestion)+" Questions");
        correctQuestion =0;
        totalQuestion =0;
        countDownTimer.cancel();
        isActive = false;
        isGameOn =false;
        score.setText("00 / 00");
        start.setText(R.string.start);
        ans1.setText(R.string.initialAns);
        ans2.setText(R.string.initialAns);
        ans3.setText(R.string.initialAns);
        ans4.setText(R.string.initialAns);
        timeLeft.setText(R.string.initialAns);
        rightNum.setText(R.string.initialAns);
        leftNum.setText(R.string.initialAns);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leftNum = findViewById(R.id.leftNum);
        rightNum = findViewById(R.id.rightNum);
        operator = findViewById(R.id.operator);
        result = findViewById(R.id.result);
        timeLeft = findViewById(R.id.timeLeft);
        score = findViewById(R.id.score);
        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        ans3 = findViewById(R.id.ans3);
        ans4 = findViewById(R.id.ans4);
        start = findViewById(R.id.start);
    }






















/*
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isGameOn",isGameOn);
        outState.putBoolean("isTimerOn",isTimerOn);
        outState.putBoolean("isActive",isActive);
        outState.putInt("correctAns",correctAns);
        outState.putInt("correctQuestion",correctQuestion);
        outState.putInt("totalQuestion",totalQuestion);
        outState.putString("score",score.getText().toString());
        outState.putString("ans1",ans1.getText().toString());
        outState.putString("ans2",ans2.getText().toString());
        outState.putString("ans3",ans3.getText().toString());
        outState.putString("ans4",ans4.getText().toString());
        outState.putString("start",start.getText().toString());
        outState.putString("result",result.getText().toString());
        outState.putString("leftNum",leftNum.getText().toString());
        outState.putString("rightNum",rightNum.getText().toString());
        outState.putString("operator",operator.getText().toString());
        outState.putString("timeLeft",timeLeft.getText().toString());


    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        isGameOn = savedInstanceState.getBoolean("isGameOn");
        isTimerOn = savedInstanceState.getBoolean("isTimerOn");
        isActive = savedInstanceState.getBoolean("isActive");
        correctAns =savedInstanceState.getInt("correctAns");
        totalQuestion =savedInstanceState.getInt("totalQuestion");
        correctQuestion =savedInstanceState.getInt("correctQuestion");
        score.setText(savedInstanceState.getString("score"));
        ans1.setText(savedInstanceState.getString("ans1"));
        ans2.setText(savedInstanceState.getString("ans2"));
        ans3.setText(savedInstanceState.getString("ans3"));
        ans4.setText(savedInstanceState.getString("ans4"));
        start.setText(savedInstanceState.getString("start"));
        result.setText(savedInstanceState.getString("result"));
        leftNum.setText(savedInstanceState.getString("leftNum"));
        rightNum.setText(savedInstanceState.getString("rightNum"));
        operator.setText(savedInstanceState.getString("operator"));
        timeLeft.setText(savedInstanceState.getString("timeLeft"));


    }

 */
}
