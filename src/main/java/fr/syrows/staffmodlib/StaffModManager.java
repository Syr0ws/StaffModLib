package fr.syrows.staffmodlib;

import fr.syrows.staffmodlib.events.items.ItemUseEvent;
import fr.syrows.staffmodlib.events.items.ItemUseOnBlockEvent;
import fr.syrows.staffmodlib.events.items.ItemUseOnEntityEvent;
import fr.syrows.staffmodlib.events.staffmod.StaffModDisableEvent;
import fr.syrows.staffmodlib.events.staffmod.StaffModEnableEvent;
import fr.syrows.staffmodlib.exceptions.StaffModException;
import fr.syrows.staffmodlib.staffmod.StaffMod;
import fr.syrows.staffmodlib.staffmod.items.StaffModItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.*;
import java.util.stream.Collectors;

public class StaffModManager {

    private final Map<UUID, StaffMod> playersInStaffMod = new HashMap<>();

    public StaffModManager(Plugin plugin) {

        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new StaffModListeners(), plugin);
    }

    public void setStaffMod(Player player, StaffMod mod) {

        if(this.isInStaffMod(player))
            throw new StaffModException("Player already in staff mod.");

        this.playersInStaffMod.put(player.getUniqueId(), mod);

        Bukkit.getPluginManager().callEvent(new StaffModEnableEvent(player, mod));
    }

    public void removeStaffMod(Player player) {

        if(!this.isInStaffMod(player))
            throw new StaffModException("Player not in staff mod.");

        StaffMod mod = this.getNullableStaffMod(player);

        this.playersInStaffMod.remove(player.getUniqueId());

        Bukkit.getPluginManager().callEvent(new StaffModDisableEvent(player, mod));
    }

    public boolean isInStaffMod(Player player) {
        return this.playersInStaffMod.containsKey(player.getUniqueId());
    }

    public Optional<StaffMod> getStaffMod(Player player) {
        return Optional.ofNullable(this.getNullableStaffMod(player));
    }

    public StaffMod getNullableStaffMod(Player player) {
        return this.playersInStaffMod.getOrDefault(player.getUniqueId(), null);
    }

    public List<Player> getPlayersInStaffMod() {
        return this.playersInStaffMod.keySet().stream()
                .map(Bukkit::getPlayer)
                .collect(Collectors.toList());
    }

    private class StaffModListeners implements Listener {

        @EventHandler
        public void onPlayerInteract(PlayerInteractEvent event) {

            Player player = event.getPlayer();

            // Not in staff mod.
            if(!StaffModManager.this.isInStaffMod(player)) return;

            StaffMod staffMod = StaffModManager.this.getNullableStaffMod(player);

            // Player doesn't hold an item.
            if(!event.hasItem()) return;

            Action action = event.getAction();
            ItemStack item = event.getItem();
            int slot = player.getInventory().getHeldItemSlot();

            StaffModItem staffModItem = this.getStaffModItem(staffMod, slot);

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

            // Player isn't in staff mod.
            if(!StaffModManager.this.isInStaffMod(player)) return;

            StaffMod staffMod = StaffModManager.this.getNullableStaffMod(player);

            Optional<ItemStack> optional = this.getItemInHand(player);

            // Player doesn't hold an item.
            if(!optional.isPresent()) return;

            ItemStack item = optional.get();
            int slot = player.getInventory().getHeldItemSlot();

            StaffModItem staffModItem = this.getStaffModItem(staffMod, slot);

            ItemUseOnEntityEvent itemUseOnEntityEvent = new ItemUseOnEntityEvent(player, item, staffModItem, slot, event.getRightClicked());

            Bukkit.getPluginManager().callEvent(itemUseOnEntityEvent);

            event.setCancelled(itemUseOnEntityEvent.isCancelled());
        }

        private Optional<ItemStack> getItemInHand(Player player) {

            PlayerInventory inventory = player.getInventory();

            int heldItemSlot = inventory.getHeldItemSlot();
            ItemStack item = inventory.getItem(heldItemSlot);

            return item != null && item.getType() != Material.AIR ? Optional.of(item) : Optional.empty();
        }

        private StaffModItem getStaffModItem(StaffMod staffMod, int slot) {

            Optional<StaffModItem> optional = staffMod.getItems()
                    .stream()
                    .filter(modItem -> modItem.getSlot() == slot)
                    .findFirst();

            if(!optional.isPresent())
                throw new NullPointerException("StaffModItem not found.");

            return optional.get();
        }
    }
}
