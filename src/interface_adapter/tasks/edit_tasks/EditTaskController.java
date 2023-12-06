package interface_adapter.tasks.edit_tasks;

import use_case.tasks.edit_tasks.EditTaskInputData;
import use_case.tasks.edit_tasks.EditTaskInteractor;


public class EditTaskController {
    private final EditTaskInteractor interactor;

    public EditTaskController(EditTaskInteractor interactor) {
        this.interactor = interactor;
    }

    public void editTask(String originalTaskId, String newTitle, String newDate, String newDescription) {
        EditTaskInputData requestModel = new EditTaskInputData(originalTaskId, newTitle, newDate, newDescription);
        interactor.editTask(requestModel);

    }
}