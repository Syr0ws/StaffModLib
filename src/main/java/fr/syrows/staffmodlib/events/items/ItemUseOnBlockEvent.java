package fr.syrows.staffmodlib.events.items;

import fr.syrows.staffmodlib.staffmod.items.StaffModItem;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

public class ItemUseOnBlockEvent extends StaffModItemEvent implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Block block;
    private final BlockFace face;
    private final Action action;
    private boolean cancelled;

    public ItemUseOnBlockEvent(Player player, ItemStack item, StaffModItem staffModItem, int slot, Block block, BlockFace face, Action action) {
        super(player, staffModItem, item, slot);
        this.block = block;
        this.face = face;
        this.action = action;
    }

    public Block getBlock() {
        return this.block;
    }

    public BlockFace getFace() {
        return this.face;
    }

    public Action getAction() {
        return this.action;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
