package fr.syrows.staffmodlib.events;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

public class ItemUseOnBlockEvent extends ItemUseEvent {

    private final Block block;
    private final BlockFace face;
    private final Action action;

    public ItemUseOnBlockEvent(Player player, ItemStack item, int slot, Block block, BlockFace face, Action action) {
        super(player, item, slot);
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
}
