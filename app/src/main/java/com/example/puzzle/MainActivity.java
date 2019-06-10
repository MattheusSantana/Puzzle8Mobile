package com.example.puzzle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    GameView.GameViewActionListener listener;
    Button btnSolve;
    View gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSolve = (Button) findViewById(R.id.button_solve);

        createListener();
        drawBoard();


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


         gameView = new GameView(this, null, listener);

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

    public void solveGame(View view){
        View game = (GameView) gameView;
        ((GameView) game).getSolve();

        for (int i = 0; i < ((GameView) game).getStepsForSoluction(); i++) {
            int vetor [] = new int[10000];
            ((GameView) game).solve();



            /*try {
                Thread.sleep(100);
                System.out.println("entrou sleep");
            } catch (InterruptedException ex) {
            }
            */
        }

    }


    //Fim instancia tela


}
