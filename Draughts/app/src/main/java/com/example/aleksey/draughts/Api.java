package com.example.aleksey.draughts;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Api {

    public static void move_draught(Field field, int x_draught, int y_draught, int x_place, int y_place) {
        Draughts draught = field.get_draught(x_draught, y_draught);
        if (check_destruction_around(field) == true){
            throw new IllegalArgumentException();
        }
        if (field.get_color() == draught.get_color()) {
            if (draught.check_of_move(x_place, y_place) == false) {
                throw new IllegalArgumentException();
            } else {
                if (field.check_free(x_place, y_place) == false) {
                    throw new IllegalArgumentException();
                } else {
                    field.set_null(x_draught, y_draught);
                    draught.set_x(x_place);
                    draught.set_y(y_place);
                    field.set_draught(draught);
                    if (draught.check_of_type() == true) {
                        draught.set_type(true);
                    }
                }
            }
        }else {
            throw new IllegalArgumentException();
        }
        field.set_color(!field.get_color());
    }

    public static void destroy_draught(Field field, int x_selected, int y_selected, int x_destroyed, int y_destroyed) {
        Draughts draught = field.get_draught(x_selected, y_selected);
        if (field.get_color() == draught.get_color()) {
            if (field.check_destruction(x_selected, y_selected, x_destroyed, y_destroyed) == false) {
                throw new IllegalArgumentException();
            } else {
                field.set_null(x_selected, y_selected);
                field.set_null(x_destroyed, y_destroyed);
                draught.set_x(x_selected + 2 * (x_destroyed - x_selected));
                draught.set_y(y_selected + 2 * (y_destroyed - y_selected));
                field.set_draught(draught);
                draught.check_of_type();
                if (draught.check_of_type() == true) {
                    draught.set_type(true);
                }
            }
        }
        if (check_destruction_around(field) == false) {
            field.set_color(!field.get_color());
        }
    }

    public static void save_file(Field field) throws IOException {
        File file = new File("savefile.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter wrt = new FileWriter(file);
        String lineSeparator = System.getProperty("line.separator");
        for (int i = 0; i <= 7; ++i){
            for (int j = 0; j <= 7; ++j){
                Draughts draught = field.get_draught(i,j);
                if (draught == null){
                    wrt.append("nl ");
                }else {
                    if ((draught.get_color() == true) && (draught.get_type() == false)) {
                        wrt.append("tf ");
                    }
                    if ((draught.get_color() == true) && (draught.get_type() == true)) {
                        wrt.append("tt ");
                    }
                    if ((draught.get_color() == false) && (draught.get_type() == false)) {
                        wrt.append("ff ");
                    }
                    if ((draught.get_color() == false) && (draught.get_type() == true)) {
                        wrt.write("ft ");
                    }
                }
            }
            wrt.append(lineSeparator);
        }
        if (field.get_color() == true){
            wrt.append("tr");
        }else {
            wrt.append("fl");
        }
        wrt.close();
    }

    public static Field load_file() throws FileNotFoundException {
        File file = new File("savefile.txt");
        Scanner s = new Scanner(file);
        String string;
        Field field = new Field();
        for (int i = 0; i <= 7; ++i){
            for (int j = 0; j <= 7; ++j){
                string = s.next();
                if (string.equals("nl")){
                    field.set_null(i,j);
                }
                if (string.equals("tt")){
                    field.set_draught(new Draughts(i,j,true,true));
                }
                if (string.equals("tf")){
                    field.set_draught(new Draughts(i,j,true,false));
                }
                if (string.equals("ff")){
                    field.set_draught(new Draughts(i,j,false,false));
                }
                if (string.equals("ft")){
                    field.set_draught(new Draughts(i,j,false,true));
                }
            }
        }
        string = s.next();
        if (string.equals("tr")){
            field.set_color(true);
        }else {
            field.set_color(false);
        }
        return field;
    }

    public static int[] get_statistics(Field field){
        int[] statistics = {0,0,0,0,0,0};
        for (int i = 0; i <= 7; ++i){
            for (int j = 0; j <= 7; ++j){
                Draughts draught = field.get_draught(i,j);
                if (draught != null) {
                    if (draught.get_color() == true) {
                        ++statistics[0];
                        if (draught.get_type() == true) {
                            ++statistics[1];
                        }
                    }else {
                        ++statistics[3];
                        if (draught.get_type() == true) {
                            ++statistics[4];
                        }
                    }
                }
            }
        }
        statistics[2] = 12 - statistics[0];
        statistics[5] = 12 - statistics[3];
        return statistics;
    }

    private static boolean check_destruction_around(Field field){
        for (int i = 0; i <= 7; ++i) {
            for (int j = 0; j <= 7; ++j) {
                if ((field.get_draught(i,j) != null) && (field.get_draught(i,j).get_color() == field.get_color())) {
                    if ((i + 1 <= 7) && (i + 1 >= 0) && (j + 1 <= 7) && (j + 1 >= 0) &&
                            (field.check_destruction(i, j, i + 1, j + 1) == true)) {
                        return true;
                    }
                    if ((i - 1 <= 7) && (i - 1 >= 0) && (j + 1 <= 7) && (j + 1 >= 0) &&
                            (field.check_destruction(i, j, i - 1, j + 1) == true)) {
                        return true;
                    }
                    if ((i + 1 <= 7) && (i + 1 >= 0) && (j - 1 <= 7) && (j - 1 >= 0) &&
                            (field.check_destruction(i, j, i + 1, j - 1) == true)) {
                        return true;
                    }
                    if ((i - 1 <= 7) && (i - 1 >= 0) && (j - 1 <= 7) && (j - 1 >= 0) &&
                            (field.check_destruction(i, j, i - 1, j - 1) == true)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}