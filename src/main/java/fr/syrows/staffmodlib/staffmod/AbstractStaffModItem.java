package fr.syrows.staffmodlib.staffmod;

import fr.syrows.events.ItemUseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class AbstractStaffModItem implements StaffModItem {

    private List<Consumer<? extends ItemUseEvent>> listeners;
    private int slot;

    public AbstractStaffModItem() {
        this.listeners = new ArrayList<>();
    }

    @Override
    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Override
    public int getSlot() {
        return this.slot;
    }

    @Override
    public List<Consumer<? extends ItemUseEvent>> getListeners() {
        return this.listeners;
    }
}
