package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void clicked(View view) {
        EditText et= findViewById(R.id.playnum);
        if(et.getText().toString().matches("") || Integer.parseInt(et.getText().toString())>8){

            et.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
            Toast toast= Toast.makeText(getApplicationContext(),"Enter a number between 1 and 8",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        Intent intent= new Intent(this,Game.class);
    try{
        intent.putExtra("players",et.getText().toString());
    }
    catch (Exception e){
    e.printStackTrace();
    }
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
    @Override
    public void onBackPressed() {
        //No codes at all
    }
}