package entity;
import java.util.List;
public interface Item {
    Description getDescription();
    List<Item>  getSubItem();
    Item getParentItem();
    void addSubItem(Item item);
    Boolean hasSubItem(String address);
    Item findSubItem(String address) throws Exception;
    Item navigate(String address) throws Exception;
}
