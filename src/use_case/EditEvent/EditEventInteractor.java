package use_case.EditEvent;

import entity.*;

import java.util.NoSuchElementException;

public class EditEventInteractor implements EditEventInputBoundary {
    final EditEventDataAccessInterface eventDataAccessObject;
    final EditEventOutputBoundary eventPresenter;
    final DescriptionFactory descriptionFactory;
    final EventFactory eventFactory;

    public EditEventInteractor(EditEventDataAccessInterface eventDataAccessObject,
                               EditEventOutputBoundary eventPresenter,
                               DescriptionFactory descriptionFactory,
                               EventFactory eventFactory) {
        this.eventDataAccessObject = eventDataAccessObject;
        this.eventPresenter = eventPresenter;
        this.descriptionFactory = descriptionFactory;
        this.eventFactory = eventFactory;
    }

    @Override
    public void execute(EditEventInputData editEventInputData){

        HeadItem headItem = eventDataAccessObject.getHeadItem();
        Event item = (Event) headItem.navigate(editEventInputData.getItem());
        try {
            item.changeTime(editEventInputData.getStart(), editEventInputData.getEnd());
            item.getDescription().setDescription(editEventInputData.getDescription());
            item.getDescription().setName(editEventInputData.getName());
            eventDataAccessObject.update(item);
        } catch (NoSuchElementException e){
            eventPresenter.prepareFailView("The event you are trying to edit does not exist");
        }

    }
}
