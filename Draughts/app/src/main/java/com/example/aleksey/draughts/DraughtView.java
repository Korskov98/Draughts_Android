package com.example.aleksey.draughts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class DraughtView extends View {
    private static final int cell_size = 130;
    Field field;
    private boolean click = false;
    private int x_first_click = -10;
    private int y_first_click = -10;
    private int x_second_click;
    private int y_second_click;

    public DraughtView(Context context, Field field) {
        super(context);
        this.field = field;
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint background = new Paint();
        background.setColor(getResources().getColor(R.color.background_field));
        canvas.drawRect(0, 0, getWidth(), getHeight(), background);
        Paint white_cell = new Paint();
        Paint black_cell = new Paint();
        white_cell.setColor(getResources().getColor(R.color.white_cell));
        black_cell.setColor(getResources().getColor(R.color.black_cell));
        for (int i = 0; i <= 7; i = i + 2){
            canvas.drawRect(i * cell_size, 0, i * cell_size + cell_size, cell_size, white_cell);
            canvas.drawRect((i + 1) * cell_size, 0, (i + 1) * cell_size + cell_size, cell_size, black_cell);
            canvas.drawRect((i + 1) * cell_size, cell_size, (i + 1) * cell_size + cell_size, 2 * cell_size, white_cell);
            canvas.drawRect(i * cell_size, cell_size, i * cell_size + cell_size, 2 * cell_size, black_cell);
            canvas.drawRect(i * cell_size, 2 * cell_size, i * cell_size + cell_size, 3 * cell_size, white_cell);
            canvas.drawRect((i + 1) * cell_size, 2 * cell_size, (i + 1) * cell_size + cell_size, 3 * cell_size, black_cell);
            canvas.drawRect((i + 1) * cell_size, 3 * cell_size, (i + 1) * cell_size + cell_size, 4 * cell_size, white_cell);
            canvas.drawRect(i * cell_size, 3 * cell_size, i * cell_size + cell_size, 4 * cell_size, black_cell);
            canvas.drawRect(i * cell_size, 4 * cell_size, i * cell_size + cell_size, 5 * cell_size, white_cell);
            canvas.drawRect((i + 1) * cell_size, 4 * cell_size, (i + 1) * cell_size + cell_size, 5 * cell_size, black_cell);
            canvas.drawRect((i + 1) * cell_size, 5 * cell_size, (i + 1) * cell_size + cell_size, 6 * cell_size, white_cell);
            canvas.drawRect(i * cell_size, 5 * cell_size, i * cell_size + cell_size, 6 * cell_size, black_cell);
            canvas.drawRect(i * cell_size, 6 * cell_size, i * cell_size + cell_size, 7 * cell_size, white_cell);
            canvas.drawRect((i + 1) * cell_size, 6 * cell_size, (i + 1) * cell_size + cell_size, 7 * cell_size, black_cell);
            canvas.drawRect((i + 1) * cell_size, 7 * cell_size, (i + 1) * cell_size + cell_size, 8 * cell_size, white_cell);
            canvas.drawRect(i * cell_size, 7 * cell_size, i * cell_size + cell_size, 8 * cell_size, black_cell);
        }
        Paint black_draught = new Paint();
        Paint white_draught = new Paint();
        white_draught.setColor(getResources().getColor(R.color.white_draught));
        black_draught.setColor(getResources().getColor(R.color.black_draught));
        for (int i = 0; i <= 7; ++i){
            for (int j = 0; j <= 7; ++j){
                Draughts draught = field.get_draught(i,j);
                if ((!field.check_free(i,j)) && (draught.get_color())) {
                    canvas.drawCircle(j * cell_size + cell_size / 2, i * cell_size + cell_size / 2, cell_size / 2, white_draught);
                }
                if ((!field.check_free(i,j)) && (!draught.get_color())) {
                    canvas.drawCircle(j * cell_size + cell_size / 2, i * cell_size + cell_size / 2, cell_size / 2, black_draught);
                }
            }
        }
        Paint select_draught = new Paint();
        select_draught.setColor(getResources().getColor(R.color.select_draught));
        canvas.drawCircle(x_first_click * cell_size + cell_size / 2, y_first_click * cell_size + cell_size / 2, cell_size / 2, select_draught);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if ((event.getAction() != MotionEvent.ACTION_DOWN) || ((event.getY() > 1040) || (event.getX() > 1040)))
            return super.onTouchEvent(event);
        click = !click;
        if (click) {
            x_first_click = (int) event.getX() / 130;
            y_first_click = (int) event.getY() / 130;
            if ((!field.check_free(y_first_click,x_first_click)) && (field.get_draught(y_first_click, x_first_click).get_color() == field.get_color())) {
                postInvalidate();
            }else{
                x_first_click = -10;
                y_first_click = -10;
                click = !click;
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
                }
            }
            x_first_click = -10;
            y_first_click = -10;
        }
        return true;
    }
}
