package myorg.myproject;

import java.util.ArrayList;
import java.util.List;

public class LaterList {
    private List<Item> items;

    public LaterList() {
        this.items = new ArrayList<>();
    }

    public void addItem(String title) {
        Item item = new Item(title);
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }
}
