package app;

import entity.DescriptionFactory;
import entity.EventFactory;
import entity.HeadItem;
import interface_adapter.CreateNewEvent.CreateEventController;
import interface_adapter.CreateNewEvent.CreateEventPresenter;
import interface_adapter.CreateNewEvent.CreateEventViewModel;
import interface_adapter.ViewManagerModel;
import use_case.CreateNewEvent.CreateEventDataAccessInterface;
import use_case.CreateNewEvent.CreateEventInputBoundary;
import use_case.CreateNewEvent.CreateEventInteractor;
import use_case.CreateNewEvent.CreateEventOutputBoundary;
import view.CreateEventView;
import view.CreateView;
import view.TestCreateEventView;

import javax.swing.*;
import java.io.IOException;

public class EventUseCaseFactory {
    private EventUseCaseFactory() {}

    public static CreateEventView create(
            ViewManagerModel viewManagerModel, CreateEventViewModel createEventViewModel, CreateEventDataAccessInterface userDataAccessObject, String parentAddress) {
         try {
            CreateEventController createEventController = createEventUseCase(viewManagerModel, createEventViewModel, userDataAccessObject);
            return new CreateEventView(createEventViewModel, createEventController, parentAddress);
        }catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static CreateEventController createEventUseCase(ViewManagerModel viewManagerModel, CreateEventViewModel createEventViewModel, CreateEventDataAccessInterface userDataAccessObject) throws IOException{
        CreateEventOutputBoundary createEventOutputBoundary = new CreateEventPresenter(viewManagerModel,createEventViewModel);
        DescriptionFactory descriptionFactory = new DescriptionFactory();
        EventFactory eventFactory = new EventFactory();
        HeadItem headItem = new HeadItem(descriptionFactory); // Not sure if this is the best place to initialize this but we will see!
        CreateEventInputBoundary createEventInteractor = new CreateEventInteractor(userDataAccessObject, createEventOutputBoundary, descriptionFactory, eventFactory, headItem);
        return new CreateEventController(createEventInteractor);
    }

}
