package fr.syrows.staffmodlib.staffmod.items;

import org.bukkit.inventory.ItemStack;

public abstract class StaffModItem {

    private int slot;

    public abstract ItemStack getItemStack();

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return this.slot;
    }
}
