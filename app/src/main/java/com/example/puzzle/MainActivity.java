package com.example.puzzle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.puzzle.Model.No;
import com.example.puzzle.Model.Num;
import com.example.puzzle.UI.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;



public class MainActivity extends AppCompatActivity {


    //stores a sequence of strings for the solution. Ex: "up", "down", "left", "right"
    //By default the first position is null.
   /* public int [][] solution = new int [][] {{1,2,3},{4,5,6},{7,8,0}};
    public ArrayList<String> wayToSolve = new ArrayList<>();
    No initialNode = new No();
    int [][] initialStateHere = GameBoard.initialState;
    Num[] arrayNumbers = new Num [9];
    int stepsForSoluction =0;*/

    GameView.GameViewActionListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createListener();
        drawBoard();

        //quando clicar em resolver chamo essa funcao
        //initialNode.setState(initialStateHere);
        //printState(initialNode.getState());

        //pegar a matriz iniciar e substituir no initialState
        /*for(int i = 0; i < initialStateHere.length; i++){
            for (int j = 0; j< initialStateHere.length;j++){
                System.out.println("aqui " + "linha "+ i + " coluna"+  j + " " + initialStateHere[i][j]);
            }
        }*/


        // makeCoordinates(initialState, arrayNumbers);
        // findSoluction(initialNode, solution, arrayNumbers);

    }

    // Instanciar a tela
    private void createListener() {
        listener = new GameView.GameViewActionListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void showMessage(String message) {
                setMessage(message);
            }
        };
    }

    private void drawBoard() {
        ViewGroup view = (ViewGroup) findViewById(R.id.game);


        View gameView = new GameView(this, null, listener);
        view.addView(gameView);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setMessage(String message) {
        if (Objects.equals(message, "create_new_game")) {
            newGame(null);
            return;
        }
        TextView view = (TextView) findViewById(R.id.moves);
        view.setText(message);
    }

    public void newGame(View view) {

        if (view != null) {

            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:
                            //Yes button clicked
                            ViewGroup n_view = (ViewGroup) findViewById(R.id.game);


                            View gameView = new GameView(MainActivity.this, null, listener);
                            n_view.addView(gameView);
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            //No button clicked
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Você tem certeza que quer iniciar um novo jogo? Você perderá o atual").setPositiveButton("Sim", dialogClickListener)
                    .setNegativeButton("Não", dialogClickListener).show();

        } else {
            ViewGroup n_view = (ViewGroup) findViewById(R.id.game);


            View gameView = new GameView(this, null, listener);
            n_view.addView(gameView);
        }
    }


    //Fim instancia tela


}
