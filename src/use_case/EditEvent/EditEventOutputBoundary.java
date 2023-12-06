package use_case.EditEvent;

public interface EditEventOutputBoundary {
    void prepareSucessView(EditEventOutputData outputData);

    void prepareFailView(String error);
}
