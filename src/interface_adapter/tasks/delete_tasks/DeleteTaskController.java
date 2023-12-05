package interface_adapter.tasks.delete_tasks;

import use_case.tasks.delete_tasks.DeleteTaskInputData;
import use_case.tasks.delete_tasks.DeleteTaskInteractor;

public class DeleteTaskController {
    private final DeleteTaskInteractor interactor;

    public DeleteTaskController(DeleteTaskInteractor interactor) {
        this.interactor = interactor;
    }

    public void deleteTask(String taskId) {
        DeleteTaskInputData requestModel = new DeleteTaskInputData(taskId);
        interactor.deleteTask(requestModel);
    }
}