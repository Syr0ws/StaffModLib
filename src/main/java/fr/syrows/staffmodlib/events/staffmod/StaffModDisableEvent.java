package fr.syrows.staffmodlib.events.staffmod;

import fr.syrows.staffmodlib.staffmod.StaffMod;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class StaffModDisableEvent extends StaffModEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    public StaffModDisableEvent(Player player, StaffMod staffmod) {
        super(player, staffmod);
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
