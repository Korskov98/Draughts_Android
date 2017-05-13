package com.example.aleksey.draughts;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Game extends Activity {
    Field field;
    DraughtView draughtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        field = new Field();
        draughtView = new DraughtView(this, field, this);
        setContentView(draughtView);
        draughtView.requestFocus();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Music.play(this, R.raw.game);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Music.stop(this);
    }

    public void wrong_move(){
        Toast error = Toast.makeText(getApplicationContext(), R.string.wrong_move_text, Toast.LENGTH_LONG);
        error.show();
    }

    public void empty_cell (){
        Toast error = Toast.makeText(getApplicationContext(), R.string.empty_cell_text, Toast.LENGTH_LONG);
        error.show();
    }

    public void wrong_color(){
        Toast error = Toast.makeText(getApplicationContext(), R.string.wrong_color_text, Toast.LENGTH_LONG);
        error.show();
    }
}
