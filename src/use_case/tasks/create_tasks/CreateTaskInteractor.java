package use_case.tasks.create_tasks;

import data_access.TaskFileLoader;
import interface_adapter.tasks.create_tasks.CreateTaskPresenter;

public class CreateTaskInteractor {
    private final CreateTaskPresenter presenter;

    public CreateTaskInteractor(CreateTaskPresenter presenter) {
        this.presenter = presenter;
    }

    public void createTask(CreateTaskInputData requestModel) {
        CreateTaskOutputData responseModel = new CreateTaskOutputData();

        // Validate the input data
        if (isValid(requestModel)) {
            // If the input is valid, interact with the data layer to save the task
            boolean isSaved = saveTaskToRepository(requestModel);

            // Create a response model based on the outcome of save operation
            responseModel.setSuccess(isSaved);
            responseModel.setMessage(isSaved ? "Task created successfully." : "Failed to save task.");
        } else {
            // If input validation fails, set the response model accordingly
            responseModel.setSuccess(false);
            responseModel.setMessage("Invalid task data provided.");
        }

        // Present the response using the presenter
        presenter.presentCreateTaskResponse(responseModel);
    }

    private boolean isValid(CreateTaskInputData requestModel) {
        // Implement your validation logic here
        // For example, check if the title or description is empty
        return !requestModel.getTitle().isEmpty() && !requestModel.getDescription().isEmpty();
    }

    private boolean saveTaskToRepository(CreateTaskInputData requestModel) {
        // Implement the logic to save the task to the data repository (CSV, database, etc.)
        // This should return true if the task is saved successfully, false otherwise
        return TaskFileLoader.appendTaskToCSV(
                requestModel.getCsvFilePath(),
                requestModel.getTitle(),
                requestModel.getDate(),
                requestModel.getDescription()
        );
    }
}
