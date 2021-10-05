package spriteframework.keyboard;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import spriteframework.observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.Assert.assertEquals;

public class KeyBoardTest {


    class ObserverKeyEvent implements Observer<KeyboardEvent> {
        private KeyboardEvent event;

        @Override
        public void update(@NotNull KeyboardEvent e) {
            event = e;
        }
    }

    @Test
    public void testKeyboard() {
        Keyboard keyboard = Keyboard.getInstance();
        ObserverKeyEvent observerKeyEvent = new ObserverKeyEvent();
        assertEquals(keyboard, Keyboard.getInstance());

        keyboard.attach(observerKeyEvent);

        Component c = new JPanel();

        int keyCode = KeyEvent.VK_A;
        keyboard.keyPressed(new KeyEvent(c, 0, 0, 0, keyCode));

        assertEquals(observerKeyEvent.event.event.getKeyCode(), keyCode);
        assertEquals(observerKeyEvent.event.action, KeyboardAction.Pressed);

        keyCode = KeyEvent.VK_B;
        keyboard.keyReleased(new KeyEvent(c, 0, 0, 0, keyCode));
        assertEquals(observerKeyEvent.event.event.getKeyCode(), keyCode);
        assertEquals(observerKeyEvent.event.action, KeyboardAction.Released);


    }
}
