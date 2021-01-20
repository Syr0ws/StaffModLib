package fr.syrows.staffmodlib.staffmod;

import fr.syrows.staffmodlib.util.Configurable;
import org.bukkit.configuration.ConfigurationSection;

public abstract class ConfigurableStaffMod extends AbstractStaffMod implements Configurable {

    @Override
    public void configure(ConfigurationSection section) {
        super.getModItems().stream()
                .filter(item -> item instanceof Configurable)
                .map(item -> (Configurable) item)
                .forEach(configurable -> configurable.configure(section));
    }
}
