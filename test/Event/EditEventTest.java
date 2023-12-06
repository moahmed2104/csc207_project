package Event;

import app.MainCreateEvent;
import data_access.DummyDataAccess;
import entity.*;
import entity.Event;
import interface_adapter.CreateNewEvent.CreateEventController;
import interface_adapter.CreateNewEvent.CreateEventPresenter;
import interface_adapter.CreateNewEvent.CreateEventViewModel;
import interface_adapter.EditEvent.EditEventController;
import interface_adapter.EditEvent.EditEventPresenter;
import interface_adapter.EditEvent.EditEventViewModel;
import interface_adapter.ViewManagerModel;
import use_case.CreateNewEvent.CreateEventInputBoundary;
import use_case.CreateNewEvent.CreateEventInteractor;
import use_case.CreateNewEvent.CreateEventOutputBoundary;
import use_case.EditEvent.EditEventInputBoundary;
import use_case.EditEvent.EditEventInteractor;
import use_case.EditEvent.EditEventOutputBoundary;
import view.CreateEventView;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EditEventTest {
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
    public void filloutBoxes(){
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

        for (int i = 1; i<7; i++){
            JPanel panel = (JPanel) sv.getComponent(i);
            JTextField field = (JTextField) panel.getComponent(1);
            field.getActionListeners();

        }


    }
    @org.junit.Test
    public void testCreateTask() {
        DescriptionFactory descriptionFactory = new DescriptionFactory();
        HeadItem headItem = new HeadItem(descriptionFactory);
        DummyDataAccess userDataAccessObject = new DummyDataAccess(headItem);
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        EditEventViewModel editEventViewModel = new EditEventViewModel();
        EditEventOutputBoundary editEventOutputBoundary = new EditEventPresenter(viewManagerModel, editEventViewModel);
        EventFactory eventFactory = new EventFactory();
        EditEventInputBoundary editEventInteractor = new EditEventInteractor(userDataAccessObject, editEventOutputBoundary, descriptionFactory, eventFactory);
        userDataAccessObject.headItem.addSubItem(new Event(new Description("test","test","testdescription","all/test"),LocalDateTime.now(), LocalDateTime.now(), userDataAccessObject.headItem));
        new EditEventController(editEventInteractor).execute("test1", "testdescription1", "all/test", LocalDateTime.now(), LocalDateTime.now());

        assertEquals("successfully Updated Item: " + "name:" + "test1" + ", description:" + "testdescription1" + ", address:" + "all/test", userDataAccessObject.lastSaid);
    }
}
