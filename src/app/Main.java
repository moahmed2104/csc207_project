package app;
import api.Calendar;
import api.GoogleCalendar;
import data_access.DummyDataAccess;
import data_access.SyncDataAccess;
import data_access.TaskRepositoryAdapter;
import entity.DescriptionFactory;
import entity.EventFactory;
import entity.HeadItem;
import interface_adapter.CreateNewEvent.CreateEventViewModel;
import interface_adapter.Sync.SyncController;
import interface_adapter.ViewManagerModel;
import interface_adapter.tasks.create_tasks.CreateTaskController;
import interface_adapter.tasks.create_tasks.CreateTaskPresenter;
import interface_adapter.tasks.create_tasks.CreateTaskViewModel;
import interface_adapter.tasks.delete_tasks.DeleteTaskController;
import interface_adapter.tasks.delete_tasks.DeleteTaskPresenter;
import interface_adapter.tasks.edit_tasks.EditTaskController;
import interface_adapter.tasks.edit_tasks.EditTaskPresenter;
import interface_adapter.tasks.task.TaskViewModel;
import use_case.sync.SyncInteractor;
import use_case.tasks.create_tasks.CreateTaskInteractor;
import use_case.tasks.delete_tasks.DeleteTaskInteractor;
import use_case.tasks.edit_tasks.EditTaskInteractor;
import view.CreateEventView;
import view.TaskView;
import view.ViewManager;

import data_access.DummyDataAccess;
import interface_adapter.CreateNewEvent.CreateEventState;
import interface_adapter.CreateNewEvent.CreateEventViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.tasks.create_tasks.CreateTaskViewModel;
import interface_adapter.tasks.edit_tasks.EditTaskViewModel;
import interface_adapter.tasks.task.TaskViewModel;
import view.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Main Menus");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);



        CreateEventViewModel createEventViewModel = new CreateEventViewModel();
        DescriptionFactory descriptionFactory = new DescriptionFactory();
        HeadItem headItem = new HeadItem(descriptionFactory);
        DummyDataAccess userDataAccessObject = new DummyDataAccess(headItem);


        CreateTaskViewModel createTaskViewModel = new CreateTaskViewModel();
        //CreateTaskView createTaskView = new CreateTaskView(createTaskViewModel);
        TaskRepositoryAdapter taskRepository = new TaskRepositoryAdapter();
        //views.add(createTaskView, createTaskView.viewName);
        TaskViewModel taskViewModel = new TaskViewModel();
        EditTaskViewModel editTaskViewModel = new EditTaskViewModel();
        TaskView taskView = new TaskView(taskViewModel, createTaskViewModel, editTaskViewModel, viewManagerModel, "all");
        CreateTaskPresenter taskPresenter = new CreateTaskPresenter(taskView);
        CreateTaskInteractor taskInteractor = new CreateTaskInteractor(taskPresenter, taskRepository);
        CreateTaskController createTaskController = new CreateTaskController(taskInteractor);
        taskView.setCreateTaskController(createTaskController);
        String csvFilePath = "src/Tasks.csv"; // Replace with your actual CSV file path
        DeleteTaskPresenter deleteTaskPresenter = new DeleteTaskPresenter(taskView);
        DeleteTaskInteractor deleteTaskInteractor = new DeleteTaskInteractor(deleteTaskPresenter, taskRepository);
        DeleteTaskController deleteTaskController = new DeleteTaskController(deleteTaskInteractor);
        EditTaskPresenter editTaskPresenter = new EditTaskPresenter(taskView);
        EditTaskInteractor editTaskInteractor = new EditTaskInteractor(editTaskPresenter, taskRepository, csvFilePath);
        EditTaskController editTaskController = new EditTaskController(editTaskInteractor);
        taskView.setEditTaskController(editTaskController);
        taskView.setEditTaskController(editTaskController);
        taskView.setDeleteTaskController(deleteTaskController);
        views.add(taskView, taskView.viewName);


        CreateEventView createEventView = EventUseCaseFactory.create(viewManagerModel, createEventViewModel, userDataAccessObject, "all");
        views.add(createEventView, createEventView.viewName);

        GoogleCalendar calendar = new GoogleCalendar();
        EventFactory eventFactory = new EventFactory();
        SyncDataAccess syncDataAccess = new SyncDataAccess();
        SyncInteractor syncInteractor = new SyncInteractor(descriptionFactory,eventFactory,userDataAccessObject);
        SyncController syncController = new SyncController(syncInteractor);

        MainMenuView mainMenuView = new MainMenuView(viewManagerModel, taskView, syncController, syncDataAccess, calendar);

        views.add(mainMenuView, mainMenuView.viewName);
        CaesarView start = new CaesarView(viewManagerModel);
        views.add(start,"Caesar View");

        viewManagerModel.setActiveView("Caesar View");
        viewManagerModel.firePropertyChanged();
        application.pack();
        application.setVisible(true);
    }
}

