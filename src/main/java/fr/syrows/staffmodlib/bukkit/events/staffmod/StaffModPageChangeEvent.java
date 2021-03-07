package fr.syrows.staffmodlib.bukkit.events.staffmod;

import fr.syrows.staffmodlib.common.staffmod.StaffMod;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class StaffModPageChangeEvent extends StaffModEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final int oldPage, newPage;

    public StaffModPageChangeEvent(Player player, StaffMod<Player, ItemStack> staffMod, int oldPage, int newPage) {
        super(player, staffMod);
        this.oldPage = oldPage;
        this.newPage = newPage;
    }

    public int getOldPage() {
        return this.oldPage;
    }

    public int getNewPage() {
        return this.newPage;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
