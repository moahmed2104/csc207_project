package use_case.DeleteEvent;

import entity.HeadItem;
import entity.Item;

import java.util.NoSuchElementException;

public class DeleteEventInteractor implements DeleteEventInputBoundary{
    final DeleteEventDataAccessInterface eventDataAccessObject;
    final DeleteEventOutputBoundary eventPresenter;

    public DeleteEventInteractor(DeleteEventDataAccessInterface eventDataAccessObject, DeleteEventOutputBoundary eventPresenter) {
        this.eventDataAccessObject = eventDataAccessObject;
        this.eventPresenter = eventPresenter;
    }

    @Override
    public void execute(DeleteEventInputData deleteEventInputData) {
        HeadItem headItem = eventDataAccessObject.getHeadItem();
        try {
            Item item = headItem.navigate(deleteEventInputData.getItemAddress());
            eventDataAccessObject.delete(item);
            item.getParentItem().getSubItem().remove(item);
            eventPresenter.prepareSucessView();
        } catch (NoSuchElementException e) {
            eventPresenter.prepareFailView("No item at this address");
        }


    }
}
