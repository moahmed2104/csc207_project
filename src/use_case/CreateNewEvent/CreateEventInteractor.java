package use_case.CreateNewEvent;

import entity.*;

import java.util.NoSuchElementException;

public class CreateEventInteractor implements CreateEventInputBoundary{
    final CreateEventDataAccessInterface eventDataAccessObject;
    final CreateEventOutputBoundary eventPresenter;
    final DescriptionFactory descriptionFactory;
    final EventFactory eventFactory;

    public CreateEventInteractor(CreateEventDataAccessInterface eventDataAccessObject,
                                 CreateEventOutputBoundary eventPresenter,
                                 DescriptionFactory descriptionFactory,
                                 EventFactory eventFactory) {
        this.eventDataAccessObject = eventDataAccessObject;
        this.eventPresenter = eventPresenter;
        this.descriptionFactory = descriptionFactory;
        this.eventFactory = eventFactory;
    }

    @Override
    public void execute(CreateEventInputData createEventInputData){

        HeadItem headItem = eventDataAccessObject.getHeadItem();
        Item parent = headItem.navigate(createEventInputData.getParent());
        Description event_description = descriptionFactory.create(
                createEventInputData.getName(),
                createEventInputData.getDescription(),
                parent
        );
        try {
            Event event = eventFactory.create(
                    event_description,
                    createEventInputData.getStart(),
                    createEventInputData.getEnd(),
                    createEventInputData.getParent(),
                    headItem

            );
            parent.addSubItem(event);
            eventDataAccessObject.save(event);
        } catch (NoSuchElementException e){
            // todo handle this
        }

    }
}
