package fr.syrows.staffmodlib.listeners;

import fr.syrows.staffmodlib.staffmod.StaffMod;
import fr.syrows.staffmodlib.staffmod.StaffModManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import java.util.Optional;

public class ItemListeners implements Listener {

    private final Plugin plugin;
    private final StaffModManager manager;

    public ItemListeners(Plugin plugin, StaffModManager manager) {
        this.plugin = plugin;
        this.manager = manager;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        if(this.manager.isInStaffMod(player)) this.manager.removeStaffMod(player);
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent event) {

        Player player = event.getPlayer();

        Optional<StaffMod> optional = this.manager.getStaffMod(player);

        if(!optional.isPresent()) return;

        StaffMod staffmod = optional.get();

        staffmod.getModItems().stream()
                .filter(item -> event.getItemDrop().getItemStack().isSimilar(item.getItemStack()))
                .findFirst()
                .ifPresent(item -> event.setCancelled(true));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();

        if(inventory == null || !inventory.equals(player.getInventory())) return;

        Optional<StaffMod> optional = this.manager.getStaffMod(player);

        if(!optional.isPresent()) return;

        StaffMod staffmod = optional.get();

        staffmod.getModItems().stream()
                .filter(item -> item.getSlot() == event.getSlot() || event.getCurrentItem().isSimilar(item.getItemStack()))
                .findFirst()
                .ifPresent(item -> event.setCancelled(true));
    }

    @EventHandler
    public void onPluginDisable(PluginDisableEvent event) {

        if(event.getPlugin().equals(this.plugin))
            Bukkit.getOnlinePlayers().stream().filter(this.manager::isInStaffMod).forEach(manager::removeStaffMod);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {

        Entity entity = event.getEntity();

        if(!(entity instanceof Player)) return;

        Player player = (Player) entity;

        if(this.manager.isInStaffMod(player)) event.setCancelled(true);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {

        Player player = (Player) event.getEntity();

        if(this.manager.isInStaffMod(player)) event.setCancelled(true);
    }
}
