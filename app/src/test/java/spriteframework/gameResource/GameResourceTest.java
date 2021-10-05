package spriteframework.gameResource;



import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class GameResourceTest {


    @Test
    public void testResource() {
        GameResources gameResources = GameResources.getInstance();
        String id = "test";
        Integer test = 10;
        gameResources.add(id, test);

        gameResources = GameResources.getInstance();

        Integer result = (Integer) gameResources.get(id);
        assertEquals(test, result);
        assertEquals(gameResources.remove(id), result);

        assertThrows(GameResourcesException.class, () -> {
            GameResources.getInstance().remove(id);
        });

        test = 20;
        gameResources.replace(id, test);
        assertEquals(test, gameResources.get(id));

        test = 40;
        gameResources.replace(id, test);
        assertEquals(test, gameResources.get(id));


    }
}
