package spriteframework.gameBuilder;

import org.jetbrains.annotations.NotNull;
import spriteframework.Commons;
import spriteframework.Game;
import spriteframework.board.Drawable;
import spriteframework.gameCycle.GameCycle;
import spriteframework.gameResource.GameResources;
import spriteframework.keyboard.Keyboard;
import spriteframework.keyboard.KeyboardEvent;
import spriteframework.observer.Observer;

import java.util.LinkedList;
import java.util.List;

public class GameBuilder {

    private String gameTitle = Commons.TITLE;
    private final List<Drawable> drawables;
    private int boardWidth = Commons.DEFAULT_BOARD_WIDTH;
    private int boardHeight = Commons.DEFAULT_BOARD_HEIGHT;

    public GameBuilder() {
        drawables = new LinkedList<>();
        GameResources.getInstance().add(Commons.DRAWABLES, drawables);
    }

    public GameBuilder setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
        return this;
    }

    public GameBuilder setBoardSize(int width, int height) {
        boardWidth = width;
        boardHeight = height;
        return this;
    }

    public GameBuilder addKeyBoardObserver(Observer<KeyboardEvent> observer) {
        Keyboard.getInstance().attach(observer);
        return this;
    }

    public GameBuilder addPlugin(@NotNull GamePlugin plugin) {
        plugin.build(this);
        return this;
    }

    public GameBuilder addGameCycleObserver(Observer<GameResources> observer) {
        GameCycle.getInstance().attach(observer);

        return this;
    }

    public GameBuilder addResource(String id, Object o) {
        GameResources.getInstance().add(id, o);
        return this;
    }

    public GameBuilder addDrawable(Drawable d) {
        this.drawables.add(d);
        return this;
    }

    public GameBuilder addDrawables(@NotNull List<Drawable> drawables) {
        this.drawables.addAll(drawables);
        return this;
    }

    public void run() {

        Game game = new Game(gameTitle, boardWidth, boardHeight);
        GameResources resources = GameResources.getInstance();
        resources.add(Commons.BOARD_WIDTH, boardWidth);
        resources.add(Commons.BOARD_HEIGHT, boardHeight);


        game.run();
    }


}
