package spriteframework.keyboard;

import java.awt.event.KeyEvent;

public class KeyboardEvent {

    public final KeyboardAction action;
    public final KeyEvent event;

    public KeyboardEvent(KeyboardAction action, KeyEvent event) {
        this.action = action;
        this.event = event;
    }
}
