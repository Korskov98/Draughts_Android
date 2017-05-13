package com.example.aleksey.draughts;

import android.media.MediaPlayer;
import android.content.Context;

public class Music {
    private static MediaPlayer mp = null;

    public static void play(Context context, int resource) {
        stop(context);
        mp = MediaPlayer.create(context, resource);
        mp.setLooping(true);
        mp.start();
    }

    public static void stop(Context context) {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}