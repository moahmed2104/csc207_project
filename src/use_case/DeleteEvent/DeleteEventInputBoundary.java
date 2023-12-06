package use_case.DeleteEvent;

import use_case.CreateNewEvent.CreateEventInputData;

public interface DeleteEventInputBoundary {
    void execute(DeleteEventInputData deleteEventInputData);
}
