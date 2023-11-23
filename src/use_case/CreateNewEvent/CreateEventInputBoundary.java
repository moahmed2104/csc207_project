package use_case.CreateNewEvent;

public interface CreateEventInputBoundary {
    void execute(CreateEventInputData createEventInputData) throws Exception;
}
