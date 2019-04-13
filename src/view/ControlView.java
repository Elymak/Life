package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlView extends JPanel implements ActionListener {

    private static final String RUN = "Run";
    private static final String STOP = "Stop";
    private static final String CLEAR = "Clear";
    private static final String RANDOMIZE = "Randomize";

    private JButton runButton;
    private JButton clearButton;
    private JButton randomButton;

    private MainView mainView;

    public ControlView(MainView mainView) {

        this.mainView = mainView;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.runButton = new JButton(RUN);
        this.runButton.addActionListener(this);
        this.add(this.runButton);

        this.clearButton = new JButton(CLEAR);
        this.clearButton.addActionListener(this);
        this.add(this.clearButton);

        this.randomButton = new JButton(RANDOMIZE);
        this.randomButton.addActionListener(this);
        this.add(this.randomButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        switch (source.getText()) {
            case RUN:
                doRun();
                break;
            case STOP:
                doStop();
                break;
            case CLEAR:
                doClear();
                break;
            case RANDOMIZE:
                doRandomize();
                break;
            default:
                break;

        }
    }

    private void doRun(){
        this.runButton.setText(STOP);
        this.clearButton.setEnabled(false);
        this.randomButton.setEnabled(false);
        this.mainView.runGame();
    }

    private void doStop(){
        this.runButton.setText(RUN);
        this.clearButton.setEnabled(true);
        this.randomButton.setEnabled(true);
        this.mainView.stopGame();
    }

    private void doClear(){
        this.mainView.getGame().clearCells();
        this.mainView.repaint();
    }

    private void doRandomize(){
        this.mainView.getGame().randomize();
        this.mainView.repaint();
    }
}
