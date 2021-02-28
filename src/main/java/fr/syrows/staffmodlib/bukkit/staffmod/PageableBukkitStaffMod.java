package fr.syrows.staffmodlib.bukkit.staffmod;

import fr.syrows.staffmodlib.bukkit.tool.Pagination;
import fr.syrows.staffmodlib.common.items.NavigationItem;
import fr.syrows.staffmodlib.common.items.StaffModItem;
import fr.syrows.staffmodlib.common.staffmod.PageableStaffMod;
import fr.syrows.staffmodlib.common.staffmod.StaffModManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;

public abstract class PageableBukkitStaffMod extends BukkitStaffMod implements PageableStaffMod<Player, ItemStack> {

    private final Pagination<StaffModItem<ItemStack>> pagination;
    private Pagination<StaffModItem<ItemStack>>.Page page;

    public PageableBukkitStaffMod(StaffModManager<Player, ItemStack> manager) {
        super(manager);

        this.pagination = new Pagination<>(MAX_ITEMS);
        this.page = this.pagination.getPage(1);
    }

    @Override
    public void enable(Player holder) {
        super.enable(holder);
        this.setItems(holder);
    }

    @Override
    public void disable(Player holder) {
        super.disable(holder);
        this.removeItems(holder);
    }

    @Override
    public void registerItem(StaffModItem<ItemStack> item) {

        super.registerItem(item);

        int count = this.pagination.countElements();

        if(count != 0 && count % MAX_ITEMS == 0) {

            int lastIndex = count - 1;
            StaffModItem<ItemStack> last = this.pagination.getElements().get(lastIndex);

            this.pagination.removeElement(lastIndex);

            NavigationItem<ItemStack> next = this.getNext();
            super.registerItem(next);

            NavigationItem<ItemStack> previous = this.getPrevious();
            super.registerItem(previous);

            this.pagination.addElement(next);
            this.pagination.addElement(previous);
            this.pagination.addElement(last);

        } else this.pagination.addElement(item);
    }

    @Override
    public void previousPage() {

        if(!this.pagination.hasPrevious(this.page)) return;

        this.page = this.pagination.getPrevious(this.page);
        this.setItems(super.getHolder());
    }

    @Override
    public void nextPage() {

        if(!this.pagination.hasNext(this.page)) return;

        this.page = this.pagination.getNext(this.page);
        this.setItems(super.getHolder());
    }

    @Override
    public List<StaffModItem<ItemStack>> getItemsHeld() {
        return this.page.getElements();
    }

    @Override
    protected void setItems(Player holder) {

        PlayerInventory inventory = holder.getInventory();

        List<StaffModItem<ItemStack>> items = this.getItemsHeld();

        for(int i = 0; i < MAX_ITEMS; i++) {

            if(i < items.size()) {

                StaffModItem<ItemStack> item = items.get(i);
                item.setSlot(i);

                inventory.setItem(item.getSlot(), item.getItem());

            } else inventory.setItem(i, null);
        }
    }

    protected Pagination<StaffModItem<ItemStack>> getPagination() {
        return this.pagination;
    }
}
