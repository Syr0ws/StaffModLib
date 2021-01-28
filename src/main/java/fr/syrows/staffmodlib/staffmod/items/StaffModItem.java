package fr.syrows.staffmodlib.staffmod.items;

import org.bukkit.inventory.ItemStack;

public interface StaffModItem {

    void onRegister();

    void onUnregister();

    int getSlot();

    void setSlot(int slot);

    ItemStack getItemStack();
}
