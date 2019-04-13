package view;

import game.Game;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    private Game game;
    private FieldView fieldView;
    private ControlView controlView;


    public MainView(Game game){

        this.setSize(700,600);

        this.game = game;
        this.fieldView = new FieldView(this.game);
        this.game.addObserver(this.fieldView);
        this.add(this.fieldView, BorderLayout.CENTER);

        this.controlView = new ControlView(this);
        this.add(this.controlView, BorderLayout.EAST);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void setGame(Game game){
        this.game = game;
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
