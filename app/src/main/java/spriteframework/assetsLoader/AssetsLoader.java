package spriteframework.assetsLoader;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class AssetsLoader {

    public static Image loadImage(@NotNull String imagePath) {

        URL path = AssetsLoader.class.getResource(imagePath);

        if (path == null)
            throw new AssetsException("unable do find image, path: " + imagePath);

        return new ImageIcon(path).getImage();
    }
}

