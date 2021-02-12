package fr.syrows.staffmodlib.bukkit.data;

import fr.syrows.staffmodlib.common.data.DataHandler;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class PlayerDataHandler implements DataHandler<Player> {

    protected List<DataHandler<Player>> data;

    public PlayerDataHandler(List<DataHandler<Player>> data) {
        this.data = data;
    }

    @Override
    public void save(Player player) {
        this.data.forEach(data -> data.save(player));
    }

    @Override
    public void clear(Player player) {
        this.data.forEach(data -> data.clear(player));
    }

    @Override
    public void restore(Player player) {
        this.data.forEach(data -> data.restore(player));
    }

    public List<DataHandler<Player>> getData() {
        return Collections.unmodifiableList(this.data);
    }
}
