package fr.syrows.staffmodlib.bukkit.items;

import fr.syrows.staffmodlib.bukkit.events.items.ItemUseEvent;
import fr.syrows.staffmodlib.bukkit.events.items.ItemUseOnBlockEvent;
import fr.syrows.staffmodlib.bukkit.events.items.ItemUseOnEntityEvent;
import fr.syrows.staffmodlib.bukkit.events.items.StaffModItemEvent;
import fr.syrows.staffmodlib.common.items.NavigationItem;
import fr.syrows.staffmodlib.common.items.NavigationType;
import fr.syrows.staffmodlib.common.staffmod.PageableStaffMod;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public abstract class BukkitNavigationItem extends BukkitStaffModItem implements NavigationItem<ItemStack> {

    private final Plugin plugin;
    private final PageableStaffMod<Player, ItemStack> staffMod;

    private Listener listener;

    public BukkitNavigationItem(Plugin plugin, PageableStaffMod<Player, ItemStack> staffMod) {
        this.plugin = plugin;
        this.staffMod = staffMod;
    }

    @Override
    public void onRegister() {
        this.listener = new ItemListener();
        Bukkit.getPluginManager().registerEvents(this.listener, this.plugin);
    }

    @Override
    public void onUnregister() {
        HandlerList.unregisterAll(this.listener);
    }

    private class ItemListener implements Listener {

        @EventHandler
        public void onItemUse(ItemUseEvent event) {
            this.handle(event);
        }

        @EventHandler
        public void onItemUseOnBlock(ItemUseOnBlockEvent event) {
            this.handle(event);
        }

        @EventHandler
        public void onItemUseOnEntity(ItemUseOnEntityEvent event) {
            this.handle(event);
        }

        private <T extends StaffModItemEvent & Cancellable> void handle(T event) {

            Player player = event.getPlayer();

            if(!player.equals(BukkitNavigationItem.this.staffMod.getHolder())) return;

            if(!event.getStaffModItem().equals(BukkitNavigationItem.this)) return;

            NavigationType type = BukkitNavigationItem.this.getNavigationType();

            if(type == NavigationType.OPEN_PREVIOUS_PAGE) BukkitNavigationItem.this.staffMod.previousPage();
            else BukkitNavigationItem.this.staffMod.nextPage();

            event.setCancelled(true);
        }
    }
}
