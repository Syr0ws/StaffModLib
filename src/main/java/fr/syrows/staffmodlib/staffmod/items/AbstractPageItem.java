package fr.syrows.staffmodlib.staffmod.items;

import fr.syrows.staffmodlib.events.items.ItemUseEvent;
import fr.syrows.staffmodlib.events.items.ItemUseOnBlockEvent;
import fr.syrows.staffmodlib.events.items.ItemUseOnEntityEvent;
import fr.syrows.staffmodlib.events.items.StaffModItemEvent;
import fr.syrows.staffmodlib.staffmod.PageableStaffMod;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public abstract class AbstractPageItem extends AbstractStaffModItem {

    private final Plugin plugin;
    private final PageableStaffMod staffMod;

    public AbstractPageItem(Player holder, Plugin plugin, PageableStaffMod staffMod) {
        super(holder);
        this.plugin = plugin;
        this.staffMod = staffMod;
    }

    @Override
    public void onRegister() {
        Bukkit.getPluginManager().registerEvents(new ItemListener(), this.plugin);
    }

    @Override
    public void onUnregister() {
        HandlerList.unregisterAll(this.plugin);
    }

    protected abstract PageItemType getPageItemType();

    public enum PageItemType {

        PREVIOUS, NEXT;
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

            if(!player.equals(AbstractPageItem.this.getHolder())) return;

            if(!event.getStaffModItem().equals(AbstractPageItem.this)) return;

            PageItemType type = AbstractPageItem.this.getPageItemType();

            if(type == PageItemType.PREVIOUS) AbstractPageItem.this.staffMod.previousPage();
            else AbstractPageItem.this.staffMod.nextPage();

            event.setCancelled(true);
        }
    }
}
