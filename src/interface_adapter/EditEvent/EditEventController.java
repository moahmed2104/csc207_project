package interface_adapter.EditEvent;

import use_case.CreateNewEvent.CreateEventInputBoundary;
import use_case.CreateNewEvent.CreateEventInputData;
import use_case.EditEvent.EditEventInputBoundary;
import use_case.EditEvent.EditEventInputData;

import java.time.LocalDateTime;

public class EditEventController {
    final EditEventInputBoundary editEventInteractor;

    public EditEventController(EditEventInputBoundary createEventInteractor) {
        this.editEventInteractor = createEventInteractor;
    }

    public void execute(String name, String description, String parentAddress, LocalDateTime start, LocalDateTime end) {
        EditEventInputData editEventInputData = new EditEventInputData(start,end,parentAddress, name, description);
        editEventInteractor.execute(editEventInputData);
    }
}
