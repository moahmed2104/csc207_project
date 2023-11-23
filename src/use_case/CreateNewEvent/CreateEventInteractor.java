package use_case.CreateNewEvent;

import entity.Description;
import entity.DescriptionFactory;
import entity.Event;
import entity.EventFactory;

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
    public void execute(CreateEventInputData createEventInputData) throws Exception {
        Description event_description = descriptionFactory.create(
                createEventInputData.getName(),
                createEventInputData.getDescription(),
                createEventInputData.getParent()
        );
        Event event = eventFactory.create(
                event_description,
                createEventInputData.getStart(),
                createEventInputData.getEnd(),
                createEventInputData.getParent().getDescription().getAddress() // Once we figure out a better way to get this address change this
        );
        createEventInputData.getParent().addSubItem(event);

        // todo now that this item has been made add it to our data access layer
    }
}
