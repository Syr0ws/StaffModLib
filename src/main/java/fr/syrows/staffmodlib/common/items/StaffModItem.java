package fr.syrows.staffmodlib.common.items;

public interface StaffModItem<I> {

    void onRegister();

    void onUnregister();

    void setSlot(int slot);

    int getSlot();

    I getItem();
}
