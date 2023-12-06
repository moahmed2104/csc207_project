package app;

import entity.DescriptionFactory;
import entity.HeadItem;
//import entity.TaskFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.tasks.create_tasks.CreateTaskController;
import interface_adapter.tasks.create_tasks.CreateTaskPresenter;
import interface_adapter.tasks.create_tasks.CreateTaskViewModel;
import interface_adapter.tasks.edit_tasks.EditTaskViewModel;
import interface_adapter.tasks.task.TaskViewModel;
import okhttp3.internal.concurrent.Task;
import use_case.tasks.create_tasks.CreateTaskDataAccessInterace;
import use_case.tasks.create_tasks.CreateTaskInputBoundary;
import use_case.tasks.create_tasks.CreateTaskInteractor;
import use_case.tasks.create_tasks.CreateTaskOutputBoundary;
import view.CreateEventView;
import view.CreateTaskView;
import view.TaskView;

import javax.swing.*;
import java.io.IOException;

/*public class TaskUseCaseFactory {

    private TaskUseCaseFactory() {}

    public static TaskView create(TaskViewModel taskViewModel, CreateTaskViewModel createTaskViewModel,
                                  EditTaskViewModel editTaskViewModel, ViewManagerModel viewManagerModel,
                                  CreateTaskDataAccessInterace userDataAccessObject, String parentAddress){

        try {
            CreateTaskController createTaskController = createTaskUseCase(viewManagerModel, createTaskViewModel, userDataAccessObject);
            return new TaskView(taskViewModel, createTaskViewModel, editTaskViewModel, viewManagerModel, createTaskController,parentAddress);

        }catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");

    }
     return null;


    }
    private static CreateTaskController createTaskUseCase(ViewManagerModel viewManagerModel,
                                                          CreateTaskViewModel createTaskViewModel, CreateTaskDataAccessInterace userDataAccessObject) throws IOException{

        TaskView TaskView = null;
        CreateTaskOutputBoundary createTaskOutputBoundary = new CreateTaskPresenter(viewManagerModel,createTaskViewModel, TaskView);
        DescriptionFactory descriptionFactory = new DescriptionFactory();
        TaskFactory taskFactory = new TaskFactory();
        HeadItem headItem = new HeadItem(descriptionFactory);
        CreateTaskInputBoundary createTaskInteractor = new CreateTaskInteractor(userDataAccessObject, createTaskOutputBoundary, descriptionFactory, taskFactory, headItem);
        return new CreateTaskController(createTaskInteractor);
    }
}*/


