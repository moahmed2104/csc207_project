package interface_adapter.tasks.delete_tasks;

import use_case.tasks.delete_tasks.DeleteTaskOutputData;
import view.TaskView;

public class DeleteTaskPresenter {
    private final TaskView view;

    public DeleteTaskPresenter(TaskView view) {
        this.view = view;
    }

    public void presentDeleteTaskResponse(DeleteTaskOutputData responseModel) {
        if (responseModel.isSuccess()) {
            view.displaySuccess(responseModel.getMessage());
            view.refreshTaskList(); // Assuming this method refreshes the task list UI
        } else {
            view.displayError(responseModel.getMessage());
        }
    }
}