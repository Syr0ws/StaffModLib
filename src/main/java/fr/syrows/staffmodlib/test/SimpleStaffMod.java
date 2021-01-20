package fr.syrows.staffmodlib.test;

import fr.syrows.staffmod.AbstractStaffMod;

public class SimpleStaffMod extends AbstractStaffMod {

    @Override
    public void registerModItems() {
        super.registerItem(new VanishItem());
    }
}
