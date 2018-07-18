package com.overstandapp.connectgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.media.Image;

public class MainActivity extends AppCompatActivity {

    int activePLayer = 0;// 0 is yello and 1 is red
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7},{2,5,8},
            {0,4,8}, {2,4,6}

    };

    boolean gameActive = true;


    public void dropin(View view) {
        ImageView counter = (ImageView) view;//create counter variable as an ImageView object based on the view argument
        counter.setTranslationY(-1500);//set the counter object to be off the screen (off the visible y axis of the screen)

        Log.i("Output:", counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePLayer;

            if (activePLayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePLayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePLayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(1000).setDuration(2000);//drop the couneter obect into the screen with animations


            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    gameActive = false;
                    String winner = "";
                    if (activePLayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    //Toast.makeText(this, winner + "Someone has won !!", Toast.LENGTH_SHORT).show();

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner + " has won!!");
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public  void playAgain(View view) {
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

//        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
//        for(int i = 0; i < gridLayout.getChildCount(); i++) {
//            ImageView childCounter = (ImageView) gridLayout.getChildAt(i);
//            childCounter.setImageDrawable(null);
//        }
        //GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        android.support.v7.widget.GridLayout gridLayout = (android.support.v7.widget.GridLayout)findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }


        // gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};  THIS WILL NOT WORK

        for (int i=0; i<gameState.length; i++) {
            gameState[i] = 2;
        }// reset value of gameState array to default THIS WORKS
        activePLayer = 0;// 0 is yello and 1 is red
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
