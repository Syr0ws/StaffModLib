package fr.syrows.staffmodlib.staffmod;

import fr.syrows.staffmodlib.StaffModManager;
import fr.syrows.staffmodlib.data.PlayerData;
import fr.syrows.staffmodlib.staffmod.items.StaffModItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public abstract class AbstractStaffMod implements StaffMod {

    public static final int MAX_ITEMS = 9;

    private final StaffModManager manager;

    private Player player;
    private PlayerData data;

    public AbstractStaffMod(StaffModManager manager) {
        this.manager = manager;
    }

    public abstract void registerItems(Player player);

    public abstract PlayerData createPlayerData();

    public void registerItem(StaffModItem item) {

        if(item.getSlot() < 0 || item.getSlot() >= MAX_ITEMS)
            throw new IllegalArgumentException("Slot must be between 0 and 8.");

        item.onRegister();
    }

    @Override
    public void enable(Player player) {

        this.player = player;
        this.manager.setStaffMod(player, this);

        this.data = this.createPlayerData();
        this.data.save(player);
        this.data.clear(player);

        this.registerItems(player);
        this.setItems();
    }

    @Override
    public void disable(Player player) {

        this.player = null;
        this.manager.removeStaffMod(player);

        this.getItems().forEach(StaffModItem::onUnregister);

        this.data.clear(player);
        this.data.restore(player);
    }

    protected void setItems() {
        PlayerInventory inventory = this.player.getInventory();
        this.getItems().forEach(item -> inventory.setItem(item.getSlot(), item.getItemStack()));
    }

    public PlayerData getPlayerData() {
        return this.data;
    }

    public Player getPlayer() {
        return this.player;
    }
}
