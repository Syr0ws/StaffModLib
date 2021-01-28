package fr.syrows.staffmodlib.staffmod;

import fr.syrows.staffmodlib.StaffModManager;
import fr.syrows.staffmodlib.staffmod.items.AbstractPageItem;
import fr.syrows.staffmodlib.staffmod.items.StaffModItem;
import fr.syrows.staffmodlib.tool.Pagination;
import org.bukkit.inventory.PlayerInventory;

import java.util.Collection;
import java.util.List;

public abstract class PageableStaffMod extends AbstractStaffMod {

    private final Pagination<StaffModItem> pagination;
    private Pagination<StaffModItem>.Page page;

    public PageableStaffMod(StaffModManager manager) {
        super(manager);

        this.pagination = new Pagination<>(MAX_ITEMS);
        this.page = this.pagination.getPage(1);
    }

    public abstract AbstractPageItem getNextPageItem();

    public abstract AbstractPageItem getPreviousPageItem();

    public void nextPage() {

        if(!this.pagination.hasNext(this.page)) return;

        this.page = this.pagination.getNext(this.page);
        this.setItems();
    }

    public void previousPage() {

        if(!this.pagination.hasPrevious(this.page)) return;

        this.page = this.pagination.getPrevious(this.page);
        this.setItems();
    }

    @Override
    public void registerItem(StaffModItem item) {

        super.registerItem(item);

        int count = this.page.countElements();

        if(count == MAX_ITEMS) {

            StaffModItem last = this.page.getElements().get(MAX_ITEMS - 1);
            this.pagination.removeElement(last);

            this.pagination.addElement(this.getNextPageItem());
            this.pagination.addElement(this.getPreviousPageItem());
            this.pagination.addElement(last);

        } else if(count < MAX_ITEMS) this.pagination.addElement(item);
    }

    @Override
    protected void setItems() {

        PlayerInventory inventory = super.getPlayer().getInventory();

        List<StaffModItem> items = this.page.getElements();

        for(int i = 0; i < MAX_ITEMS; i++) {

            if(i < items.size()) {

                StaffModItem item = items.get(i);

                item.setSlot(i);
                inventory.setItem(item.getSlot(), item.getItemStack());

            } else inventory.setItem(i, null);
        }
    }

    @Override
    public Collection<StaffModItem> getItems() {
        return this.pagination.getElements();
    }
}
