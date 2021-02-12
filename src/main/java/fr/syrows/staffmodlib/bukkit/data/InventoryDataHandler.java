package fr.syrows.staffmodlib.bukkit.data;

import fr.syrows.staffmodlib.common.data.DataHandler;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class InventoryDataHandler implements DataHandler<Player> {

    private ItemStack[] contents, armors;

    @Override
    public void save(Player player) {
        PlayerInventory inventory = player.getInventory();
        this.contents = inventory.getContents();
        this.armors = inventory.getArmorContents();
    }

    @Override
    public void clear(Player player) {
        PlayerInventory inventory = player.getInventory();
        inventory.setArmorContents(new ItemStack[4]);
        inventory.clear();
    }

    @Override
    public void restore(Player player) {
        PlayerInventory inventory = player.getInventory();
        inventory.setContents(this.contents);
        inventory.setArmorContents(this.armors);
    }
}
