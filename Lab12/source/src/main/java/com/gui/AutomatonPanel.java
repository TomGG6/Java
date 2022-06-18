package com.gui;

import javax.swing.*;
import java.awt.*;

public class AutomatonPanel extends JPanel {
    int[][] state;
    private int rows, columns;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;

    public AutomatonPanel(int[][] state){
        this.state = state;
        this.rows = state.length;
        this.columns = state[0].length;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int ht = getHeight();
        Color cellColor = Color.WHITE;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(state[i][j] == 0){
                    cellColor = Color.BLACK;
                }else if (state[i][j] == 1){
                    cellColor = Color.YELLOW;
                }else if (state[i][j] == 2){
                    cellColor = Color.BLUE;
                }else if (state[i][j] == 3){
                    cellColor = Color.RED;
                }

                g.setColor(cellColor);
                int x = (i * width) / columns;
                int y = (j * ht) / rows;
                int w = ((i + 1) * width) / columns - x;
                int h = ((j + 1) * ht) / rows - y;
                g.fillRect(x, y, w, h);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
}
