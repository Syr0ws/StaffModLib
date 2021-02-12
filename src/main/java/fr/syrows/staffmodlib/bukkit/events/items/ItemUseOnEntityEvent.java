package fr.syrows.staffmodlib.bukkit.events.items;

import fr.syrows.staffmodlib.common.items.StaffModItem;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class ItemUseOnEntityEvent extends StaffModItemEvent implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Entity entity;
    private boolean cancelled;

    public ItemUseOnEntityEvent(Player player, ItemStack item, StaffModItem<ItemStack> staffModItem, int slot, Entity entity) {
        super(player, staffModItem, item, slot);
        this.entity = entity;
    }

    public Entity getEntity() {
        return this.entity;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
