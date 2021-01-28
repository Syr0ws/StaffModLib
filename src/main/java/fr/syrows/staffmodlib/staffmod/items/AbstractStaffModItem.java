package fr.syrows.staffmodlib.staffmod.items;

import org.bukkit.entity.Player;

public abstract class AbstractStaffModItem implements StaffModItem {

    private final Player holder;
    private int slot;

    public AbstractStaffModItem(Player holder) {
        this.holder = holder;
    }

    @Override
    public void onRegister() {}

    @Override
    public void onUnregister() {}

    @Override
    public int getSlot() {
        return this.slot;
    }

    public void setSlot(int slot) {

        if(slot < 0 || slot > 8)
            throw new IllegalArgumentException("Slot must be between 0 and 8.");

        this.slot = slot;
    }

    public Player getHolder() {
        return this.holder;
    }
}
