package fr.syrows.staffmodlib.bukkit.data;

import fr.syrows.staffmodlib.common.data.DataHandler;
import org.bukkit.entity.Player;

public class HealthDataHandler implements DataHandler<Player> {

    private double health;

    @Override
    public void save(Player player) {
        this.health = player.getHealth();
    }

    @Override
    public void clear(Player player) {
        player.setHealth(player.getMaxHealth());
    }

    @Override
    public void restore(Player player) {
        player.setHealth(this.health);
    }
}
