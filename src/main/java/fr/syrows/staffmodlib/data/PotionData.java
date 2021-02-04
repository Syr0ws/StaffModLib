package fr.syrows.staffmodlib.data;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

public class PotionData implements Data {

    private List<PotionEffect> effects = new ArrayList<>();

    @Override
    public void save(Player player) {
        this.effects = new ArrayList<>(player.getActivePotionEffects());
    }

    @Override
    public void clear(Player player) {
        player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));
    }

    @Override
    public void restore(Player player) {
        player.addPotionEffects(this.effects);
    }
}
