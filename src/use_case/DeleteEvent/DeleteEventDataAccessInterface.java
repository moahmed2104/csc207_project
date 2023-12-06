package use_case.DeleteEvent;

import entity.HeadItem;
import entity.Item;

public interface DeleteEventDataAccessInterface {
    void delete(Item item);
    HeadItem getHeadItem();
}
