package fr.syrows.staffmodlib.staffmod;

import fr.syrows.staffmodlib.staffmod.items.StaffModItem;
import org.bukkit.entity.Player;

import java.util.Collection;

public interface StaffMod {

    void enable(Player player);

    void disable(Player player);

    Collection<StaffModItem> getItems();
}
