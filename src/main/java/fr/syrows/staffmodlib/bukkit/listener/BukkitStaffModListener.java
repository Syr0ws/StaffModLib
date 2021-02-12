package fr.syrows.staffmodlib.bukkit.listener;

import fr.syrows.staffmodlib.common.exceptions.StaffModException;
import fr.syrows.staffmodlib.common.items.StaffModItem;
import fr.syrows.staffmodlib.common.staffmod.StaffMod;
import fr.syrows.staffmodlib.common.staffmod.StaffModManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Optional;

public class BukkitStaffModListener implements Listener {

    private final StaffModManager<Player, ItemStack> manager;

    public BukkitStaffModListener(StaffModManager<Player, ItemStack> manager) {
        this.manager = manager;
    }

    protected Optional<ItemStack> getItemInHand(Player player) {

        PlayerInventory inventory = player.getInventory();

        int heldItemSlot = inventory.getHeldItemSlot();
        ItemStack item = inventory.getItem(heldItemSlot);

        return item != null && item.getType() != Material.AIR ? Optional.of(item) : Optional.empty();
    }

    protected StaffModItem<ItemStack> getStaffModItem(StaffMod<Player, ItemStack> staffMod, int slot) {

        Optional<StaffModItem<ItemStack>> optional = staffMod.getItemsHeld()
                .stream()
                .filter(modItem -> modItem.getSlot() == slot)
                .findFirst();

        if(!optional.isPresent())
            throw new StaffModException(String.format("No StaffModItem found at slot %d.", slot));

        return optional.get();
    }

    public StaffModManager<Player, ItemStack> getManager() {
        return this.manager;
    }
}
