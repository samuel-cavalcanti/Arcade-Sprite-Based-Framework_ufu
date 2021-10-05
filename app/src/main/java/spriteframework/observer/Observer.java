package spriteframework.observer;

import org.jetbrains.annotations.NotNull;

/**
 * An interface for all Observers
 */
public interface Observer<Event> {
    /**
     * Informs this observer that an observed subject has changed
     *
     * @param e the observed event that has changed
     */
    void update(@NotNull Event e);
}