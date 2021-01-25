package fr.syrows.staffmodlib.staffmod;

import fr.syrows.staffmodlib.StaffModManager;
import fr.syrows.staffmodlib.staffmod.items.StaffModItem;
import fr.syrows.staffmodlib.util.Configurable;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public abstract class ConfigurableStaffMod extends AbstractStaffMod implements Configurable {

    public ConfigurableStaffMod(StaffModManager manager) {
        super(manager);
    }

    public abstract ConfigurationSection getConfigurationSection();

    @Override
    public void registerItem(StaffModItem item) {

        ConfigurationSection section = this.getConfigurationSection();

        if(section == null)
            throw new NullPointerException("ConfigurationSection cannot be null.");

        this.configure(section);
        super.registerItem(item);
    }

    @Override
    public void configure(ConfigurationSection section) {
        super.getModItems().stream()
                .filter(item -> item instanceof Configurable)
                .map(item -> (Configurable) item)
                .forEach(configurable -> configurable.configure(section));
    }
}
