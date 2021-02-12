package fr.syrows.staffmodlib.bukkit.listener;

import fr.syrows.staffmodlib.bukkit.events.items.ItemUseEvent;
import fr.syrows.staffmodlib.bukkit.events.items.ItemUseOnBlockEvent;
import fr.syrows.staffmodlib.bukkit.events.items.ItemUseOnEntityEvent;
import fr.syrows.staffmodlib.common.items.StaffModItem;
import fr.syrows.staffmodlib.common.staffmod.StaffMod;
import fr.syrows.staffmodlib.common.staffmod.StaffModManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public class LegacyStaffModListener extends BukkitStaffModListener {

    public LegacyStaffModListener(StaffModManager<Player, ItemStack> manager) {
        super(manager);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        StaffModManager<Player, ItemStack> manager = super.getManager();

        // Player doesn't hold an item.
        if(!event.hasItem()) return;

        Optional<StaffMod<Player, ItemStack>> optional = manager.getStaffMod(player);

        // Player is not in staff mod.
        if(!optional.isPresent()) return;

        Action action = event.getAction();
        ItemStack item = event.getItem();
        StaffMod<Player, ItemStack> staffMod = optional.get();

        int slot = player.getInventory().getHeldItemSlot();

        StaffModItem<ItemStack> staffModItem = super.getStaffModItem(staffMod, slot);

        if(action == Action.RIGHT_CLICK_AIR || action == Action.LEFT_CLICK_AIR) {

            ItemUseEvent itemUseEvent = new ItemUseEvent(player, staffModItem, item, slot);

            Bukkit.getPluginManager().callEvent(itemUseEvent);
            event.setCancelled(itemUseEvent.isCancelled());

        } else if(action == Action.RIGHT_CLICK_BLOCK || action == Action.LEFT_CLICK_BLOCK){

            ItemUseOnBlockEvent itemUseEvent = new ItemUseOnBlockEvent(player, item, staffModItem, slot,
                    event.getClickedBlock(), event.getBlockFace(), action);

            Bukkit.getPluginManager().callEvent(itemUseEvent);
            event.setCancelled(itemUseEvent.isCancelled());
        }
    }

    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {

        Player player = event.getPlayer();
        StaffModManager<Player, ItemStack> manager = super.getManager();

        Optional<ItemStack> optionalItem = this.getItemInHand(player);

        // Player doesn't hold an item.
        if(!optionalItem.isPresent()) return;

        Optional<StaffMod<Player, ItemStack>> optional = manager.getStaffMod(player);

        // Player isn't in staff mod.
        if(!optional.isPresent()) return;

        ItemStack item = optionalItem.get();
        int slot = player.getInventory().getHeldItemSlot();

        StaffMod<Player, ItemStack> staffMod = optional.get();
        StaffModItem<ItemStack> staffModItem = this.getStaffModItem(staffMod, slot);

        ItemUseOnEntityEvent itemUseOnEntityEvent = new ItemUseOnEntityEvent(player, item, staffModItem, slot, event.getRightClicked());

        Bukkit.getPluginManager().callEvent(itemUseOnEntityEvent);
        event.setCancelled(itemUseOnEntityEvent.isCancelled());
    }
}
