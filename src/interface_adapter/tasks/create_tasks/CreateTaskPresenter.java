package interface_adapter.tasks.create_tasks;

import interface_adapter.ViewManagerModel;
import use_case.CreateNewEvent.CreateEventOutputData;
import use_case.tasks.create_tasks.CreateTaskOutputBoundary;
import use_case.tasks.create_tasks.CreateTaskOutputData;
import view.TaskView;

public class CreateTaskPresenter {
    private final TaskView view;

    public CreateTaskPresenter(TaskView view) {
        this.view = view;
    }

    public void presentCreateTaskResponse(CreateTaskOutputData responseModel) {
        if (responseModel.isSuccess()) {
            view.displaySuccess(responseModel.getMessage());
            view.refreshTaskList();
        } else {
            view.displayError(responseModel.getMessage());
        }
    }
}