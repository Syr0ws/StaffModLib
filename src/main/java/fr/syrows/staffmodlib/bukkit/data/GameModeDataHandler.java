package fr.syrows.staffmodlib.bukkit.data;

import fr.syrows.staffmodlib.common.data.DataHandler;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GameModeDataHandler implements DataHandler<Player> {

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
