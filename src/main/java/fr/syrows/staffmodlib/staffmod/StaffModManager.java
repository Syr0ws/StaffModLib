package fr.syrows.staffmodlib.staffmod;

import fr.syrows.events.ItemUseEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class StaffModManager {

    private Map<UUID, StaffMod> playersInStaffMod = new HashMap<>();

    public void handle(Player player, ItemUseEvent event) {

        Optional<StaffMod> optional = this.getStaffMod(player);

        // TODO Throw an exception.
        if(!optional.isPresent()) return;

        StaffMod mod = optional.get();
        mod.handle(event);
    }

    public void setStaffMod(Player player, StaffMod mod) {

        if(this.isInStaffMod(player)) this.removeStaffMod(player);

        this.playersInStaffMod.put(player.getUniqueId(), mod);

        mod.registerModItems();
        mod.setStaffMod(player);
    }

    public void removeStaffMod(Player player) {

        Optional<StaffMod> optional = this.getStaffMod(player);

        if(!optional.isPresent()) return;

        StaffMod mod = optional.get();
        mod.removeStaffMod(player);

        this.playersInStaffMod.remove(player.getUniqueId());
    }

    public boolean isInStaffMod(Player player) {
        return this.playersInStaffMod.containsKey(player.getUniqueId());
    }

    public Optional<StaffMod> getStaffMod(Player player) {
        return Optional.ofNullable(this.getNullableStaffMod(player));
    }

    public List<Player> getPlayersInStaffMod() {
        return this.playersInStaffMod.keySet().stream()
                .map(Bukkit::getPlayer)
                .collect(Collectors.toList());
    }

    private StaffMod getNullableStaffMod(Player player) {
        return this.playersInStaffMod.getOrDefault(player.getUniqueId(), null);
    }
}
