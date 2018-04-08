package com.example.anantrai.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //ImageView[] imageViews=new ImageView[9];
    int[] symbol=new int[9];
    int player=1;
    int count=0,winner=0;
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = (TextView) findViewById(R.id.text1);
        if(savedInstanceState==null) {
            symbol=new int[9];
            player = 1;
            count = 0;
            winner = 0;
        }
        else{
            symbol=savedInstanceState.getIntArray("symbol");
            player=savedInstanceState.getInt("player");
            count=savedInstanceState.getInt("count");
            winner=savedInstanceState.getInt("winner");
            resetDisp();
        }
        /*for(int i=0;i<9;i++){
            imageViews[i]=(ImageView)findViewById(getResourceId(String.format("image%d",i+1),"drawable",getPackageName()));
        }*/
    }


    public void onClickbox(View view){
        int resNo=Integer.parseInt(view.getTag().toString());
        Log.i("click",Integer.toString(resNo));
        ImageView imageView=(ImageView)view;
        if(player==1&&symbol[resNo-1]==0&&winner==0) {
            count++;
            imageView.setTranslationX(-1200);
            imageView.setImageResource(R.drawable.cross);
            imageView.animate().translationXBy(1200).setDuration(500);
            symbol[resNo-1]=1;
            player=2;
            winner=checkWinner();
        }
        else if(player==2&&symbol[resNo-1]==0&&winner==0){
            count++;
            imageView.setTranslationY(-1200);
            imageView.setImageResource(R.drawable.zero);
            imageView.animate().translationYBy(1200).setDuration(500);
            //imageView.setAlpha(0);
            //imageView.animate().alpha(1).setDuration(800);
            symbol[resNo-1]=2;
            player=1;
            winner=checkWinner();
        }
        if(count==9||winner!=0){
            if(winner!=0){
                textview.setText(String.format("Winner is player %d",winner));
            }
            else{
                textview.setText("It is a Draw");
            }
            Button button=(Button)findViewById(R.id.button);
            button.setVisibility(View.VISIBLE);
        }
    }


    public int checkWinner(){
        if(symbol[0]==symbol[4]&&symbol[4]==symbol[8]){
            return symbol[0];
        }
        else if(symbol[2]==symbol[4]&&symbol[4]==symbol[6]){
            return symbol[2];
        }
        for(int i=0;i<3;i++){
            if(symbol[i*3+0]==symbol[i*3+1]&&symbol[i*3+1]==symbol[i*3+2]){
                return symbol[i*3+1];
            }
        }
        for(int i=0;i<3;i++){
            if(symbol[i]==symbol[i+3]&&symbol[i+3]==symbol[i+6]){
                return symbol[i+3];
            }
        }
        return 0;
    }

    public void resetDisp(){
        ImageView imageView;
        for(int i=0;i<9;i++){
            imageView = (ImageView) findViewById(getResourceId(String.format("image%d", i + 1), "id", getPackageName()));
                if(symbol[i]==1){
                    imageView.setImageResource(R.drawable.cross);
                }
                else if(symbol[i]==2){
                    imageView.setImageResource(R.drawable.zero);
                }
                else{
                    imageView.setImageResource(android.R.drawable.screen_background_light_transparent);
                }
        }
        if(count==9||winner!=0){
            if(winner!=0){
                textview.setText(String.format("Winner is player %d",winner));
            }
            else{
                textview.setText("It is a Draw");
            }
            Button button=(Button)findViewById(R.id.button);
            button.setVisibility(View.VISIBLE);
        }
        else{
            textview.setText("TicTacToe");
        }
    }

    public void playAgain(View view){
        symbol=new int[9];
        player = 1;
        count = 0;
        winner = 0;
        resetDisp();
        Button button=(Button)findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putIntArray("symbol",symbol);
        savedInstanceState.putInt("player",player);
        savedInstanceState.putInt("count",count);
        savedInstanceState.putInt("winner",winner);
    }
//used to create identifier
   public int getResourceId(String pVariableName, String pResourcename, String pPackageName)
    {
        try {
            return getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
