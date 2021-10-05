package spriteframework;

import org.jetbrains.annotations.NotNull;
import spriteframework.board.Board;
import spriteframework.gameCycle.GameCycle;
import spriteframework.gameResource.GameResources;
import spriteframework.observer.Observer;

import java.awt.*;

public class Game {
    private final String title;
    private final Board board;


    public Game(String title, int width, int height) {
        this.title = title;
        board = new Board(width, height);

    }

    public void run() {

        EventQueue.invokeLater(() -> {
            GameCycleObserver gameCycleObserver = new GameCycleObserver();
            GameCycle gameCycle = GameCycle.getInstance();
            gameCycle.attach(gameCycleObserver);
            gameCycle.start();
            new MainFrame(title, board);
        });
    }

    class GameCycleObserver implements Observer<GameResources> {

        @Override
        public void update(@NotNull GameResources e) {
            board.repaint();
        }
    }
}
