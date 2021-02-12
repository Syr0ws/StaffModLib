package fr.syrows.staffmodlib.bukkit.events.items;

import fr.syrows.staffmodlib.common.items.StaffModItem;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public abstract class StaffModItemEvent extends Event {

    private final Player player;
    private final StaffModItem<ItemStack> staffModItem;
    private final ItemStack item;
    private final int slot;

    public StaffModItemEvent(Player player, StaffModItem<ItemStack> staffModItem, ItemStack item, int slot) {
        this.player = player;
        this.staffModItem = staffModItem;
        this.item = item;
        this.slot = slot;
    }

    public Player getPlayer() {
        return this.player;
    }

    public StaffModItem<ItemStack> getStaffModItem() {
        return this.staffModItem;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public int getSlot() {
        return this.slot;
    }

    public boolean isSimilar(StaffModItem<ItemStack> item) {
        return item.getSlot() == this.slot && item.getItem().isSimilar(this.item);
    }
}
