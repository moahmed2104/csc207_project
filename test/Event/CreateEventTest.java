package Event;

import app.EventUseCaseFactory;
import app.MainCreateEvent;
import data_access.DummyDataAccess;
import entity.*;
import entity.Event;
import interface_adapter.CreateNewEvent.*;
import interface_adapter.DeleteEvent.DeleteEventController;
import interface_adapter.DeleteEvent.DeleteEventPresenter;
import interface_adapter.ViewManagerModel;
import use_case.CreateNewEvent.CreateEventInputBoundary;
import use_case.CreateNewEvent.CreateEventInteractor;
import use_case.CreateNewEvent.CreateEventOutputBoundary;
import use_case.DeleteEvent.DeleteEventInputBoundary;
import use_case.DeleteEvent.DeleteEventInteractor;
import use_case.DeleteEvent.DeleteEventOutputBoundary;
import view.CreateEventView;

import javax.swing.*;
import java.awt.*;
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
import interface_adapter.CreateNewEvent.CreateEventViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.tasks.create_tasks.CreateTaskViewModel;
import interface_adapter.tasks.edit_tasks.EditTaskViewModel;
import interface_adapter.tasks.task.TaskViewModel;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

import static java.lang.System.out;
import static org.junit.Assert.*;

public class CreateEventTest {
    public JButton getButton(){
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        CreateEventView sv = (CreateEventView) jp2.getComponent(0);

        JPanel buttons = (JPanel) sv.getComponent(7);
        buttons.getComponent(0);

        return (JButton) buttons.getComponent(1);
    }
    @org.junit.Test
    public void testCanClickButton(){
        MainCreateEvent mainCreateEvent = new MainCreateEvent();
        mainCreateEvent.execute();
        JButton button = getButton();
        button.doClick();
    }
    @org.junit.Test
    public void testCreateEvent() {
        DescriptionFactory descriptionFactory = new DescriptionFactory();
        HeadItem headItem = new HeadItem(descriptionFactory);
        DummyDataAccess userDataAccessObject = new DummyDataAccess(headItem);
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CreateEventViewModel createEventViewModel = new CreateEventViewModel();
        CreateEventOutputBoundary createEventOutputBoundary = new CreateEventPresenter(viewManagerModel, createEventViewModel);
        EventFactory eventFactory = new EventFactory();
        CreateEventInputBoundary createEventInteractor = new CreateEventInteractor(userDataAccessObject, createEventOutputBoundary, descriptionFactory, eventFactory);
        new CreateEventController(createEventInteractor).execute("test", "testdescription", "all", LocalDateTime.now(), LocalDateTime.now());
        assertEquals("successfully created Item: " + "name:" + "test" + ", description:" + "testdescription" + ", address:" + "all/test", userDataAccessObject.lastSaid);
        DeleteEventOutputBoundary deleteEventOutputBoundary = new DeleteEventPresenter();
        DeleteEventInputBoundary deleteEventInteractor = new DeleteEventInteractor(userDataAccessObject, deleteEventOutputBoundary);
        new DeleteEventController(deleteEventInteractor).execute("all/test");
        assertEquals("successfully deleted Item: " + "name:" + "test" + ", description:" + "testdescription" + ", address:" + "all/test", userDataAccessObject.lastSaid);
        assertFalse(headItem.hasSubItem("all/test"));
    }
}
