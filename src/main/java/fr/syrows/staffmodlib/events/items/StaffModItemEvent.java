package fr.syrows.staffmodlib.events.items;

import fr.syrows.staffmodlib.staffmod.items.StaffModItem;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public abstract class StaffModItemEvent extends Event {

    private final Player player;
    private final StaffModItem staffModItem;
    private final ItemStack item;
    private final int slot;

    public StaffModItemEvent(Player player, StaffModItem staffModItem, ItemStack item, int slot) {
        this.player = player;
        this.staffModItem = staffModItem;
        this.item = item;
        this.slot = slot;
    }

    public Player getPlayer() {
        return this.player;
    }

    public StaffModItem getStaffModItem() {
        return this.staffModItem;
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
}
