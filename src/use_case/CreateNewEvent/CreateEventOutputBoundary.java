package use_case.CreateNewEvent;

public interface CreateEventOutputBoundary {
    void prepareSucessView(CreateEventOutputData outputData);

    void prepareFailView(String error);
}
