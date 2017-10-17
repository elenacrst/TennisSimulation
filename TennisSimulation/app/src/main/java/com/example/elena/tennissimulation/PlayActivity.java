package com.example.elena.tennissimulation;

import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity {

    private TextView mPlayerTurnTextView;
    private TextView mComputerTurnTextView;
    private Button mGoButton;
    private TextView mScoreTextView;
    private boolean mIsUserTurn;
    private int mPlayerPoints, mComputerPoints;

    public static final String SCORE_TEXT_KEY = "score";
    public static final String COMPUTER_POINTS_KEY = "computer";
    public static final String PLAYER_POINTS_KEY = "player";
    public static final String IS_USER_TURN_KEY= "turn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        findViews();

        if(savedInstanceState!= null && savedInstanceState.containsKey(SCORE_TEXT_KEY)){
            mScoreTextView.setText(savedInstanceState.getString(SCORE_TEXT_KEY));
            if (mScoreTextView.getText().toString().contains("won")){
                mGoButton.setText(getString(R.string.reset_go_button));
            }
            mPlayerPoints = savedInstanceState.getInt(PLAYER_POINTS_KEY);
            mComputerPoints = savedInstanceState.getInt(COMPUTER_POINTS_KEY);
            mIsUserTurn = savedInstanceState.getBoolean(IS_USER_TURN_KEY);
            if(mIsUserTurn){
                mPlayerTurnTextView.setVisibility(View.VISIBLE);
                mComputerTurnTextView.setVisibility(View.INVISIBLE);
            }else{
                mComputerTurnTextView.setVisibility(View.VISIBLE);
                mPlayerTurnTextView.setVisibility(View.INVISIBLE);
            }
        }else{
            decideFirstRandomly();
            mScoreTextView.setText("Love All\n");
        }

    }

    private void decideFirstRandomly(){
        int randomValue = (int)(Math.random()*10);
        mIsUserTurn = randomValue >= 5;
        if(mIsUserTurn){
            mPlayerTurnTextView.setVisibility(View.VISIBLE);
            mComputerTurnTextView.setVisibility(View.INVISIBLE);
        }else{
            mComputerTurnTextView.setVisibility(View.VISIBLE);
            mPlayerTurnTextView.setVisibility(View.INVISIBLE);
        }
    }

    private void findViews(){
        mPlayerTurnTextView = (TextView) findViewById(R.id.text_view_player_turn);
        mComputerTurnTextView  = (TextView) findViewById(R.id.text_view_computer_turn);
        mGoButton = (Button) findViewById(R.id.button_go);
        mScoreTextView = (TextView) findViewById(R.id.text_view_score);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mScoreTextView.getText().toString().length() != 0){
            outState.putString(SCORE_TEXT_KEY, mScoreTextView.getText().toString());
            outState.putInt(COMPUTER_POINTS_KEY, mComputerPoints);
            outState.putInt(PLAYER_POINTS_KEY, mPlayerPoints);
            outState.putBoolean(IS_USER_TURN_KEY, mIsUserTurn);
        }
    }

    public void onGoClick(View view) {

        int randomValue = (int)(Math.random()*10);
        boolean isPlayerPoint = randomValue >= 5;
        if(isPlayerPoint){
            if(mComputerPoints==4 && mPlayerPoints==3){/*advantage of computer erases*/

                mComputerPoints--;
            }else
                mPlayerPoints++;

        }else{
            if(mComputerPoints==3 && mPlayerPoints==4){/*advantage of player erases*/
                mPlayerPoints--;
            }else
                mComputerPoints++;
        }

        if(mGoButton.getText().toString().contains("Reset")){
            mGoButton.setText(getString(R.string.go_button));
            mScoreTextView.setText("Love All\n");
            decideFirstRandomly();
            mComputerPoints = 0;
            mPlayerPoints= 0;

        }else{
            String result = ScoreUtils.getTextScore(mComputerPoints,mPlayerPoints);
            if(result.contains("won")){
                mComputerPoints = 0;
                mPlayerPoints= 0;
                mGoButton.setText(getString(R.string.reset_go_button));
            }
            mScoreTextView.append(result+"\n");

        }

    }

    public void onShareClick(View view) {
        String mimeType = "text/plain";
        String title = "Share score with ";
        String textToShare = "Tennis Simulation Score:\n"+mScoreTextView.getText().toString();
        ShareCompat.IntentBuilder.from(this)
                .setChooserTitle(title)
                .setType(mimeType)
                .setText(textToShare)
                .startChooser();
    }
}
