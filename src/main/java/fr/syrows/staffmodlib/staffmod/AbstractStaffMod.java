package fr.syrows.staffmodlib.staffmod;

import fr.syrows.staffmodlib.StaffModManager;
import fr.syrows.staffmodlib.data.Data;
import fr.syrows.staffmodlib.data.PlayerData;
import fr.syrows.staffmodlib.events.items.ItemUseEvent;
import fr.syrows.staffmodlib.staffmod.items.StaffModItem;
import fr.syrows.staffmodlib.staffmod.items.StaffModItemMetadata;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractStaffMod implements StaffMod {

    private final StaffModManager manager;
    private final List<StaffModItemMetadata> items = new ArrayList<>();

    private PlayerData playerData;

    public AbstractStaffMod(StaffModManager manager) {
        this.manager = manager;
    }

    public abstract PlayerData createPlayerData();

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

        this.manager.setStaffMod(player, this);

        this.playerData = this.createPlayerData();
        this.playerData.save(player);
        this.playerData.clear(player);

        this.getModItems()
                .forEach(item -> player.getInventory().setItem(item.getSlot(), item.getItemStack()));
    }

    @Override
    public void removeStaffMod(Player player) {

        this.manager.removeStaffMod(player);

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

    public StaffModManager getStaffModManager() {
        return this.manager;
    }
}
