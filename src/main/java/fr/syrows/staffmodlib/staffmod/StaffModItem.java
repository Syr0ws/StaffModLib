package fr.syrows.staffmodlib.staffmod;

import fr.syrows.staffmodlib.events.ItemUseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.function.Consumer;

public interface StaffModItem {

    void setSlot(int slot);

    int getSlot();

    ItemStack getItemStack();

    Collection<Consumer<? extends ItemUseEvent>> getListeners();
}
