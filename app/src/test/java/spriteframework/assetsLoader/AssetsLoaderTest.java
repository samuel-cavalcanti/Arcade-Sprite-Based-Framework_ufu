package spriteframework.assetsLoader;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertEquals;

public class AssetsLoaderTest {


    @Test
    public void testAssetsLoader() {

        String imagePath = "/spaceinvaders.png";

        Image image = AssetsLoader.loadImage(imagePath);

        assertEquals(image.getWidth(null),358);
        assertEquals(image.getHeight(null),350);

        assertThrows(AssetsException.class,()->{
            AssetsLoader.loadImage("test");
        });



    }

}
