package fr.syrows.staffmodlib.bukkit.listener;

import fr.syrows.staffmodlib.bukkit.tool.Version;
import fr.syrows.staffmodlib.common.staffmod.StaffModManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class ListenerFactory {

    private static final Version BUKKIT_VERSION = new Version();

    public static Listener getListener(StaffModManager<Player, ItemStack> manager) {
        return BUKKIT_VERSION.isLegacy() ? new LegacyStaffModListener(manager) : new NewStaffModListener(manager);
    }
}
