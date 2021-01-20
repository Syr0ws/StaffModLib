package fr.syrows.staffmodlib.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemUseOnEntityEvent extends ItemUseEvent {

    private final Entity entity;

    public ItemUseOnEntityEvent(Player player, ItemStack item, int slot, Entity entity) {
        super(player, item, slot);
        this.entity = entity;
    }

    public Entity getEntity() {
        return this.entity;
    }
}
