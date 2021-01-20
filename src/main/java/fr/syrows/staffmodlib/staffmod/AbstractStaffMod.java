package fr.syrows.staffmodlib.staffmod;

import fr.syrows.data.Data;
import fr.syrows.data.PlayerData;
import fr.syrows.events.ItemUseEvent;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractStaffMod implements StaffMod {

    private final PlayerData playerData;
    private final List<StaffModItemMetadata> items;

    public AbstractStaffMod() {
        this.playerData = new PlayerData();
        this.items = new ArrayList<>();
    }

    public void registerItem(StaffModItem item) {
        StaffModItemMetadata metadata = new StaffModItemMetadata(item);
        this.items.add(metadata);
    }

    @Override
    public void handle(ItemUseEvent event) {
        this.items.stream()
                .filter(item -> item.getItem().getSlot() == event.getSlot())
                .forEach(item -> item.handle(event));
    }

    @Override
    public void setStaffMod(Player player) {

        this.playerData.save(player);
        this.playerData.clear(player);

        this.getModItems()
                .forEach(item -> player.getInventory().setItem(item.getSlot(), item.getItemStack()));
    }

    @Override
    public void removeStaffMod(Player player) {
        this.playerData.clear(player);
        this.playerData.restore(player);
    }

    @Override
    public Collection<StaffModItem> getModItems() {
        return this.items.stream()
                .map(StaffModItemMetadata::getItem)
                .collect(Collectors.toList());
    }

    @Override
    public Data getPlayerData() {
        return this.playerData;
    }
}
