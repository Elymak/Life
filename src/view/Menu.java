package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JMenuBar implements ActionListener {

    private static final String FILE_LABEL = "File";
    private static final String NEW = "New";
    private static final String EXIT_LABEL = "Quitter";

    private MainView mainView;

    public Menu(MainView mainView) {
        this.mainView = mainView;

        JMenu menu = new JMenu(FILE_LABEL);

        JMenuItem newGameMenuItem = new JMenuItem(NEW);
        newGameMenuItem.addActionListener(this);
        menu.add(newGameMenuItem);

        JMenuItem quitterMenuItem = new JMenuItem(EXIT_LABEL);
        quitterMenuItem.addActionListener(this);
        menu.add(quitterMenuItem);

        this.add(menu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) (e.getSource());
        if (NEW.equals(source.getText())) {
            if(!this.mainView.getGame().isRunning()){
                this.mainView.getGame().clearCells();
            }
        } else if (EXIT_LABEL.equals(source.getText())) {
            System.exit(0);
        }
    }
}
