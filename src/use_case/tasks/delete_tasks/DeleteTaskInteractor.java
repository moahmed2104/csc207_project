package use_case.tasks.delete_tasks;

import data_access.TaskRepository;
import interface_adapter.tasks.delete_tasks.DeleteTaskPresenter;

public class DeleteTaskInteractor {
    private final DeleteTaskPresenter presenter;
    private final TaskRepository taskRepository; // Use TaskRepository instead of csvFilePath

    public DeleteTaskInteractor(DeleteTaskPresenter presenter, TaskRepository taskRepository) {
        this.presenter = presenter;
        this.taskRepository = taskRepository;
    }

    public void deleteTask(DeleteTaskInputData requestModel) {
        // Using taskRepository to delete the task
        boolean isDeleted = taskRepository.deleteTaskById(requestModel.getCsvFilePath(), requestModel.getTaskId());
        DeleteTaskOutputData responseModel = new DeleteTaskOutputData(
                isDeleted,
                isDeleted ? "Task deleted successfully." : "Failed to delete task."
        );
        presenter.presentDeleteTaskResponse(responseModel);
    }
}