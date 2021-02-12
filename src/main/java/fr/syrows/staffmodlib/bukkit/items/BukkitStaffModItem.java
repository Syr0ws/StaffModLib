package fr.syrows.staffmodlib.bukkit.items;

import fr.syrows.staffmodlib.common.exceptions.StaffModException;
import fr.syrows.staffmodlib.common.items.StaffModItem;
import org.bukkit.inventory.ItemStack;

public abstract class BukkitStaffModItem implements StaffModItem<ItemStack> {

    private int slot;

    @Override
    public void onRegister() {}

    @Override
    public void onUnregister() {}

    @Override
    public void setSlot(int slot) {

        if(slot < 0 || slot >= 9)
            throw new StaffModException("Slot must be between 0 and 8.");

        this.slot = slot;
    }

    @Override
    public int getSlot() {
        return this.slot;
    }
}
