import game.Game;
import view.MainView;

public class App {
    public static void main(String[] args) {
        Game game = new Game(50, 50);
        MainView mainView = new MainView(game);
    }
}
