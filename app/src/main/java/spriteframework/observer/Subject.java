package spriteframework.observer;

import org.jetbrains.annotations.NotNull;

public interface Subject<Event> {
    void attach(@NotNull Observer<Event> e);

    void detach(@NotNull Observer<Event> e);

    void notifyObservers(@NotNull Event e);
}
