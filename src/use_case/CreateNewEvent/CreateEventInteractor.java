package use_case.CreateNewEvent;

import entity.*;

import java.util.NoSuchElementException;

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
    public void execute(CreateEventInputData createEventInputData){
        System.out.println("Success");
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
        } catch (NoSuchElementException e){
            // todo handle this
        }

        // todo now that this item has been made add it to our data access layer
    }
}
