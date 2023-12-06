package interface_adapter.tasks.edit_tasks;

// Other imports...

import use_case.tasks.edit_tasks.EditTaskOutputData;
import view.TaskView;

public class EditTaskPresenter {
    private TaskView taskView;

    public EditTaskPresenter(TaskView taskView) {
        this.taskView = taskView;
    }

    public void presentEditTaskResponse(EditTaskOutputData responseModel) {
        if (responseModel.isSuccess()) {
            taskView.displaySuccess(responseModel.getMessage());
            taskView.refreshTaskList();
            taskView.updateTaskListUI();
            //taskView.setTaskDetailsText(taskView.getTaskList().getSelectedValue());
        } else {
            taskView.displayError(responseModel.getMessage());
        }
    }
}
