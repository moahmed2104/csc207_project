package use_case.CreateNewEvent;

import entity.HeadItem;
import entity.Item;

public interface CreateEventDataAccessInterface {
    void save(Item item);
    HeadItem getHeadItem();


}
