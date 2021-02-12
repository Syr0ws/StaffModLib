package fr.syrows.staffmodlib.bukkit.events.items;

import fr.syrows.staffmodlib.common.items.StaffModItem;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class ItemUseEvent extends StaffModItemEvent implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private boolean cancelled;

    public ItemUseEvent(Player player, StaffModItem<ItemStack> staffModItem, ItemStack item, int slot) {
        super(player, staffModItem, item, slot);
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
