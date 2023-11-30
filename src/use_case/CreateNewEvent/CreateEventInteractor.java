package use_case.CreateNewEvent;

import entity.*;

public class CreateEventInteractor implements CreateEventInputBoundary{
    final CreateEventDataAccessInterface eventDataAccessObject;
    final CreateEventOutputBoundary eventPresenter;
    final DescriptionFactory descriptionFactory;
    final EventFactory eventFactory;
    final HeadItem headItem;

    public CreateEventInteractor(CreateEventDataAccessInterface eventDataAccessObject,
                                 CreateEventOutputBoundary eventPresenter,
                                 DescriptionFactory descriptionFactory,
                                 EventFactory eventFactory, HeadItem headItem) {
        this.eventDataAccessObject = eventDataAccessObject;
        this.eventPresenter = eventPresenter;
        this.descriptionFactory = descriptionFactory;
        this.eventFactory = eventFactory;
        this.headItem = headItem;
    }

    @Override
    public void execute(CreateEventInputData createEventInputData) throws Exception {
        Item parent = headItem.navigate(createEventInputData.getParent());
        Description event_description = descriptionFactory.create(
                createEventInputData.getName(),
                createEventInputData.getDescription(),
                parent
        );

        Event event = eventFactory.create(
                event_description,
                createEventInputData.getStart(),
                createEventInputData.getEnd(),
                parent.getDescription().getAddress() // Once we figure out a better way to get this address change this
        );
        parent.addSubItem(event);

        // todo now that this item has been made add it to our data access layer
    }
}
