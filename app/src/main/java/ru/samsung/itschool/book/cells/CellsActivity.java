package ru.samsung.itschool.book.cells;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.GridLayout;


import task.Stub;
import task.Task;

public class CellsActivity extends Activity implements OnClickListener,
        OnLongClickListener {

    private int WIDTH = 10;
    private int HEIGHT = 10;

    private Button[][] cells;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cells);
        makeCells();

        generate();

    }

    void generate() {

        //Эту строку нужно удалить
        //Task.showMessage(this, "Добавьте код в функцию активности generate() для генерации клеточного поля");
        /*
        int num = 1;
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++) {
                cells[i][j].setText(i + " "+ j);
                num++;

            }

         */
    }

    @Override
    public boolean onLongClick(View v) {
        for (int y = 0; y < WIDTH; y++)
        {
            for (int x = 0; x < WIDTH; x++) {
                cells[x][y].setBackgroundColor(Color.WHITE);
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        //Эту строку нужно удалить
        //Stub.show(this, "Добавьте код в функцию активности onClick() - реакцию на нажатие на клетку");

        Button tappedCell = (Button) v;

        //Получаем координтаты нажатой клетки
        int tappedX = getX(tappedCell);
        int tappedY = getY(tappedCell);

        int color = ((ColorDrawable)cells[tappedY][tappedX].getBackground()).getColor();

        if (((ColorDrawable)cells[tappedX][tappedY].getBackground()).getColor()==Color.BLACK) {
            cells[tappedX][tappedY].setBackgroundColor(Color.WHITE);
        } else {
            cells[tappedX][tappedY].setBackgroundColor(Color.BLACK);
        }

        for (int x = 0; x < WIDTH; x++)
        {
            if (((ColorDrawable)cells[x][tappedY].getBackground()).getColor()==Color.BLACK) {
                cells[x][tappedY].setBackgroundColor(Color.WHITE);
            } else {
                cells[x][tappedY].setBackgroundColor(Color.BLACK);
            }

        }
        for (int y = 0; y < WIDTH; y++)
        {
            if (((ColorDrawable)cells[tappedX][y].getBackground()).getColor()==Color.BLACK) {
                cells[tappedX][y].setBackgroundColor(Color.WHITE);
            } else {
                cells[tappedX][y].setBackgroundColor(Color.BLACK);
            }
        }

    }

	/*
     * NOT FOR THE BEGINNERS
	 * ==================================================
	 */

    int getY(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[1]);
    }

    int getX(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[0]);
    }

    void makeCells() {
        cells = new Button[HEIGHT][WIDTH];
        GridLayout cellsLayout = (GridLayout) findViewById(R.id.CellsLayout);
        cellsLayout.removeAllViews();
        cellsLayout.setColumnCount(WIDTH);
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                cells[i][j] = (Button) inflater.inflate(R.layout.cell, cellsLayout, false);
                cells[i][j].setOnClickListener(this);
                cells[i][j].setOnLongClickListener(this);
                cells[i][j].setTag(i + "," + j);
                cellsLayout.addView(cells[i][j]);
            }
    }

}