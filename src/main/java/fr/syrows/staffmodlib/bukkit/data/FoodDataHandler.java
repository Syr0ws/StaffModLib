package fr.syrows.staffmodlib.bukkit.data;

import fr.syrows.staffmodlib.common.data.DataHandler;
import org.bukkit.entity.Player;

public class FoodDataHandler implements DataHandler<Player> {

    private static final int MAX_FOOD_LEVEL = 20;
    private static final float DEFAULT_SATURATION = 1f;

    private int foodLevel;
    private float saturation;

    @Override
    public void save(Player player) {
        this.foodLevel = player.getFoodLevel();
        this.saturation = player.getSaturation();
    }

    @Override
    public void clear(Player player) {
        player.setFoodLevel(MAX_FOOD_LEVEL);
        player.setSaturation(DEFAULT_SATURATION);
    }

    @Override
    public void restore(Player player) {
        player.setFoodLevel(this.foodLevel);
        player.setSaturation(this.saturation);
    }
}
