package app;

import data_access.DummyDataAccess;
import interface_adapter.CreateNewEvent.CreateEventState;
import interface_adapter.CreateNewEvent.CreateEventViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.tasks.create_tasks.CreateTaskViewModel;
import interface_adapter.tasks.task.TaskViewModel;
import view.CreateEventView;
import view.TaskView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("Login Example");
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

        CreateTaskViewModel createTaskViewModel = new CreateTaskViewModel();
        //CreateTaskView createTaskView = new CreateTaskView(createTaskViewModel);

        //views.add(createTaskView, createTaskView.viewName);
        TaskViewModel taskViewModel = new TaskViewModel();
        TaskView taskView = new TaskView(taskViewModel, createTaskViewModel);
        views.add(taskView, taskView.viewName);
        views.setSize(600,600);


        viewManagerModel.setActiveView(taskView.viewName);
        viewManagerModel.firePropertyChanged();
        application.pack();
        application.setVisible(true);

    }
}