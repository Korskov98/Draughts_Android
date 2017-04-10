package com.example.aleksey.draughts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Draught extends Activity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        View newButton = findViewById(R.id.new_button);
        newButton.setOnClickListener(this);
        View loadButton = findViewById(R.id.load_button);
        loadButton.setOnClickListener(this);
        View aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(this);
        View exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_button:
                startGame();
                break;
            case R.id.about_button:
                Intent i = new Intent(this, About.class);
                startActivity(i);
                break;
            case R.id.exit_button:
                finish();
                break;

        }
    }

    private void startGame(){
        Intent intent = new Intent(Draught.this, Game.class);
        startActivity(intent);
    }
}
