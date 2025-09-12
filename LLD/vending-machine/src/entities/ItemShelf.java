package entities;

import java.util.List;

public class ItemShelf {
    private Integer code;
    private List<Item> items;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<Item> getItems() {
        return items;
    }
}
