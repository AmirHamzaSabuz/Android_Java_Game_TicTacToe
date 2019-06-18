package com.amir_hamza_sabuz.android_java_game_tictactoe2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 0 = yellow, 1 = red
    int activePlayer = 0;
    boolean gameIsActive = true;
    // 2 means unplayed
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    int counter=1;
    public void buClick(View view)
    {
        Button buSelected = (Button) view;
        //buSelected.setBackgroundResource(R.drawable.red);
        int tappedCounter = Integer.parseInt(buSelected.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive)
        {
            gameState[tappedCounter] = activePlayer;
            buSelected.setTranslationY(-1000f);

            if (activePlayer == 0)
            {
                buSelected.setBackgroundResource(R.drawable.yellow);
                activePlayer = 1;
            }

            else
            {
                buSelected.setBackgroundResource(R.drawable.red);
                activePlayer = 0;
            }

            buSelected.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for (int[] winningPosition : winningPositions)
            {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2)
                {
                    // Someone has won!

                    gameIsActive = false;
                    String winner = "Red";

                    if (gameState[winningPosition[0]] == 0)
                    {
                        winner = "Yellow";
                    }

                    TextView winnerMessage =  findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has won!");
                    LinearLayout layout = findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }

                else
                {
                    boolean gameIsOver = true;

                    for (int counterState : gameState)
                    {
                        if (counterState == 2) gameIsOver = false;
                    }


                    if (gameIsOver)
                    {

                        TextView winnerMessage =  findViewById(R.id.winnerMessage);
                        winnerMessage.setText("It's a draw");
                        LinearLayout layout = findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }

                    System.out.println("winningPosition[0]" + " = " + winningPosition[0]);
                    System.out.println("gameState[winningPosition[0]] " + " = " + gameState[winningPosition[0]] );

                    System.out.println("winningPosition[1]" + " = " + winningPosition[1]);
                    System.out.println("gameState[winningPosition[1]] " + " = " + gameState[winningPosition[1]] );

                    System.out.println("winningPosition[2]" + " = " + winningPosition[2]);
                    System.out.println("gameState[winningPosition[2]] " + " = " + gameState[winningPosition[2]] );
                    System.out.println(".........." + counter);
                    System.out.println(" ");
                    counter++;
                }
            }
        }
    }

    public void playAgain(View view)
    {

        gameIsActive = true;
        LinearLayout layout = findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++)
        {
            gameState[i] = 2;
        }

        TableRow tableRow1 =  findViewById(R.id.tableRow1);
        TableRow tableRow2 =  findViewById(R.id.tableRow2);
        TableRow tableRow3 =  findViewById(R.id.tableRow3);

        for (int i = 0; i< tableRow1.getChildCount(); i++)
        {
            (tableRow1.getChildAt(i)).setBackgroundResource(android.R.color.holo_blue_bright);
        }
        for (int i = 0; i< tableRow2.getChildCount(); i++)
        {
            (tableRow2.getChildAt(i)).setBackgroundResource(android.R.color.holo_blue_bright);
        }
        for (int i = 0; i< tableRow3.getChildCount(); i++)
        {
            (tableRow3.getChildAt(i)).setBackgroundResource(android.R.color.holo_blue_bright);
        }
    }
}

