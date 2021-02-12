package fr.syrows.staffmodlib.common.staffmod;

import fr.syrows.staffmodlib.common.items.NavigationItem;

public interface PageableStaffMod<T, I> extends StaffMod<T, I> {

    void previousPage();

    void nextPage();

    NavigationItem<I> getPrevious();

    NavigationItem<I> getNext();
}
