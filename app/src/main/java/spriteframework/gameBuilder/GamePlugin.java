package spriteframework.gameBuilder;

import org.jetbrains.annotations.NotNull;

public interface GamePlugin {
    void build(@NotNull GameBuilder builder);
}
