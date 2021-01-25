package fr.syrows.staffmodlib.events.staffmod;

import fr.syrows.staffmodlib.staffmod.StaffMod;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public abstract class StaffModEvent extends Event {

    private Player player;
    private StaffMod staffmod;

    public StaffModEvent(Player player, StaffMod staffmod) {
        this.player = player;
        this.staffmod = staffmod;
    }

    public Player getPlayer() {
        return this.player;
    }

    public StaffMod getStaffMod() {
        return this.staffmod;
    }
}
