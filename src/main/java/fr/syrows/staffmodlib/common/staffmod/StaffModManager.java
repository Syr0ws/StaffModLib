package fr.syrows.staffmodlib.common.staffmod;

import java.util.Collection;
import java.util.Optional;

public interface StaffModManager<T, I> {

    void setStaffMod(T holder, StaffMod<T, I> staffMod);

    void removeStaffMod(T holder);

    boolean hasStaffMod(T holder);

    Optional<StaffMod<T, I>> getStaffMod(T holder);

    Collection<StaffMod<T, I>> getAllHolders();
}
