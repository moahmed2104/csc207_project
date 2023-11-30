package entity;
import java.util.List;
import java.util.NoSuchElementException;

public interface Item {
    Description getDescription();
    List<Item>  getSubItem();
    Item getParentItem();
    void addSubItem(Item item);
    Boolean hasSubItem(String address);
    Item findSubItem(String address) throws NoSuchElementException;
    Item navigate(String address) throws NoSuchElementException;
}
