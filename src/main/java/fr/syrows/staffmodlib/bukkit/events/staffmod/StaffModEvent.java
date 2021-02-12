package fr.syrows.staffmodlib.bukkit.events.staffmod;
import fr.syrows.staffmodlib.common.staffmod.StaffMod;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public abstract class StaffModEvent extends Event {

    private final Player player;
    private final StaffMod<Player, ItemStack> staffMod;

    public StaffModEvent(Player player, StaffMod<Player, ItemStack> staffMod) {
        this.player = player;
        this.staffMod = staffMod;
    }

    public Player getPlayer() {
        return this.player;
    }

    public StaffMod<Player, ItemStack> getStaffMod() {
        return this.staffMod;
    }
}
