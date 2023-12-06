package use_case.tasks.edit_tasks;

import data_access.TaskRepository;
import interface_adapter.tasks.edit_tasks.EditTaskPresenter;
import use_case.tasks.edit_tasks.EditTaskInputData;
import use_case.tasks.edit_tasks.EditTaskOutputData;

public class EditTaskInteractor {
    private final EditTaskPresenter presenter;
    private final TaskRepository taskRepository;
    private final String csvFilePath; // Assuming the file path is provided to the interactor

    public EditTaskInteractor(EditTaskPresenter presenter, TaskRepository taskRepository, String csvFilePath) {
        this.presenter = presenter;
        this.taskRepository = taskRepository;
        this.csvFilePath = csvFilePath;
    }

    public void editTask(EditTaskInputData requestModel) {
        boolean isEdited = taskRepository.editTaskInCSV(
                csvFilePath, // File path
                requestModel.getOriginalTaskId(),  // Original title
                requestModel.getNewTitle(),
                requestModel.getNewDate(),
                requestModel.getNewDescription()
        );
        EditTaskOutputData responseModel = new EditTaskOutputData(
                isEdited,
                isEdited ? "Task edited successfully." : "Failed to edit task."
        );
        presenter.presentEditTaskResponse(responseModel);
    }
}