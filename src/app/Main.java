package app;

import data_access.DummyDataAccess;
import interface_adapter.CreateNewEvent.CreateEventViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.tasks.create_tasks.CreateTaskController;
import interface_adapter.tasks.create_tasks.CreateTaskPresenter;
import interface_adapter.tasks.create_tasks.CreateTaskViewModel;
import interface_adapter.tasks.edit_tasks.EditTaskViewModel;
import interface_adapter.tasks.task.TaskViewModel;
import use_case.tasks.create_tasks.CreateTaskInteractor;
import view.CreateEventView;
import view.MainMenuView;
import view.TaskView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("Task Manager");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);
        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        CreateEventViewModel createEventViewModel = new CreateEventViewModel();
        DummyDataAccess userDataAccessObject = new DummyDataAccess();

        CreateEventView createEventView = EventUseCaseFactory.create(viewManagerModel, createEventViewModel, userDataAccessObject, "all");
        views.add(createEventView, createEventView.viewName);

        MainMenuView mainMenuView = new MainMenuView(viewManagerModel);
        views.add(mainMenuView, mainMenuView.viewName);

        CreateTaskViewModel createTaskViewModel = new CreateTaskViewModel();
        //CreateTaskView createTaskView = new CreateTaskView(createTaskViewModel);

        //views.add(createTaskView, createTaskView.viewName);
        TaskViewModel taskViewModel = new TaskViewModel();
        EditTaskViewModel editTaskViewModel = new EditTaskViewModel();
        TaskView taskView = new TaskView(taskViewModel, createTaskViewModel, editTaskViewModel, viewManagerModel, "all");
        CreateTaskPresenter taskPresenter = new CreateTaskPresenter(taskView);
        CreateTaskInteractor taskInteractor = new CreateTaskInteractor(taskPresenter);
        CreateTaskController createTaskController = new CreateTaskController(taskInteractor);
        taskView.setCreateTaskController(createTaskController);
        views.add(taskView, taskView.viewName);
        /*TaskView createtaskView = TaskUseCaseFactory.create(taskViewModel, createTaskViewModel, editTaskViewModel, viewManagerModel,userDataAccessObject, "all");
        views.add(createtaskView, createtaskView.viewName);*/

        //CreateTaskController createTaskController = new CreateTaskController();
        //TaskView taskView = new TaskView(taskViewModel, createTaskViewModel, editTaskViewModel, viewManagerModel, createTaskController, "all");
        //views.add(taskView, taskView.viewName);
        views.setSize(600,600);



        viewManagerModel.setActiveView(mainMenuView.viewName);
        viewManagerModel.firePropertyChanged();
        application.pack();
        application.setVisible(true);

    }
}