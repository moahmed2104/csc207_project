package use_case.CreateNewEvent;

import data_access.TaskRepository;
import data_access.TaskRepositoryAdapter;
import entity.*;

import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

public class CreateEventInteractor implements CreateEventInputBoundary{
    final CreateEventDataAccessInterface eventDataAccessObject;
    final CreateEventOutputBoundary eventPresenter;
    final DescriptionFactory descriptionFactory;
    final EventFactory eventFactory;
    TaskRepository taskRepository;

    public CreateEventInteractor(CreateEventDataAccessInterface eventDataAccessObject,
                                 CreateEventOutputBoundary eventPresenter,
                                 DescriptionFactory descriptionFactory,
                                 EventFactory eventFactory) {
        this.eventDataAccessObject = eventDataAccessObject;
        this.eventPresenter = eventPresenter;
        this.descriptionFactory = descriptionFactory;
        this.eventFactory = eventFactory;
        this.taskRepository = new TaskRepositoryAdapter();
    }

    @Override
    public void execute(CreateEventInputData createEventInputData){

        HeadItem headItem = eventDataAccessObject.getHeadItem();
        try {
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
                    createEventInputData.getParent(),
                    headItem

            );
            parent.addSubItem(event);
            eventDataAccessObject.save(event);
            taskRepository.appendTaskToCSV("src/Tasks.csv", "EVENT: " + createEventInputData.getName(), createEventInputData.getStart().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), createEventInputData.getDescription());
            eventPresenter.prepareSucessView(new CreateEventOutputData());
        } catch (Exception e){
            eventPresenter.prepareFailView("woops");
        }

    }
}
