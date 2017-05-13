package com.example.aleksey.draughts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

public class DraughtView extends View {
    Game game;
    private static final int cell_size = 130;
    Field field;
    private boolean click = false;
    private int x_first_click = -10;
    private int y_first_click = -10;
    private int x_second_click;
    private int y_second_click;
    private int fontSize = 50;

    public DraughtView(Context context, Field field, Game game) {
        super(context);
        this.field = field;
        this.game = game;
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint_backgroung = new Paint();
        paint_backgroung.setColor(getResources().getColor(R.color.background_field));
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint_backgroung);
        Paint paint_white_cell = new Paint();
        Paint paint_black_cell = new Paint();
        paint_white_cell.setColor(getResources().getColor(R.color.white_cell));
        paint_black_cell.setColor(getResources().getColor(R.color.black_cell));
        for (int i = 0; i <= 7; i = i + 2){
            canvas.drawRect(i * cell_size, 0, i * cell_size + cell_size, cell_size, paint_white_cell);
            canvas.drawRect((i + 1) * cell_size, 0, (i + 1) * cell_size + cell_size, cell_size, paint_black_cell);
            canvas.drawRect((i + 1) * cell_size, cell_size, (i + 1) * cell_size + cell_size, 2 * cell_size, paint_white_cell);
            canvas.drawRect(i * cell_size, cell_size, i * cell_size + cell_size, 2 * cell_size, paint_black_cell);
            canvas.drawRect(i * cell_size, 2 * cell_size, i * cell_size + cell_size, 3 * cell_size, paint_white_cell);
            canvas.drawRect((i + 1) * cell_size, 2 * cell_size, (i + 1) * cell_size + cell_size, 3 * cell_size, paint_black_cell);
            canvas.drawRect((i + 1) * cell_size, 3 * cell_size, (i + 1) * cell_size + cell_size, 4 * cell_size, paint_white_cell);
            canvas.drawRect(i * cell_size, 3 * cell_size, i * cell_size + cell_size, 4 * cell_size, paint_black_cell);
            canvas.drawRect(i * cell_size, 4 * cell_size, i * cell_size + cell_size, 5 * cell_size, paint_white_cell);
            canvas.drawRect((i + 1) * cell_size, 4 * cell_size, (i + 1) * cell_size + cell_size, 5 * cell_size, paint_black_cell);
            canvas.drawRect((i + 1) * cell_size, 5 * cell_size, (i + 1) * cell_size + cell_size, 6 * cell_size, paint_white_cell);
            canvas.drawRect(i * cell_size, 5 * cell_size, i * cell_size + cell_size, 6 * cell_size, paint_black_cell);
            canvas.drawRect(i * cell_size, 6 * cell_size, i * cell_size + cell_size, 7 * cell_size, paint_white_cell);
            canvas.drawRect((i + 1) * cell_size, 6 * cell_size, (i + 1) * cell_size + cell_size, 7 * cell_size, paint_black_cell);
            canvas.drawRect((i + 1) * cell_size, 7 * cell_size, (i + 1) * cell_size + cell_size, 8 * cell_size, paint_white_cell);
            canvas.drawRect(i * cell_size, 7 * cell_size, i * cell_size + cell_size, 8 * cell_size, paint_black_cell);
        }
        Paint paint_white_draught = new Paint();
        Paint paint_black_draught = new Paint();
        Paint paint_super_draught = new Paint();
        paint_white_draught.setColor(getResources().getColor(R.color.white_draught));
        paint_black_draught.setColor(getResources().getColor(R.color.black_draught));
        paint_super_draught.setColor(getResources().getColor(R.color.super_draught));
        Path polygon = new Path();
        for (int i = 0; i <= 7; ++i){
            for (int j = 0; j <= 7; ++j){
                Draughts draught = field.get_draught(i,j);
                if ((!field.check_free(i,j)) && (draught.get_color())) {
                    canvas.drawCircle(j * cell_size + cell_size / 2, i * cell_size + cell_size / 2, cell_size / 2, paint_white_draught);
                }
                if ((!field.check_free(i,j)) && (!draught.get_color())) {
                    canvas.drawCircle(j * cell_size + cell_size / 2, i * cell_size + cell_size / 2, cell_size / 2, paint_black_draught);
                }
                if ((!field.check_free(i,j)) && (draught.get_type())) {
                    polygon.moveTo(j * cell_size + cell_size / 2 - 45, i * cell_size + cell_size / 2 + 40);
                    polygon.lineTo(j * cell_size + cell_size / 2 - 45, i * cell_size + cell_size / 2 - 40);
                    polygon.lineTo(j * cell_size + cell_size / 2 - 23, i * cell_size + cell_size / 2);
                    polygon.lineTo(j * cell_size + cell_size / 2, i * cell_size + cell_size / 2 - 40);
                    polygon.lineTo(j * cell_size + cell_size / 2 + 23, i * cell_size + cell_size / 2);
                    polygon.lineTo(j * cell_size + cell_size / 2 + 45, i * cell_size + cell_size / 2 - 40);
                    polygon.lineTo(j * cell_size + cell_size / 2 + 45, i * cell_size + cell_size / 2 + 40);
                    canvas.drawPath(polygon, paint_super_draught);
                }
            }
        }
        Paint paint_select_draught = new Paint();
        paint_select_draught.setColor(getResources().getColor(R.color.select_draught));
        canvas.drawCircle(x_first_click * cell_size + cell_size / 2, y_first_click * cell_size + cell_size / 2, cell_size / 2, paint_select_draught);
        Paint font = new Paint();
        font.setColor(Color.BLACK);
        font.setTextSize(fontSize);
        font.setStyle(Paint.Style.FILL);
        if (field.get_color()){
            canvas.drawText("Ход белого игрока", 0, 1090, font);
        }else {
            canvas.drawText("Ход чёрного игрока", 0, 1090, font);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if ((event.getAction() != MotionEvent.ACTION_DOWN) || ((event.getY() > 8 * cell_size) || (event.getX() > 8 * cell_size)))
            return super.onTouchEvent(event);
        click = !click;
        if (click) {
            x_first_click = (int) event.getX() / 130;
            y_first_click = (int) event.getY() / 130;
            if (!field.check_free(y_first_click,x_first_click)) {
                if (field.get_color() == field.get_draught(y_first_click,x_first_click).get_color()) {
                    postInvalidate();
                }else {
                    x_first_click = -10;
                    y_first_click = -10;
                    click = !click;
                    game.wrong_color();
                }
            }else{
                x_first_click = -10;
                y_first_click = -10;
                click = !click;
                game.empty_cell();
            }
        }else {
            x_second_click = (int) event.getX() / 130;
            y_second_click = (int) event.getY() / 130;
            try {
                Api.move_draught(field, y_first_click,x_first_click,y_second_click,x_second_click);
                postInvalidate();
            }catch (IllegalArgumentException ex){
                try {
                    Api.destroy_draught(field,y_first_click,x_first_click,y_second_click,x_second_click);
                    postInvalidate();
                }catch (IllegalArgumentException e){
                    x_first_click = -10;
                    y_first_click = -10;
                    postInvalidate();
                    game.wrong_move();
                }
            }
            x_first_click = -10;
            y_first_click = -10;
        }
        return true;
    }
}
