package spriteframework.gameCycle;

import org.jetbrains.annotations.NotNull;
import spriteframework.Commons;
import spriteframework.gameResource.GameResources;
import spriteframework.observer.Observer;
import spriteframework.observer.Subject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.util.concurrent.ConcurrentLinkedDeque;

public class GameCycle implements ActionListener, Subject<GameResources> {

    private static GameCycle singleton;
    private int lastTimeInNanoSeconds;
    private final ConcurrentLinkedDeque<Observer<GameResources>> observers;
    private final GameResources gameResources;
    private final Timer timer;

    private GameCycle() {
        observers = new ConcurrentLinkedDeque<>();
        gameResources = GameResources.getInstance();
        this.lastTimeInNanoSeconds = Instant.now().getNano();
        timer = new Timer(Commons.DELAY, this);
    }

    public static GameCycle getInstance() {

        if (singleton == null)
            singleton = new GameCycle();
        return singleton;
    }

    public void start() {
        timer.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        int timeInNanoSeconds = Instant.now().getNano();

        int deltaTime = timeInNanoSeconds - lastTimeInNanoSeconds;

        lastTimeInNanoSeconds = timeInNanoSeconds;

        gameResources.replace(Commons.DELTA_TIME, new TimeEvent(deltaTime));

        notifyObservers(gameResources);

    }

    @Override
    public void attach(@NotNull Observer<GameResources> e) {

        observers.add(e);
    }

    @Override
    public void detach(@NotNull Observer<GameResources> e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers(@NotNull GameResources e) {

        observers.forEach(o -> o.update(e));


    }
}
