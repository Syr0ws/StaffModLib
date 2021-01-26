package fr.syrows.staffmodlib.staffmod;

import fr.syrows.staffmodlib.StaffModManager;
import fr.syrows.staffmodlib.data.Data;
import fr.syrows.staffmodlib.data.PlayerData;
import fr.syrows.staffmodlib.staffmod.items.StaffModItem;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractStaffMod implements StaffMod {

    private final StaffModManager manager;
    private final List<StaffModItem> items = new ArrayList<>();

    private Player player;
    private PlayerData playerData;

    public AbstractStaffMod(StaffModManager manager) {
        this.manager = manager;
    }

    public abstract PlayerData createPlayerData();

    public void registerItem(StaffModItem item) {
        item.onRegister();
        this.items.add(item);
    }

    @Override
    public void enable(Player player) {

        this.player = player;
        this.manager.setStaffMod(player, this);

        this.registerItems(player);

        this.playerData = this.createPlayerData();
        this.playerData.save(player);
        this.playerData.clear(player);

        this.getModItems()
                .forEach(item -> player.getInventory().setItem(item.getSlot(), item.getItemStack()));
    }

    @Override
    public void disable(Player player) {

        this.player = null;
        this.manager.removeStaffMod(player);

        this.items.forEach(StaffModItem::onUnregister);

        this.playerData.clear(player);
        this.playerData.restore(player);
    }

    @Override
    public Collection<StaffModItem> getModItems() {
        return Collections.unmodifiableList(this.items);
    }

    @Override
    public Data getPlayerData() {
        return this.playerData;
    }

    public StaffModManager getStaffModManager() {
        return this.manager;
    }

    public Player getPlayer() {
        return this.player;
    }
}
