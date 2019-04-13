package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar implements ActionListener {

    private static final String FILE = "File";
    private static final String NEW = "New Game";
    private static final String IMPORT = "Import from...";
    private static final String LOAD = "Load Game";
    private static final String SAVE = "Save";
    private static final String SAVE_AS = "Save as...";

    private MainView mainView;

    private JMenu menu;
    private JMenuItem newGameMenuItem;
    private JMenuItem importGameMenuItem;
    private JMenuItem loadGameMenuItem;
    private JMenuItem saveGameMenuItem;
    private JMenuItem saveGameAsMenuItem;

    public MenuBar(MainView mainView) {
        this.mainView = mainView;

        this.menu = new JMenu(FILE);

        this.newGameMenuItem = new JMenuItem(NEW);
        this.newGameMenuItem.addActionListener(this);
        this.menu.add(this.newGameMenuItem);

        this.importGameMenuItem = new JMenuItem(IMPORT);
        this.importGameMenuItem.addActionListener(this);
        this.menu.add(this.importGameMenuItem);

        this.loadGameMenuItem = new JMenuItem(LOAD);
        this.loadGameMenuItem.addActionListener(this);
        this.menu.add(this.loadGameMenuItem);

        this.saveGameMenuItem = new JMenuItem(SAVE);
        this.saveGameMenuItem.addActionListener(this);
        this.saveGameMenuItem.setEnabled(false); //par défaut aucun fichier n'est ouvert
        this.menu.add(this.saveGameMenuItem);

        this.saveGameAsMenuItem = new JMenuItem(SAVE_AS);
        this.saveGameAsMenuItem.addActionListener(this);
        this.menu.add(this.saveGameAsMenuItem);

        this.add(menu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) (e.getSource());
        if (NEW.equals(source.getText())) {
            if(!this.mainView.getGame().isRunning()) {
                this.mainView.getGame().clearCells();
            }
        } else if (IMPORT.equals(source.getText())) {
            //TODO
        }
    }
}
