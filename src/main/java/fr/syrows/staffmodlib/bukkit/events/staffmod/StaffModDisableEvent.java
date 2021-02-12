package fr.syrows.staffmodlib.bukkit.events.staffmod;

import fr.syrows.staffmodlib.common.staffmod.StaffMod;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class StaffModDisableEvent extends StaffModEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    public StaffModDisableEvent(Player player, StaffMod<Player, ItemStack> staffMod) {
        super(player, staffMod);
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
