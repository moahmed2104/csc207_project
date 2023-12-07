package Tasks;

import data_access.TaskRepository;
import interface_adapter.ViewManagerModel;
import interface_adapter.tasks.create_tasks.CreateTaskController;
import interface_adapter.tasks.create_tasks.CreateTaskPresenter;
import interface_adapter.tasks.create_tasks.CreateTaskViewModel;
import interface_adapter.tasks.delete_tasks.DeleteTaskController;
import interface_adapter.tasks.delete_tasks.DeleteTaskPresenter;
import interface_adapter.tasks.edit_tasks.EditTaskController;
import interface_adapter.tasks.edit_tasks.EditTaskPresenter;
import interface_adapter.tasks.edit_tasks.EditTaskViewModel;
import interface_adapter.tasks.task.TaskViewModel;
import use_case.tasks.create_tasks.CreateTaskInteractor;
import use_case.tasks.delete_tasks.DeleteTaskInputData;
import use_case.tasks.delete_tasks.DeleteTaskInteractor;
import use_case.tasks.edit_tasks.EditTaskInputData;
import use_case.tasks.edit_tasks.EditTaskInteractor;
import view.CreateTaskView;
import view.EditTaskView;
import view.TaskView;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CreateTasksTest {
    @org.junit.Test
    public void TasksTests() {
        CreateTaskViewModel createTaskViewModel = new CreateTaskViewModel();
        TaskViewModel taskViewModel = new TaskViewModel();
        EditTaskViewModel editTaskViewModel = new EditTaskViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        TaskView taskView = new TaskView(taskViewModel, createTaskViewModel, editTaskViewModel, viewManagerModel, "all");
        CreateTaskPresenter createTaskPresenter = new CreateTaskPresenter(taskView);
        repo taskRepository = new repo();

        CreateTaskInteractor createTaskInteractor = new CreateTaskInteractor(createTaskPresenter, taskRepository);
        CreateTaskController createTaskController = new CreateTaskController(createTaskInteractor);
        createTaskController.createTask("Test", "10/10/2003", "hmm", "");
        assertEquals(taskRepository.lastSaid, "appended");

        EditTaskPresenter editTaskPresenter = new EditTaskPresenter(taskView);
        //taskView.setTaskDetailsText(("hmm"));

        taskView.setTaskList(new ArrayList<>());

        EditTaskInteractor editTaskInteractor = new EditTaskInteractor(editTaskPresenter,taskRepository, "");
        EditTaskController editTaskController = new EditTaskController(editTaskInteractor);
        editTaskController.editTask("test", "test2", "test3", "test4");

        DeleteTaskPresenter deleteTaskPresenter = new DeleteTaskPresenter(taskView);
        DeleteTaskInteractor deleteTaskInteractor = new DeleteTaskInteractor(deleteTaskPresenter,taskRepository);
        DeleteTaskController deleteTaskController = new DeleteTaskController(deleteTaskInteractor);
        deleteTaskController.deleteTask("test");
        EditTaskView editTaskView = new EditTaskView("","","",editTaskController);
        viewManagerModel.setActiveView(editTaskView.viewName);
        viewManagerModel.firePropertyChanged();
        CreateTaskView createTaskView = new CreateTaskView(createTaskViewModel,createTaskController,"");
        viewManagerModel.setActiveView(createTaskView.viewName);
        viewManagerModel.firePropertyChanged();
    }
    public class repo implements TaskRepository{
        public String lastSaid = "";
        @Override
        public Map<String, String[]> loadTaskDetailsFromCSV(String filePath) {
            return null;
        }

        @Override
        public boolean appendTaskToCSV(String filePath, String title, String date, String description) {
            lastSaid = "appended";
            return false;
        }

        @Override
        public boolean deleteTaskById(String filePath, String taskTitleToDelete) {
            lastSaid = "deleted";
            return false;
        }

        @Override
        public boolean editTaskInCSV(String filePath, String originalTitle, String newTitle, String newDate, String newDescription) {
            lastSaid = "edited";
            return false;
        }
    };
}
