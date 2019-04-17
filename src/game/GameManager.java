package game;

public class GameManager {

    //------- SINGLETON ------//

    private static GameManager instance = new GameManager();

    public static GameManager getInstance() {
        return instance;
    }

    //------------------------//

    private Game game;
    private Thread gameThread;

    private GameManager() {
        this.game = new Game(50,50);
    }

    //------ Thread Part --------//

    public void runGame(){
        if(this.gameThread != null){
            if(!this.gameThread.isAlive()){
                this.gameThread.start();
            } else {
                stop();
            }
        } else {
            this.gameThread = new Thread(this.game, "Game");
            runGame();
        }
    }

    public void stop(){
        this.game.stop();
    }

    //---------------------------//

    public void createNewGame(int width, int heigth){
        if(this.game != null && !this.game.isRunning()){
            this.game = new Game(width, heigth);
        }
    }




    public Game getGame() {
        return game;
    }
}
