package fr.syrows.staffmodlib.data;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlayerData implements Data {

    protected List<Data> data;

    public PlayerData(List<Data> data) {
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

    public List<Data> getData() {
        return Collections.unmodifiableList(this.data);
    }
}
