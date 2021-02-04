package fr.syrows.staffmodlib.staffmod;

import fr.syrows.staffmodlib.StaffModManager;
import fr.syrows.staffmodlib.staffmod.items.AbstractPageItem;
import fr.syrows.staffmodlib.staffmod.items.StaffModItem;
import fr.syrows.staffmodlib.tool.Pagination;
import org.bukkit.entity.Player;
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

        item.onRegister();

        int count = this.pagination.countElements();

        if(count != 0 && count % MAX_ITEMS == 0) {

            int lastIndex = count - 1;
            StaffModItem last = this.pagination.getElements().get(lastIndex);

            this.pagination.removeElement(lastIndex);

            AbstractPageItem next = this.getNextPageItem();
            next.onRegister();

            AbstractPageItem previous = this.getPreviousPageItem();
            previous.onRegister();

            this.pagination.addElement(next);
            this.pagination.addElement(previous);
            this.pagination.addElement(last);

        } else this.pagination.addElement(item);
    }

    @Override
    protected void setItems() {

        PlayerInventory inventory = super.getPlayer().getInventory();

        List<StaffModItem> items = this.getItems();

        for(int i = 0; i < MAX_ITEMS; i++) {

            if(i < items.size()) {

                StaffModItem item = items.get(i);
                item.setSlot(i);

                inventory.setItem(item.getSlot(), item.getItemStack());

            } else inventory.setItem(i, null);
        }
    }

    @Override
    public List<StaffModItem> getItems() {
        return this.page.getElements();
    }

    public List<StaffModItem> getAllItems() {
        return this.pagination.getElements();
    }
}
