package view;

import game.Game;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class MainView extends JFrame implements Observer {

    private Game game;
    private FieldView fieldView;
    private MenuBar menuBar;
    private ControlView controlView;


    public MainView(Game game){

        this.setSize(700,600);

        this.menuBar = new MenuBar(this);
        this.setJMenuBar(this.menuBar);

        this.game = game;
        this.fieldView = new FieldView(this.game);
        this.game.addObserver(this);
        this.add(this.fieldView, BorderLayout.CENTER);

        this.controlView = new ControlView(this);
        this.add(this.controlView, BorderLayout.EAST);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.game = (Game) o;
        this.fieldView.setGame(this.game);
        this.repaint();
    }

    public Game getGame(){
        return this.game;
    }

    public void runGame(){
        Thread gameThread = new Thread(this.game);
        gameThread.start();
    }

    public void stopGame(){
        this.game.stop();
    }

}
