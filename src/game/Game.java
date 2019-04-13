package game;

import java.util.Observable;
import java.util.Random;

public class Game extends Observable implements Runnable{

    private boolean[][] cells;
    private int xLength, yLength;
    private volatile boolean running;

    public Game(int xLength, int yLength){
        this.xLength = xLength;
        this.yLength = yLength;
        this.cells = new boolean[xLength][yLength];
        this.running = false;
        clearCells();
    }

    public void clearCells(){
        if(!running) {
            for (int x = 0; x < xLength; x++) {
                for (int y = 0; y < yLength; y++) {
                    this.cells[x][y] = false;
                }
            }
            this.setChanged();
            this.notifyObservers();
        }
    }

    public void randomize(){
        if(!running) {
            this.clearCells();
            int minCells = 10;
            int maxCells = this.xLength * this.yLength - minCells;
            int nbCells = new Random().nextInt(maxCells);
            for (int i = 0; i < nbCells; i++) {
                boolean ok = false;
                while (!ok) {
                    int x = new Random().nextInt(this.xLength);
                    int y = new Random().nextInt(this.yLength);
                    if (!this.cells[x][y]) {
                        this.addCell(x, y);
                        ok = true;
                    }
                }
            }
            this.setChanged();
            this.notifyObservers();
        }
    }

    public void addCell(int x, int y){
        this.cells[x][y] = true;
    }

    public void nextGeneration(){
        boolean[][] nextGeneration = new boolean[this.xLength][this.yLength];
        for(int x = 0; x < xLength; x++){
            for(int y = 0; y < yLength; y++){
                int nbVoisins = countVoisins(x, y);
                if(this.cells[x][y]){
                    nextGeneration[x][y] = survive(nbVoisins);
                } else {
                    nextGeneration[x][y] = born(nbVoisins);
                }
            }
        }
        this.cells = nextGeneration;
        this.setChanged();
        this.notifyObservers();

    }

    private int countVoisins(int x, int y) {
        int nbVoisins = 0;
        if (x > 0) {
            nbVoisins += incrementNeighbours(x - 1, y);
            if (y > 0) {
                nbVoisins += incrementNeighbours(x - 1, y - 1);
            }
            if (y < this.yLength - 1) {
                nbVoisins += incrementNeighbours(x - 1, y + 1);
            }
        }
        if (x < this.xLength - 1) {
            nbVoisins += incrementNeighbours(x + 1, y);
            if (y > 0) {
                nbVoisins += incrementNeighbours(x + 1, y - 1);
            }
            if (y < this.yLength - 1) {
                nbVoisins += incrementNeighbours(x + 1, y + 1);
            }
        }
        if (y > 0) {
            nbVoisins += incrementNeighbours(x, y - 1);
        }
        if (y < yLength - 1) {
            nbVoisins += incrementNeighbours(x, y + 1);
        }
        return nbVoisins;
    }

    private int incrementNeighbours(int x, int y) {
        if(this.cells[x][y]){
            return 1;
        }
        return 0;
    }

    private boolean born(int nbVoisins){
        return nbVoisins == 3;
    }

    private boolean survive(int nbVoisins){
        return nbVoisins == 2 || nbVoisins == 3;
    }

    @Override
    public void run() {
        this.running = true;
        while(running){
            this.nextGeneration();
            this.notifyObservers();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        this.running = false;
    }

    public boolean isCellAlive(int x, int y){
        return this.cells[x][y];
    }

    public int getxLength() {
        return xLength;
    }

    public void setxLength(int xLength) {
        this.xLength = xLength;
    }

    public int getyLength() {
        return yLength;
    }

    public void setyLength(int yLength) {
        this.yLength = yLength;
    }

    public boolean isRunning(){
        return this.running;
    }

    public void toogleCell(int x, int y){
        this.cells[x][y] = !this.cells[x][y];
    }

    @Override
    public String toString(){
        String s = "";
        for(int x = 0; x < xLength; x++){
            for(int y = 0; y < yLength; y++){
                s += (this.cells[x][y] ? "X" : "-");
            }
            s += "\n";
        }
        return s;
    }
}
