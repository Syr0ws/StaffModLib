package fr.syrows.staffmodlib.common.staffmod;

import fr.syrows.staffmodlib.common.items.StaffModItem;

import java.util.Collection;

public interface StaffMod<T, I> {

    void registerItem(StaffModItem<I> item);

    void enable(T holder);

    void disable(T holder);

    T getHolder();

    Collection<StaffModItem<I>> getItemsHeld();
}
