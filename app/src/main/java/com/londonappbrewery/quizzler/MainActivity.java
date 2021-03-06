package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // TODO: Declare constants here


    // TODO: Declare member variables here:
    Button trueButton;
    Button falseButton;
    TextView questionText;
    TextView scoreText;
    int curQuestion;
    ProgressBar mProgressBar;
    int score;
    double scorePct = 0;
    int idx = 0;
    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13, true)
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            score = savedInstanceState.getInt("ScoreKey");
            idx = savedInstanceState.getInt("questionIndex");

        } else {
            score = 0;
            idx = 0;
        }
        setContentView(R.layout.activity_main);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        questionText = findViewById(R.id.question_text_view);
        curQuestion = mQuestionBank[idx].getQuestionID();
        questionText.setText(curQuestion);
        mProgressBar = findViewById(R.id.progress_bar);
        scoreText = findViewById(R.id.score);
        scoreText.setText("Score " + score + "/" + mQuestionBank.length);


        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                nextQuestion();

            }
        });


        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer(false);
                nextQuestion();
            }
        });

    }

    private void nextQuestion() {

        idx = (idx + 1) % mQuestionBank.length;
        if(idx == 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game over");
            alert.setCancelable(false);
            alert.setMessage("You have gotten " + score + " out of " + mQuestionBank.length + " questions correct");

            alert.setPositiveButton("Close app", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }
        curQuestion = mQuestionBank[idx].getQuestionID();
        questionText.setText(curQuestion);
    }

    private void checkAnswer(boolean input) {

        boolean correct = mQuestionBank[idx].isAnswer();
        if (input == correct) {
            Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT).show();
            score++;
            scoreText.setText("Score " + score + "/" + mQuestionBank.length);
            scorePct = ((double) score) / ((double) mQuestionBank.length);
            mProgressBar.setProgress((int) ((scorePct) * 100));
        } else {
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
    }


    public void onSaveInstanceState(Bundle outstate) {
        super.onSaveInstanceState(outstate);
        outstate.putInt("ScoreKey",score);
        outstate.putInt("questionIndex",idx);
    }

}
