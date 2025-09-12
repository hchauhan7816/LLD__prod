package entities;

import enums.ItemTypeEnum;

public class Item {
    private ItemTypeEnum itemType;
    private Integer price;

    public ItemTypeEnum getItemType() {
        return itemType;
    }

    public Integer getPrice() {
        return price;
    }
}
