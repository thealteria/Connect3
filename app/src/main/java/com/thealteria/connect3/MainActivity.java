package com.thealteria.connect3;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0 = yellow, 1 = red

    int activePlay = 0;

    boolean gameAct = true;

    //2 means unplayed

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winPos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedCount =  Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCount] == 2 && gameAct) {

            gameState[tappedCount] = activePlay;

            counter.setTranslationY(-1000f);

            if (activePlay == 0) {
                counter.setImageResource(R.drawable.yellow);

                activePlay = 1;
            } else {
                counter.setImageResource(R.drawable.red);

                activePlay = 0;
            }


            counter.animate().translationYBy(1000f).setDuration(300);

            for(int[] winPos : winPos) {
                if (gameState[winPos[0]] == gameState[winPos[1]] &&
                        gameState[winPos[1]] == gameState[winPos[2]] &&
                        gameState[winPos[0]] != 2) {

                    gameAct = false;

                    String winner = "Red";

                    if (gameState[winPos[0]] == 0) {

                        winner = "Yellow";
                    }

                    TextView wm = (TextView) findViewById(R.id.winnermsg);
                    wm.setText( winner + " has won!");


                    LinearLayout ll = (LinearLayout) findViewById(R.id.playAL);

                    GridLayout gl = (GridLayout) findViewById(R.id.connectGrid);

                    gl.animate().alpha(0f).setDuration(1000);
                    ll.setVisibility(View.VISIBLE);

                }

                else {

                    boolean gameOver = true;

                    for (int draw : gameState) {
                        if (draw == 2) {
                            gameOver = false;
                        }
                    }

                        if(gameOver) {

                            TextView wm = (TextView) findViewById(R.id.winnermsg);
                            wm.setText("It's a draw");


                            LinearLayout ll = (LinearLayout) findViewById(R.id.playAL);

                            GridLayout gl = (GridLayout) findViewById(R.id.connectGrid);

                            gl.animate().alpha(0f).setDuration(1000);
                            ll.setVisibility(View.VISIBLE);
                        }
                    }
                }


            }

    }

    public void playAgain(View view) {

        gameAct = true;

        LinearLayout ll = (LinearLayout) findViewById(R.id.playAL);

        GridLayout gl = (GridLayout) findViewById(R.id.connectGrid);
        gl.animate().alpha(1f).setDuration(1000);

        ll.setVisibility(View.INVISIBLE);

        activePlay = 0;

        for(int i = 0; i < gameState.length; i++) {

            gameState[i] = 2;
        }

        for (int i = 0; i<gl.getChildCount(); i++) {

            ((ImageView) gl.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
