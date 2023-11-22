package entity;
import java.util.List;
public interface Item {
    Description getDescription();
    List<Item>  getSubItem();
    Item getParentItem();
    String getAddress();
    void addSubItem(Item item);
    Item navigate(String address);


}
