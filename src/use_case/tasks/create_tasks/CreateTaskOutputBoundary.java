package use_case.tasks.create_tasks;

import use_case.CreateNewEvent.CreateEventOutputData;

public interface CreateTaskOutputBoundary {
    void prepareSuccessView(CreateTaskOutputData outputData);
    void prepareFailView(String error);

    void presentCreateTaskResponse(CreateTaskOutputData createTaskOutputData);
}
