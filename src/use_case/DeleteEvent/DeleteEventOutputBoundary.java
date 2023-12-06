package use_case.DeleteEvent;

import use_case.CreateNewEvent.CreateEventOutputData;

public interface DeleteEventOutputBoundary {
    void prepareSucessView();

    void prepareFailView(String error);
}
