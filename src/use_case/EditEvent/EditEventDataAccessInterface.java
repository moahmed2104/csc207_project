package use_case.EditEvent;

import entity.HeadItem;
import entity.Item;

public interface EditEventDataAccessInterface {
    void update(Item item);
    HeadItem getHeadItem();


}
