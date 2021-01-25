package fr.syrows.staffmodlib.staffmod.items;

import fr.syrows.staffmodlib.util.Configurable;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public abstract class ConfigurableItem extends StaffModItem implements Configurable {

    private ItemStack item;

    public abstract ConfigurationSection getConfigurationSection(ConfigurationSection parent);

    @Override
    public void configure(ConfigurationSection parent) {

        ConfigurationSection section = this.getConfigurationSection(parent);

        this.item = section.getItemStack("item").clone();
        super.setSlot(section.getInt("slot"));
    }

    @Override
    public ItemStack getItemStack() {
        return this.item.clone();
    }
}
