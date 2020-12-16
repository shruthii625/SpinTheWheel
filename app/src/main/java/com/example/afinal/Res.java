package com.example.afinal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Res extends AppCompatActivity {
    RecyclerView rv;
    RecyclerView.Adapter programAdapter;
    RecyclerView.LayoutManager layoutManager;
    int[] images={R.drawable.user1,R.drawable.user2,R.drawable.user3,R.drawable.user4, R.drawable.user5, R.drawable.user6,R.drawable.user7,R.drawable.user8};

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);
        rv=findViewById(R.id.list);
        rv.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        ArrayList<String> namel = getIntent().getStringArrayListExtra("nameslist");
        ArrayList<Integer> scorel = getIntent().getIntegerArrayListExtra("ScoreList");
        Integer[] scorear= scorel.toArray(new Integer[scorel.size()]);
        
        String[] namear= namel.toArray(new String[namel.size()]);
        for(int i =0;i<scorear.length-1;i++){
            for(int j=0;j<scorear.length-i-1;j++){
                if(scorear[j]<scorear[j+1]){
                    Integer t=scorear[j];
                    scorear[j]=scorear[j+1];
                    scorear[j+1]=t;
                    String te=namear[j];
                    namear[j]=namear[j+1];
                    namear[j+1]=te;
                }
            }
        }
        programAdapter=new ProgramAdapter(this, namear,scorear,images);
        rv.setAdapter(programAdapter);
    }
    public void playAgain(View view){
        Intent intent=new Intent(Res.this, MainActivity.class);
        startActivity(intent);
    }

    public void exitGame(View view){
        Toast toast = Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }
    @Override
    public void onBackPressed() {
        //No codes at all
    }
}