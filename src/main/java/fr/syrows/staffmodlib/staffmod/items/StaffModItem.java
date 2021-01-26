package fr.syrows.staffmodlib.staffmod.items;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class StaffModItem {

    private final Player owner;
    private int slot;

    public StaffModItem(Player owner) {
        this.owner = owner;
    }

    public abstract ItemStack getItemStack();

    public void onRegister() {}

    public void onUnregister() {}

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return this.slot;
    }

    public Player getOwner() {
        return this.owner;
    }
}
