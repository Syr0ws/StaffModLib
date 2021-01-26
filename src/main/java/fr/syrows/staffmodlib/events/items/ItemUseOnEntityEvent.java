package fr.syrows.staffmodlib.events.items;

import fr.syrows.staffmodlib.staffmod.items.StaffModItem;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemUseOnEntityEvent extends ItemUseEvent {

    private final Entity entity;

    public ItemUseOnEntityEvent(Player player, ItemStack item, StaffModItem staffModItem, int slot, Entity entity) {
        super(player, staffModItem, item, slot);
        this.entity = entity;
    }

    public Entity getEntity() {
        return this.entity;
    }
}
