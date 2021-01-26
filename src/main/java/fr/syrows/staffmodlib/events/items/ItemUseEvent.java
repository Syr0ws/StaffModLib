package fr.syrows.staffmodlib.events.items;

import fr.syrows.staffmodlib.staffmod.items.StaffModItem;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class ItemUseEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final ItemStack item;
    private final int slot;
    private boolean cancelled;

    public ItemUseEvent(Player player, ItemStack item, int slot) {
        this.player = player;
        this.item = item;
        this.slot = slot;
    }

    public Player getPlayer() {
        return this.player;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public int getSlot() {
        return this.slot;
    }

    public boolean isSimilar(StaffModItem item) {
        return item.getSlot() == this.slot && item.getItemStack().isSimilar(this.item);
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
