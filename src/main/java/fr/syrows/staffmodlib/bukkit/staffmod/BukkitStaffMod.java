package fr.syrows.staffmodlib.bukkit.staffmod;

import fr.syrows.staffmodlib.bukkit.BukkitStaffModManager;
import fr.syrows.staffmodlib.common.items.StaffModItem;
import fr.syrows.staffmodlib.common.staffmod.StaffMod;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public abstract class BukkitStaffMod implements StaffMod<Player, ItemStack> {

    public static final int MAX_ITEMS = 9;

    private final BukkitStaffModManager manager;
    private Player holder;

    public BukkitStaffMod(BukkitStaffModManager manager) {
        this.manager = manager;
    }

    @Override
    public void registerItem(StaffModItem<ItemStack> item) {
        item.onRegister();
    }

    @Override
    public void enable(Player holder) {
        // Do not register items here because they're already registered.
        this.holder = holder;
        this.manager.setStaffMod(holder, this); // Setting in staff mod.
    }

    @Override
    public void disable(Player holder) {
        this.getItemsHeld().forEach(StaffModItem::onUnregister); // Unregistering items.
        this.manager.removeStaffMod(holder); // Removing from staff mod.
        this.holder = null; // Avoiding holder reuse.
    }

    @Override
    public Player getHolder() {
        return this.holder;
    }

    protected void setItems(Player holder) {
        PlayerInventory inventory = holder.getInventory();
        this.getItemsHeld().forEach(item -> inventory.setItem(item.getSlot(), item.getItem()));
    }

    protected void removeItems(Player holder) {
        PlayerInventory inventory = holder.getInventory();
        this.getItemsHeld().forEach(item -> inventory.setItem(item.getSlot(), null));
    }
}
