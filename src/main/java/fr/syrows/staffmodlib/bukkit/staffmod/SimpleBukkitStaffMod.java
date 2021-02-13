package fr.syrows.staffmodlib.bukkit.staffmod;

import fr.syrows.staffmodlib.bukkit.BukkitStaffModManager;
import fr.syrows.staffmodlib.common.items.StaffModItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SimpleBukkitStaffMod extends BukkitStaffMod {

    private final List<StaffModItem<ItemStack>> items = new ArrayList<>();

    public SimpleBukkitStaffMod(BukkitStaffModManager manager) {
        super(manager);
    }

    @Override
    public void registerItem(StaffModItem<ItemStack> item) {
        super.registerItem(item);
        this.items.add(item);
    }

    @Override
    public void enable(Player holder) {
        super.enable(holder);
        this.getItemsHeld().forEach(item -> holder.getInventory().setItem(item.getSlot(), item.getItem()));
    }

    @Override
    public void disable(Player holder) {
        super.disable(holder);
        this.getItemsHeld().forEach(item -> holder.getInventory().setItem(item.getSlot(), null));
    }

    @Override
    public Collection<StaffModItem<ItemStack>> getItemsHeld() {
        return Collections.unmodifiableList(this.items);
    }
}
