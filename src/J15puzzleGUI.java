/*
 * Sceleton was taken from https://github.com/TideSofDarK/tutorial-basics/tree/master/src/org/tides/tutorial
 * Original tutorial was read from https://habrahabr.ru/post/145433/
 *
 */

/*
** Description: 15 Puzzle
** Date: 10 MAY 2016
** Author: Andrii Grytsenko
** Programing Language: Java
*/ 

package ua.kiev.agrit.games.j15puzzle;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Andrii Grytsenko (ahrytsenko@gmail.com)
 */
public class J15puzzleGUI extends JPanel {
    
    private final Dimension DRAUGHT_SIZE = new Dimension(40, 40);
    private final Color DRAUGHT_BG_COLOR = new Color(0, 255, 0);
    private final Color DRAUGHT_FG_COLOR = new Color(255, 255, 255);
    private final int ROWS;
    private final int COLS;
    
    public J15puzzleGUI(int rows, int cols, int space) {
        super();
        setLayout(new GridLayout(rows, cols, space, space));
        ROWS = rows;
        COLS = cols;
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLS; j++) {
                add(new Draught(DRAUGHT_SIZE, DRAUGHT_BG_COLOR, DRAUGHT_FG_COLOR, i*COLS+j+1));
            }
    }
    
    public J15puzzleGUI() {
        this(4, 4, 2);
    }
    
    public static void main(String[] args) {
        JFrame wnd = new JFrame("J15puzzle");
        wnd.setLayout(new GridLayout(1, 1, 2, 2));
        wnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        J15puzzleGUI content = new J15puzzleGUI();
        wnd.setContentPane(content);
        
        wnd.pack();
        wnd.setResizable(false);
        wnd.setVisible(true);
    }
    
    private class Draught extends JPanel implements MouseListener, MouseMotionListener {

        private Dimension size;
        private Color bgColor, fgColor;
        private int number;
        
        public Draught(Dimension size, Color bgColor, Color fgColor, int number) {
            super();
            
            this.size = size;
            this.bgColor = bgColor;
            this.fgColor = fgColor;
            this.number = number;
            
            setBackground(bgColor);
            setForeground(fgColor);
            setSize(size);
            setPreferredSize(size);
            
            addMouseListener(this);
            addMouseMotionListener(this);
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawString(Integer.toString(number), getWidth()/4, getHeight()/4*3);
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("class Draught#" + number + ": mouseClicked");
        }

        @Override
        public void mousePressed(MouseEvent e) { }

        @Override
        public void mouseReleased(MouseEvent e) { }

        @Override
        public void mouseEntered(MouseEvent e) {
            System.out.println("class Draught#" + number + ": mouseEntered");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            System.out.println("class Draught#" + number + ": mouseExited");
        }

        @Override
        public void mouseDragged(MouseEvent e) { }

        @Override
        public void mouseMoved(MouseEvent e) { }
        
    }
    
}
