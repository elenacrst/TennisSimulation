package com.example.elena.tennissimulation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onInstructionsClick(View view) {
        Context context = view.getContext();
        Class destination = InstructionsActivity.class;
        Intent intent = new Intent(context, destination);
        context.startActivity(intent);
    }

    public void onPlayClick(View view) {
        Context context = view.getContext();
        Class destination = PlayActivity.class;
        Intent intent = new Intent(context, destination);
        context.startActivity(intent);
    }
}
