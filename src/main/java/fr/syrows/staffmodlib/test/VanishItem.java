package fr.syrows.staffmodlib.test;

import fr.syrows.staffmodlib.events.ItemUseEvent;
import fr.syrows.staffmodlib.events.ItemUseOnBlockEvent;
import fr.syrows.staffmodlib.staffmod.AbstractStaffModItem;
import fr.syrows.staffmodlib.util.Configurable;
import fr.syrows.staffmodlib.util.UseEvent;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class VanishItem extends AbstractStaffModItem implements Configurable {

    private boolean vanished;

    @UseEvent
    public void onItemUse(ItemUseEvent event) {
        this.vanish(event.getPlayer());
    }

    @UseEvent
    public void onItemUseOnBlock(ItemUseOnBlockEvent event) {
        this.vanish(event.getPlayer());
    }

    public void vanish(Player player) {
        this.vanished = !this.vanished;
        System.out.println("Player vanish state : " + this.vanished);
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.EYE_OF_ENDER);
    }

    @Override
    public void configure(ConfigurationSection section) {
        System.out.println("Vanish item : configuration");
        this.setSlot(0);
    }
}
