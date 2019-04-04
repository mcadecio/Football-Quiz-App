package com.example.android.footballquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekbar;
    private TextView progressTextView;
    private int userScore;
    private int score;
    private int userProgress;



    private CheckBox manCCheckBox;
    private CheckBox manUCheckBox;
    private CheckBox juveCheckBox;
    private CheckBox noneCheckBox;



    public void setManCCheckBox(View v) {
        this.manCCheckBox.toggle();
    }
    public void setManUCheckBox(View v) {
        this.manUCheckBox.toggle();
    }
    public void setJuveCheckBox(View v) {
        this.juveCheckBox.toggle();
    }
    public void setNoneCheckBox(View v) {
        this.noneCheckBox.toggle();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSeekbar(15);
        userScore = 0;
        score = 4;
        manCCheckBox = findViewById(R.id.manC_checkbox);
        manUCheckBox= findViewById(R.id.manu_checkbox);
        juveCheckBox = findViewById(R.id.juve_checkbox);
        noneCheckBox = findViewById(R.id.none_checkbox);
    }

    protected void setSeekbar(int defaultValue) {
        seekbar = (SeekBar) findViewById(R.id.seekbar);
        progressTextView = (TextView) findViewById(R.id.progress);
        seekbar.setMax(30);
        seekbar.setProgress(defaultValue);

        // perform seek bar change listener event used for getting the progress value
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                userProgress = progressChangedValue;
                progressTextView.setText("" + progressChangedValue);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }


    public void questionOne() {
        EditText editText = findViewById(R.id.qone_editText);
        String input = editText.getText().toString();

        if (input.equals("Chelsea")) {
            userScore++;
        }
    }

    public void questionTwo(){

        if (manUCheckBox.isChecked() & !manCCheckBox.isChecked()
                & !juveCheckBox.isChecked() & !noneCheckBox.isChecked()){
            userScore++;
        }
    }

    public void questionThree(){
        RadioButton radioButton = findViewById(R.id.ronaldo_radioButton);
        boolean isRonaldo = radioButton.isChecked();
        if (isRonaldo){
            userScore++;
        }
    }

    public void questionFour(){
        if (userProgress == 22){
            userScore++;
        }
    }
    public void checkAnswers(View v){
        questionOne();
        questionTwo();
        questionThree();
        questionFour();
        evaluateAnswers();
    }
    public void evaluateAnswers(){
        Button answersButton = findViewById(R.id.showAnswers_button);
        TextView textView = findViewById(R.id.total_score_textView);
        if (userScore == score){

            textView.setText("You have successfully completed the quiz");
            answersButton.setVisibility(View.INVISIBLE);
        }
        else{
            textView.setText("You have completed " + userScore + " correctly.");
            answersButton.setVisibility(View.VISIBLE);
        }
        userScore= 0;
    }

    public void showAnswers(View v){
        TextView firstAnswer = findViewById(R.id.firstAnswer_textView);
        firstAnswer.setVisibility(View.VISIBLE);
        TextView secondAnswer = findViewById(R.id.secondAnswer_textView);
        secondAnswer.setVisibility(View.VISIBLE);
        TextView thirdAnswer = findViewById(R.id.thirdAnswer_textView);
        thirdAnswer.setVisibility(View.VISIBLE);
        TextView fourthAnswer = findViewById(R.id.fourthAnswer_textView);
        fourthAnswer.setVisibility(View.VISIBLE);
    }

    public void reset(View v){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }



}

