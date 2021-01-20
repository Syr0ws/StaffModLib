package fr.syrows.staffmodlib.data;

import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class PlayerData implements Data {

    private List<Data> data = Arrays.asList(new InventoryData(), new PotionData());

    @Override
    public void save(Player player) {
        this.data.forEach(data -> data.save(player));
    }

    @Override
    public void clear(Player player) {
        this.data.forEach(data -> data.clear(player));
    }

    @Override
    public void restore(Player player) {
        this.data.forEach(data -> data.restore(player));
    }
}
