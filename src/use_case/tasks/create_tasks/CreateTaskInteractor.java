package use_case.tasks.create_tasks;

import data_access.TaskRepository;
import interface_adapter.tasks.create_tasks.CreateTaskPresenter;

public class CreateTaskInteractor {
    private final CreateTaskPresenter presenter;
    private final TaskRepository taskRepository;

    public CreateTaskInteractor(CreateTaskPresenter presenter, TaskRepository taskRepository) {
        this.presenter = presenter;
        this.taskRepository = taskRepository;
    }

    public void createTask(CreateTaskInputData requestModel) {
        CreateTaskOutputData responseModel = new CreateTaskOutputData();

        if (isValid(requestModel)) {
            // Using taskRepository to append the task
            boolean isSaved = taskRepository.appendTaskToCSV(
                    requestModel.getCsvFilePath(),
                    requestModel.getTitle(),
                    requestModel.getDate(),
                    requestModel.getDescription()
            );

            responseModel.setSuccess(isSaved);
            responseModel.setMessage(isSaved ? "Task created successfully." : "Failed to save task.");
        } else {
            responseModel.setSuccess(false);
            responseModel.setMessage("Invalid task data provided.");
        }

        presenter.presentCreateTaskResponse(responseModel);
    }

    private boolean isValid(CreateTaskInputData requestModel) {
        // Implement your validation logic here
        // For example, check if the title or description is empty
        return !requestModel.getTitle().isEmpty() && !requestModel.getDescription().isEmpty();
    }



}
