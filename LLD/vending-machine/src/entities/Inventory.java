package entities;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<ItemShelf> itemShelves;

    public Inventory(Integer shelfCount) {
        this.itemShelves = new ArrayList<>(shelfCount);
    }

    public List<ItemShelf> getInventory() {
        return itemShelves;
    }

    public void initializeInventory(int shelfCount) {
        int startCode = 101;
        for (int i = 0; i < shelfCount; i++) {
            ItemShelf itemShelf = new ItemShelf();
            itemShelf.setCode(startCode++);
            this.itemShelves.add(itemShelf);
        }
    }

    public void addItemShelf(ItemShelf itemShelf) {
        this.itemShelves.add(itemShelf);
    }

}
