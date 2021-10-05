package woodyFreezeMonster.plugins.MonsterGoopPlugin.systems;

import org.jetbrains.annotations.NotNull;
import spriteframework.Commons;
import spriteframework.assetsLoader.AssetsLoader;
import spriteframework.board.Drawable;
import spriteframework.gameResource.GameResources;
import spriteframework.observer.Observer;
import spriteframework.sprite.Sprite;
import spriteframework.sprite.Vec2D;
import woodyFreezeMonster.FreezeMonsterCommons;
import woodyFreezeMonster.plugins.MonsterGoopPlugin.Goop;
import woodyFreezeMonster.plugins.MonsterPlugin.componets.Monster;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class SpawnGoopSystem implements Observer<GameResources> {

    private final Image goopImage = AssetsLoader.loadImage(FreezeMonsterCommons.GOOP_IMAGE);

    @Override
    public void update(@NotNull GameResources resources) {
        List<Monster> monsters = (List<Monster>) resources.get(FreezeMonsterCommons.MONSTERS);
        List<Goop> goops = (List<Goop>) resources.get(FreezeMonsterCommons.MONSTER_GOOP);
        List<Drawable> drawables = (List<Drawable>) resources.get(Commons.DRAWABLES);

        for (Monster monster : monsters) {
            if (new Random().nextInt(100) != 5)
                continue;

            Vec2D pos = monster.sprite.position.copy();
            pos.x += monster.sprite.image.getWidth(null) / 2;
            pos.y += monster.sprite.image.getHeight(null) / 2;

            Sprite goopSprite = new Sprite(goopImage, pos, true);
            goops.add(new Goop(goopSprite, monster.speed));
            drawables.add(goopSprite);
        }
    }
}
