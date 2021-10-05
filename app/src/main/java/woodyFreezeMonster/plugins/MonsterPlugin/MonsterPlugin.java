package woodyFreezeMonster.plugins.MonsterPlugin;

import org.jetbrains.annotations.NotNull;
import spriteframework.Commons;
import spriteframework.assetsLoader.AssetsLoader;
import spriteframework.board.Drawable;
import spriteframework.gameBuilder.GameBuilder;
import spriteframework.gameBuilder.GamePlugin;
import spriteframework.sprite.Sprite;
import spriteframework.sprite.Vec2D;
import woodyFreezeMonster.FreezeMonsterCommons;
import woodyFreezeMonster.plugins.MonsterPlugin.componets.Monster;
import woodyFreezeMonster.plugins.MonsterPlugin.componets.MonsterRandomDirection;
import woodyFreezeMonster.plugins.MonsterPlugin.systems.MonsterMovementSystem;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MonsterPlugin implements GamePlugin {

    private final Image[] monsterImages;
    private final Image[] frozenMonsterImages;


    public MonsterPlugin() {
        monsterImages = loadImages(FreezeMonsterCommons.MONSTERS_IMAGE_TEMPLATE);
        frozenMonsterImages = loadImages(FreezeMonsterCommons.FROZEN_MONSTERS_IMAGE_TEMPLATE);
    }

    private Image @NotNull [] loadImages(String template) {
        Image[] images = new Image[FreezeMonsterCommons.TOTAL_MONSTER_TYPES];

        for (int i = 1; i <= 9; i++)
            images[i - 1] = AssetsLoader.loadImage(template.formatted(i));

        return images;
    }

    private @NotNull List<Monster> createNewMonsters() {
        assert monsterImages.length == 9;

        List<Monster> monsters = new LinkedList<>();
        final int initialMonsterPos = 5;
        final int biggerMonsterSizeY = 50;
        final int biggerMonsterSizeX = 40;
        final int spaceYBetween = FreezeMonsterCommons.BOARD_HEIGHT - initialMonsterPos - Commons.NATIVE_WINDOW_HEIGHT - biggerMonsterSizeY;
        final int finalMonsterPosX = FreezeMonsterCommons.BOARD_WIDTH - biggerMonsterSizeX;
        final MonsterRandomDirection randomDirection = new MonsterRandomDirection();

        for (int i = 0; i < 3; i++) {
            int posY = initialMonsterPos + i * spaceYBetween / 2;
            monsters.add(new Monster(new Sprite(monsterImages[i], new Vec2D(initialMonsterPos, posY), true), frozenMonsterImages[i], randomDirection.getRandomDirection()));
            monsters.add(new Monster(new Sprite(monsterImages[i + 3], new Vec2D(finalMonsterPosX, posY), true), frozenMonsterImages[i +3], randomDirection.getRandomDirection()));
            monsters.add(new Monster(new Sprite(monsterImages[i + 6], new Vec2D(finalMonsterPosX / 2, posY), true), frozenMonsterImages[i +6], randomDirection.getRandomDirection()));
        }


        return monsters;
    }

    @Override
    public void build(@NotNull GameBuilder builder) {

        final List<Monster> monsters = createNewMonsters();

        builder.addResource(FreezeMonsterCommons.FREEZE_MONSTERS_IMAGE, frozenMonsterImages)
                .addResource(FreezeMonsterCommons.MONSTERS, monsters)
                .addDrawables(monsters.stream().map((m) -> (Drawable) m.sprite).collect(Collectors.toList()))
                .addGameCycleObserver(new MonsterMovementSystem());

    }


}
