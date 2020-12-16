package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Game extends AppCompatActivity {

    public static int num = 1;
    public static int pl;
    public static ArrayList<String> names = new ArrayList<String>();
    public static ArrayList<Integer> score = new ArrayList<Integer>();
    int[] sectors = {5,4,3,2,1,10,9,8,7,6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageView wimg;
        MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.music);
        if (num == 1) {
            String p = getIntent().getStringExtra("players");
            pl = Integer.parseInt(p);
        }
        num++;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Button btn = findViewById(R.id.btn);
        TextView tv = findViewById(R.id.disp);
        wimg = findViewById(R.id.wheel);
        //Set an onClickListener to detect clicks.
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rr = new Random();
                int degree = rr.nextInt(360);
                RotateAnimation rotateAnimation = new RotateAnimation(0, degree + 360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setDuration(3000);
                rotateAnimation.setFillAfter(true);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());
                rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        mPlayer.start();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mPlayer.stop();

                        calculatePoint(degree%360);
                         Toast toast= Toast.makeText(getApplicationContext(),"Your score is "+score.get(num-2)+"",Toast.LENGTH_SHORT);
                        toast.show();

                        // Log.d("HII",score.get(0).toString());
                        EditText et = findViewById(R.id.playername);

                        try {
                         et = findViewById(R.id.playername);
                            names.add(et.getText().toString());
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                        if (num <= pl) {
                            Intent intent = new Intent(Game.this, Game.class);
                            startActivity(intent);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        } else {
                            Log.d("Scoreeee", score.toString());
                            num=1;

                            Intent intent2 = new Intent(Game.this, Res.class);
                            intent2.putExtra("nameslist",  names);
                            intent2.putExtra("ScoreList",score);
                            names=new ArrayList<String>();
                            score=new ArrayList<Integer>();
                            startActivity(intent2);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        mPlayer.start();
                    }
                });
                EditText et = findViewById(R.id.playername);
                if(et.getText().toString().matches("")) {
                    et.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
                    Toast toast = Toast.makeText(getApplicationContext(), "Player name must not be empty!", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                else {
                    wimg.startAnimation(rotateAnimation);
                }
            }
        });


    }

    public void calculatePoint(int degree) {
        Log.d("ddd",String.valueOf(degree));
        int initialPoint = 0;
        int endPoint = 36;
        int i = 0;
        int res = -1;
        do {
            if (degree >= initialPoint && degree <= endPoint) {
                res = sectors[i];
                score.add(res);
            }
            initialPoint += 36;
            endPoint += 36;
            i++;
            Log.d("i=",String.valueOf(i));
            Log.d("res", String.valueOf(res));
        } while (res == -1);
    }

    @Override
    public void onBackPressed() {
        //No codes at all
    }
}