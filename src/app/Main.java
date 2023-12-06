package app;
import data_access.DummyDataAccess;
import entity.DescriptionFactory;
import entity.HeadItem;
import interface_adapter.CreateNewEvent.CreateEventViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.tasks.create_tasks.CreateTaskViewModel;
import interface_adapter.tasks.task.TaskViewModel;
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


//        CreateTaskViewModel createTaskViewModel = new CreateTaskViewModel();
//        TaskViewModel taskViewModel = new TaskViewModel();
//        EditTaskViewModel editTaskViewModel = new EditTaskViewModel();
//        TaskView taskView = new TaskView(taskViewModel, createTaskViewModel, editTaskViewModel, viewManagerModel);
//        views.add(taskView, taskView.viewName);
//        views.setSize(600,600);
//
//        CreateEventView createEventView = EventUseCaseFactory.create(viewManagerModel, createEventViewModel, userDataAccessObject, "all");
//        views.add(createEventView, createEventView.viewName);
//
//        MainMenuView mainMenuView = new MainMenuView(viewManagerModel);
//        views.add(mainMenuView, mainMenuView.viewName);
//
//        //CreateTaskView createTaskView = new CreateTaskView(createTaskViewModel);
//
//        viewManagerModel.setActiveView(mainMenuView.viewName);
//        viewManagerModel.firePropertyChanged();
//        application.pack();
//        application.setVisible(true);


        CreateEventView createEventView = EventUseCaseFactory.create(viewManagerModel, createEventViewModel, userDataAccessObject, "all");
        views.add(createEventView, createEventView.viewName);

        viewManagerModel.setActiveView(createEventView.viewName);
        viewManagerModel.firePropertyChanged();
        application.pack();
        application.setVisible(true);
    }
}

