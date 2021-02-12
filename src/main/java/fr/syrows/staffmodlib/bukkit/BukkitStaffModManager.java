package fr.syrows.staffmodlib.bukkit;

import fr.syrows.staffmodlib.bukkit.listener.ListenerFactory;
import fr.syrows.staffmodlib.common.exceptions.StaffModException;
import fr.syrows.staffmodlib.common.staffmod.StaffMod;
import fr.syrows.staffmodlib.common.staffmod.StaffModManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.*;

public class BukkitStaffModManager implements StaffModManager<Player, ItemStack> {

    private final Map<UUID, StaffMod<Player, ItemStack>> playersInStaffMod = new HashMap<>();

    public BukkitStaffModManager(Plugin plugin) {

        Listener listener = ListenerFactory.getListener(this);

        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(listener, plugin);
    }

    @Override
    public void setStaffMod(Player holder, StaffMod<Player, ItemStack> staffMod) {

        UUID uuid = holder.getUniqueId();

        if(this.hasStaffMod(holder))
            throw new StaffModException(String.format("Player with uuid '%s' is already in staff mod.", uuid.toString()));

        this.playersInStaffMod.put(uuid, staffMod);
    }

    @Override
    public void removeStaffMod(Player holder) {

        UUID uuid = holder.getUniqueId();

        if(!this.hasStaffMod(holder))
            throw new StaffModException(String.format("Player with uuid '%s' is not in staff mod.", uuid.toString()));

        this.playersInStaffMod.remove(uuid);
    }

    @Override
    public boolean hasStaffMod(Player holder) {
        return this.playersInStaffMod.containsKey(holder.getUniqueId());
    }

    @Override
    public Optional<StaffMod<Player, ItemStack>> getStaffMod(Player holder) {
        StaffMod<Player, ItemStack> staffMod = this.playersInStaffMod.getOrDefault(holder.getUniqueId(), null);
        return Optional.ofNullable(staffMod);
    }

    @Override
    public Collection<StaffMod<Player, ItemStack>> getAllHolders() {
        return this.playersInStaffMod.values();
    }
}
