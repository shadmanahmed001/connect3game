package info.shadmanahmed.connect3app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0; // 0 is red and 1 is yellow

    int gameState[] = {2,2,2,2,2,2,2,2,2,2};
//                     0,1,2,3,4,5,6,7,8,9

    boolean isGameActive = true;

    int[][] winningPostions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void playAgain(View view){
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.popuplayout);
        linearLayout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        for(int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }
        GridLayout gameBoard = (GridLayout) findViewById(R.id.gameBoard);
        for (int i = 0; i < gameBoard.getChildCount(); i++){
            ImageView counterToFlush = (ImageView) gameBoard.getChildAt(i);
            counterToFlush.setImageResource(0);
        }
        isGameActive = true;

    }

    public void dropIn(View view){
        ImageView theCounter = (ImageView) view;

        int tappedCounter = Integer.parseInt(theCounter.getTag().toString());

        if (gameState[tappedCounter] == 2 && isGameActive) {

            theCounter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                theCounter.setImageResource(R.drawable.red);
                gameState[tappedCounter] = activePlayer;
                activePlayer = 1;
            } else {
                theCounter.setImageResource(R.drawable.yellow);
                gameState[tappedCounter] = activePlayer;
                activePlayer = 0;
            }

            theCounter.animate().translationYBy(1000f).setDuration(300);

        }

        for (int[] winningPostion : winningPostions){
            if (gameState[winningPostion[0]] == gameState[winningPostion[1]] && gameState[winningPostion[2]] == gameState[winningPostion[0]] && gameState[winningPostion[0]] != 2){
                // person won
                isGameActive = false;
                String winner = "Red";
                if (gameState[winningPostion[0]] == 1){
                    winner = "Yellow";
                }
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.popuplayout);
                TextView winningMsg = (TextView) findViewById(R.id.winningMsg);
                winningMsg.setText(winner + " has won!");
                linearLayout.setVisibility(View.VISIBLE);
            }
            else {
                boolean isGameOver = true;
                for (int element: gameState){
                    if (element == 2){
                        isGameOver = false;
                    }
                    if (isGameOver){
                        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.popuplayout);
                        TextView winningMsg = (TextView) findViewById(R.id.winningMsg);
                        winningMsg.setText("It's a draw!");
                        linearLayout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }
}
