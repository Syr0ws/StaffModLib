package fr.syrows.staffmodlib.data;

import org.bukkit.entity.Player;

public class HealthData implements Data {

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
