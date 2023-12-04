package interface_adapter.tasks.create_tasks;

import use_case.tasks.create_tasks.CreateTaskInputData;
import use_case.tasks.create_tasks.CreateTaskInteractor;
import use_case.tasks.create_tasks.CreateTaskInputData;
import use_case.tasks.create_tasks.CreateTaskOutputData;
import view.TaskView;

public class CreateTaskController {
    private final CreateTaskInteractor interactor;

    public CreateTaskController(CreateTaskInteractor interactor) {
        this.interactor = interactor;
    }

    public void createTask(String title, String date, String description, String csvpath) {
        CreateTaskInputData requestModel = new CreateTaskInputData(title, date, description, csvpath);
        interactor.createTask(requestModel);
    }
}