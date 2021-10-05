package spriteframework.gameCycle;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import spriteframework.gameResource.GameResources;
import spriteframework.observer.Observer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameCycleTest {

    class ObserverGameCycle implements Observer<GameResources> {
        boolean updating = false;

        @Override
        public void update(@NotNull GameResources e) {
            updating = true;
        }
    }

    @Test
    public void testGameCycle() throws InterruptedException {
        ObserverGameCycle observerGameCycle = new ObserverGameCycle();
        assertFalse(observerGameCycle.updating);
        GameCycle gameCycle = GameCycle.getInstance();

        gameCycle.attach(observerGameCycle);
        gameCycle.start();

        Thread.sleep(500, 0);

        assertTrue(observerGameCycle.updating);


    }

}
