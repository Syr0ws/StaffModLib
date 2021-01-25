package fr.syrows.staffmodlib.data;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GameModeData implements Data {

    private GameMode gamemode;

    @Override
    public void save(Player player) {
        this.gamemode = player.getGameMode();
    }

    @Override
    public void clear(Player player) {
        player.setGameMode(GameMode.SURVIVAL);
    }

    @Override
    public void restore(Player player) {
        player.setGameMode(this.gamemode);
    }
}
