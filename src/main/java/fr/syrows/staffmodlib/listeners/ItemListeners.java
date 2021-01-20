package fr.syrows.staffmodlib.listeners;

import fr.syrows.staffmodlib.events.ItemUseEvent;
import fr.syrows.staffmodlib.events.ItemUseOnBlockEvent;
import fr.syrows.staffmodlib.events.ItemUseOnEntityEvent;
import fr.syrows.staffmodlib.staffmod.StaffModManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Optional;

public class ItemListeners implements Listener {

    private final StaffModManager manager;

    public ItemListeners(StaffModManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        // Not in staff mod.
        if(!this.manager.isInStaffMod(player)) return;

        // Player doesn't hold an item.
        if(!event.hasItem()) return;

        Action action = event.getAction();
        ItemStack item = event.getItem();
        int slot = player.getInventory().getHeldItemSlot();

        ItemUseEvent itemUseEvent;

        if(action == Action.RIGHT_CLICK_AIR || action == Action.LEFT_CLICK_AIR) {

            itemUseEvent = new ItemUseEvent(player, item, slot);

        } else if(action == Action.RIGHT_CLICK_BLOCK || action == Action.LEFT_CLICK_BLOCK){

            itemUseEvent = new ItemUseOnBlockEvent(player, item, slot,
                    event.getClickedBlock(), event.getBlockFace(), action);

        } else return;

        this.manager.handle(player, itemUseEvent);

        event.setCancelled(itemUseEvent.isCancelled());
    }

    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {

        Player player = event.getPlayer();

        // Player isn't in staff mod.
        if(!this.manager.isInStaffMod(player)) return;

        Optional<ItemStack> optional = this.getItemInHand(player);

        // Player doesn't hold an item.
        if(!optional.isPresent()) return;

        ItemStack item = optional.get();
        int slot = player.getInventory().getHeldItemSlot();

        ItemUseOnEntityEvent itemUseOnEntityEvent = new ItemUseOnEntityEvent(player, item, slot, event.getRightClicked());

        this.manager.handle(player, itemUseOnEntityEvent);

        event.setCancelled(itemUseOnEntityEvent.isCancelled());
    }

    private Optional<ItemStack> getItemInHand(Player player) {

        PlayerInventory inventory = player.getInventory();

        int heldItemSlot = inventory.getHeldItemSlot();
        ItemStack item = inventory.getItem(heldItemSlot);

        return item != null && item.getType() != Material.AIR ? Optional.of(item) : Optional.empty();
    }
}
