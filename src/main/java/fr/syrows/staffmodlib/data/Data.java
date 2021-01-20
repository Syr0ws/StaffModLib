package fr.syrows.staffmodlib.data;

import org.bukkit.entity.Player;

public interface Data {

    void save(Player player);

    void clear(Player player);

    void restore(Player player);
}
