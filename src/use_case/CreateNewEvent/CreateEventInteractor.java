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
    public void execute(CreateEventInputData createEventInputData) {
        Description event_description = descriptionFactory.create(createEventInputData.getName(), createEventInputData.getDescription());
        Event event = eventFactory.create(event_description, createEventInputData.getStart(),createEventInputData.getEnd());
        createEventInputData.getParent().addSubItem(event);
    }
}
