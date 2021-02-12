package fr.syrows.staffmodlib.common.items;

public interface NavigationItem<T> extends StaffModItem<T> {

    NavigationType getNavigationType();
}
