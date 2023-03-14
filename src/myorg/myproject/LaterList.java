package myorg.myproject;

import java.util.ArrayList;
import java.util.List;

public class LaterList {
    private List<Item> items;

    public LaterList() {
        this.items = new ArrayList<>();
    }

    public static List<Item> filterItemsByContentType(List<Item> items, String contentType) {
        List<Item> filteredItems = new ArrayList<>();
        for (Item item : items) {
            if (item.getContentType().equalsIgnoreCase(contentType)) {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }


    public void addItem(String title, String contentType) {
        Item item = new Item(title, contentType);
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public int getSize() {
        return 0;
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }
}
