package spriteframework.keyboard;

import org.jetbrains.annotations.NotNull;
import spriteframework.observer.Observer;
import spriteframework.observer.Subject;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;


public class Keyboard extends KeyAdapter implements Subject<KeyboardEvent> {
    private final List<Observer<KeyboardEvent>> observers;
    private static Keyboard instance;


    private Keyboard() {
        observers = new LinkedList<>();
    }

    public static Keyboard getInstance() {
        if (instance == null)
            instance = new Keyboard();

        return instance;
    }


    @Override
    public void keyReleased(KeyEvent e) {

        KeyboardEvent event = new KeyboardEvent(KeyboardAction.Released, e);
        notifyObservers(event);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        KeyboardEvent event = new KeyboardEvent(KeyboardAction.Pressed, e);
        notifyObservers(event);
    }

    @Override
    public void attach(@NotNull Observer<KeyboardEvent> e) {
        observers.add(e);
    }

    @Override
    public void detach(@NotNull Observer<KeyboardEvent> e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers(@NotNull KeyboardEvent e) {
        observers.forEach(o -> o.update(e));
    }
}