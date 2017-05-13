package com.example.aleksey.draughts;


public class Field {
    private Draughts[][] draughts;
    private boolean color = true;

    public Field() {
        draughts = new Draughts[8][8];
        int i = 0;
        while (i <= 7){
            draughts[0][i] = new Draughts(0,i,true,false);
            i = i + 2;
        }
        i = 1;
        while (i <= 7){
            draughts[1][i] = new Draughts(1,i,true,false);
            i = i + 2;
        }
        i = 0;
        while (i <= 7){
            draughts[2][i] = new Draughts(2,i,true,false);
            i = i + 2;
        }
        i = 1;
        while (i <= 7){
            draughts[5][i] = new Draughts(5,i,false,false);
            i = i + 2;
        }
        i = 0;
        while (i <= 7){
            draughts[6][i] = new Draughts(6,i,false,false);
            i = i + 2;
        }
        i = 1;
        while (i <= 7) {
            draughts[7][i] = new Draughts(7,i,false,false);
            i = i + 2;
        }
        color = true;
    }

    public boolean check_free(int x, int y){
        if ((x <= 7) && (y <= 7) && (x >= 0) && (y >= 0)) {
            if (draughts[x][y] == null) {
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean check_destruction(int x_selected, int y_selected, int x_destroyed, int y_destroyed){
        if (!draughts[x_selected][y_selected].get_type()) {
            if (!draughts[x_selected][y_selected].get_color()) {
                if ((draughts[x_destroyed][y_destroyed] != null) && (Math.abs(draughts[x_selected][y_selected].get_x() - draughts[x_destroyed][y_destroyed].get_x()) == 1) &&
                        (Math.abs(draughts[x_selected][y_selected].get_y() - draughts[x_destroyed][y_destroyed].get_y()) == 1) &&
                        (draughts[x_destroyed][y_destroyed].get_color() != draughts[x_selected][y_selected].get_color()) &&
                        (check_free(x_selected + 2 * (x_destroyed - x_selected), y_selected + 2 * (y_destroyed - y_selected))) &&
                        (x_selected + 2 * (x_destroyed - x_selected) >= 0) &&
                        (x_selected + 2 * (x_destroyed - x_selected) <= 7) &&
                        (y_selected + 2 * (y_destroyed - y_selected) >= 0) &&
                        (y_selected + 2 * (y_destroyed - y_selected) <= 7)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if ((draughts[x_destroyed][y_destroyed] != null) && (Math.abs(draughts[x_selected][y_selected].get_x() - draughts[x_destroyed][y_destroyed].get_x()) == 1) &&
                        (Math.abs(draughts[x_selected][y_selected].get_y() - draughts[x_destroyed][y_destroyed].get_y()) == 1) &&
                        (draughts[x_destroyed][y_destroyed].get_color() != draughts[x_selected][y_selected].get_color()) &&
                        (check_free(x_selected + 2 * (x_destroyed - x_selected), y_selected + 2 * (y_destroyed - y_selected))) &&
                        (x_selected + 2 * (x_destroyed - x_selected) >= 0) &&
                        (x_selected + 2 * (x_destroyed - x_selected) <= 7) &&
                        (y_selected + 2 * (y_destroyed - y_selected) >= 0) &&
                        (y_selected + 2 * (y_destroyed - y_selected) <= 7)) {
                    return true;
                } else {
                    return false;
                }
            }
        }else {
            if ((x_destroyed - x_selected > 0) && (y_destroyed - y_selected > 0)) {
                if ((draughts[x_selected][y_selected].check_of_move(x_destroyed, y_destroyed))
                        && (draughts[x_destroyed][y_destroyed] != null)
                        && (draughts[x_destroyed][y_destroyed].get_color() != draughts[x_selected][y_selected].get_color())
                        && check_free(x_destroyed + 1, y_destroyed + 1) &&
                        (x_destroyed + 1 >= 0) &&
                        (x_destroyed + 1 <= 7) &&
                        (y_destroyed + 1 >= 0) &&
                        (y_destroyed + 1 <= 7)) {
                    return true;
                } else {
                    return false;
                }
            }
            if ((x_destroyed - x_selected > 0) && (y_destroyed - y_selected < 0)) {
                if ((draughts[x_selected][y_selected].check_of_move(x_destroyed, y_destroyed))
                        && (draughts[x_destroyed][y_destroyed] != null)
                        && (draughts[x_destroyed][y_destroyed].get_color() != draughts[x_selected][y_selected].get_color())
                        && check_free(x_destroyed + 1, y_destroyed - 1) && (x_destroyed + 1 >= 0)
                        && (x_destroyed + 1 <= 7)
                        && (y_destroyed - 1 >= 0)
                        && (y_destroyed - 1 <= 7)) {
                    return true;
                } else {
                    return false;
                }
            }
            if ((x_destroyed - x_selected < 0) && (y_destroyed - y_selected > 0)) {
                if ((draughts[x_selected][y_selected].check_of_move(x_destroyed, y_destroyed))
                        && (draughts[x_destroyed][y_destroyed] != null)
                        && (draughts[x_destroyed][y_destroyed].get_color() != draughts[x_selected][y_selected].get_color())
                        && check_free(x_destroyed - 1, y_destroyed + 1)
                        && (x_destroyed - 1 >= 0)
                        && (x_destroyed - 1 <= 7)
                        && (y_destroyed + 1 >= 0)
                        && (y_destroyed + 1 <= 7)) {
                    return true;
                } else {
                    return false;
                }
            }
            if ((x_destroyed - x_selected < 0) && (y_destroyed - y_selected < 0)) {
                if ((draughts[x_selected][y_selected].check_of_move(x_destroyed, y_destroyed))
                        && (draughts[x_destroyed][y_destroyed] != null)
                        && (draughts[x_destroyed][y_destroyed].get_color() != draughts[x_selected][y_selected].get_color())
                        && check_free(x_destroyed - 1, y_destroyed - 1)
                        && (x_destroyed - 1 >= 0)
                        && (x_destroyed - 1 <= 7)
                        && (y_destroyed - 1 >= 0)
                        && (y_destroyed - 1 <= 7)) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }
    }

    public  Draughts get_draught(int x, int y){return draughts[x][y];}

    public void set_draught(Draughts draught){
        draughts[draught.get_x()][draught.get_y()] = draught;
    }

    public void  set_null(int x, int y){
        draughts[x][y] = null;
    }

    public void set_color(boolean color){this.color = color;}

    public boolean get_color(){return color;}
}
