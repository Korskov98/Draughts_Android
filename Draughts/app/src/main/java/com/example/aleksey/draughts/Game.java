package com.example.aleksey.draughts;

import android.app.Activity;
import android.os.Bundle;

public class Game extends Activity{
    Field field;
    DraughtView draughtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        field = new Field();
        draughtView = new DraughtView(this, field);
        setContentView(draughtView);
        draughtView.requestFocus();
    }
}
