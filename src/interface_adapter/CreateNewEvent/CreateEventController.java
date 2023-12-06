package interface_adapter.CreateNewEvent;

import use_case.CreateNewEvent.CreateEventInputBoundary;
import use_case.CreateNewEvent.CreateEventInputData;

import java.time.LocalDateTime;

public class CreateEventController {
    final CreateEventInputBoundary createEventInteractor;

    public CreateEventController(CreateEventInputBoundary createEventInteractor) {
        this.createEventInteractor = createEventInteractor;
    }

    public void execute(String name, String description, String parentAddress, LocalDateTime start, LocalDateTime end) {
        CreateEventInputData createEventInputData = new CreateEventInputData(start,end,parentAddress, name, description);
        createEventInteractor.execute(createEventInputData);
    }
}
