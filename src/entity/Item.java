package entity;
import java.util.List;
public interface Item {
    Description getDescription();
    List<Item>  getSubItem();

}
