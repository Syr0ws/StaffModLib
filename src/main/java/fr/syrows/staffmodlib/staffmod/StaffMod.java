package fr.syrows.staffmodlib.staffmod;

import fr.syrows.staffmodlib.data.Data;
import fr.syrows.staffmodlib.events.items.ItemUseEvent;
import fr.syrows.staffmodlib.staffmod.items.StaffModItem;
import org.bukkit.entity.Player;

import java.util.Collection;

public interface StaffMod {

    void registerItems(Player owner);

    void enable(Player player);

    void disable(Player player);

    Collection<StaffModItem> getModItems();

    Data getPlayerData();
}
