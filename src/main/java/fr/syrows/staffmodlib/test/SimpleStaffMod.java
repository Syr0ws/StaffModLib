package fr.syrows.staffmodlib.test;

import fr.syrows.staffmodlib.staffmod.AbstractStaffMod;

public class SimpleStaffMod extends AbstractStaffMod {

    @Override
    public void registerModItems() {
        super.registerItem(new VanishItem());
    }
}
