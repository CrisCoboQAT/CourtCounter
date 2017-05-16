package com.example.android.courtcounter;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scoreTeamA = 0;
    int scoreTeamB = 0;
    int faultTeamA = 0;
    int faultTeamB = 0;
    int seconds = 24;
    int minutes = 9;
    int periodSeconds = 59;

    //runs without a timer by reposting this handler at the end of the runnable
    final  Handler timerHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Controls the move.
     */
    public void controlMove(View v) {
        timerHandler.removeCallbacks(timerMoveRunnable);
        seconds = 25;
        timerMoveRunnable.run();
    }

    /**
     * Controls the period.
     */
    public void controlPeriod(View v) {
        // Reset move when the period is restarted
        controlMove(v);
        // Restart period
        timerHandler.removeCallbacks(timerPeriodRunnable);
        minutes = 10;
        periodSeconds = 00;
        timerPeriodRunnable.run();
    }

    /**
     * Displays the given move.
     */
    public void displayMove(int seconds) {
        Button moveButton = (Button) findViewById(R.id.move);
        moveButton.setText(String.format("%s:%02d", "00", seconds));
    }

    /**
     * Displays the given .period
     */
    public void displayPeriod(int minutes, int seconds) {
        Button periodButton = (Button) findViewById(R.id.period);
        periodButton.setText(String.format("%02d:%02d", minutes, seconds));
    }

    /**
     * Increase 3 to the score for Team A.
     */
    public void addThreeForTeamA(View v){
        displayForTeamA(scoreTeamA += 3);
    }

    /**
     * Increase 2 to the score for Team A.
     */
    public void addTwoForTeamA(View v){
        displayForTeamA(scoreTeamA += 2);
    }

    /**
     * Increase 1 to the score for Team A.
     */
    public void addOneForTeamA(View v){
        displayForTeamA(scoreTeamA += 1);
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Increase 1 to the fault for Team A.
     */
    public void addOneFaultForTeamA(View v){
        displayFaultForTeamA(faultTeamA += 1);
    }

    /**
     * Displays the given fault for Team A.
     */
    public void displayFaultForTeamA(int fault) {
        Button faultButton = (Button) findViewById(R.id.team_a_faults);
        faultButton.setText(String.valueOf(fault));
    }

    /**
     * Increase 3 to the score for Team B.
     */
    public void addThreeForTeamB(View v){
        displayForTeamB(scoreTeamB += 3);
    }

    /**
     * Increase 2 to the score for Team B.
     */
    public void addTwoForTeamB(View v){
        displayForTeamB(scoreTeamB += 2);
    }

    /**
     * Increase 1 to the score for Team B.
     */
    public void addOneForTeamB(View v){
        displayForTeamB(scoreTeamB += 1);
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Increase 1 to the fault for Team B.
     */
    public void addOneFaultForTeamB(View v){
        displayFaultForTeamB(faultTeamB += 1);
    }

    /**
     * Displays the given fault for Team B.
     */
    public void displayFaultForTeamB(int fault) {
        Button faultButton = (Button) findViewById(R.id.team_b_faults);
        faultButton.setText(String.valueOf(fault));
    }

    public void resetScores(View v){
        displayForTeamA(scoreTeamA = 0);
        displayForTeamB(scoreTeamB = 0);
        displayFaultForTeamA(faultTeamA = 0);
        displayFaultForTeamB(faultTeamB = 0);
        timerHandler.removeCallbacks(timerMoveRunnable);
        timerHandler.removeCallbacks(timerPeriodRunnable);
        displayMove(24);
        displayPeriod(10,00);
    }

    Runnable timerMoveRunnable = new Runnable() {

        @Override
        public void run() {
            if(seconds > 0) {
                displayMove(seconds-=1);
                timerHandler.postDelayed(this, 1000);
            }

        }
    };

    Runnable timerPeriodRunnable = new Runnable() {

        @Override
        public void run() {
            if(minutes >= 0 && periodSeconds >= 0) {
                displayPeriod(minutes, periodSeconds);
                periodSeconds -= 1;
                if(periodSeconds == -1) {
                    minutes -= 1;
                    periodSeconds = 59;
                }
                timerHandler.postDelayed(this, 1000);
            }
        }
    };

}