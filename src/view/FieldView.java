package view;

import constantes.Constantes;
import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

public class FieldView extends JPanel implements Observer, MouseListener {

    private static final int OFFSET = Constantes.CELL_SIZE;
    private Game game;

    public FieldView(Game game){
        this.game = game;
        this.addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if(this.game != null) {
            Graphics2D g2 = (Graphics2D) g;

            for (int x = 0; x < this.game.getxLength(); x++) {
                for (int y = 0; y < this.game.getyLength(); y++) {
                    this.paintCell(x, y, g2);
                }
            }

            this.drawGrid(g2);
        }

    }

    private void paintCell(int x, int y, Graphics2D g2){
        int s = Constantes.CELL_SIZE;
        int xPos = x * s + OFFSET;
        int yPos = y * s + OFFSET;

        g2.setColor(Color.WHITE);
        if(this.game.isCellAlive(x, y)){
            g2.setColor(Color.BLACK);
        }
        g2.fillRect(xPos, yPos, s, s);
    }

    private void drawGrid(Graphics2D g2){
        int s = Constantes.CELL_SIZE;
        int xEdge = (this.game.getxLength() * s) + OFFSET;
        int yEdge = (this.game.getyLength() * s) + OFFSET;
        g2.setColor(Color.BLACK);

        for(int x = 0; x < this.game.getxLength() + 1; x++){
            for(int y = 0; y < this.game.getyLength() + 1; y++){
                g2.drawLine(OFFSET, y * s + OFFSET, xEdge, y * s + OFFSET);
                g2.drawLine(x * s + OFFSET, OFFSET, x * s + OFFSET, yEdge);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        this.game = (Game) o;
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int xClick = e.getX();
        int yClick = e.getY();

        int x = (xClick - OFFSET) / Constantes.CELL_SIZE;
        int y = (yClick - OFFSET) / Constantes.CELL_SIZE;
        boolean isInBounds = x < this.game.getxLength() && y < this.game.getyLength() && x > -1 && y > -1;

        if(isInBounds) {
            this.game.toogleCell(x, y);
            this.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
