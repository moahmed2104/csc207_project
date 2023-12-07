package interface_adapter.CreateNewEvent;

import interface_adapter.ViewManagerModel;
import use_case.CreateNewEvent.CreateEventInputBoundary;
import use_case.CreateNewEvent.CreateEventOutputBoundary;
import use_case.CreateNewEvent.CreateEventOutputData;

public class CreateEventPresenter implements CreateEventOutputBoundary {
    private final CreateEventViewModel createEventViewModel;
    private ViewManagerModel viewManagerModel;
    public CreateEventPresenter(ViewManagerModel viewManagerModel, CreateEventViewModel createEventViewModel){
        this.viewManagerModel = viewManagerModel;
        this.createEventViewModel = createEventViewModel;
    }
    @Override
    public void prepareSucessView(CreateEventOutputData outputData) {
        viewManagerModel.setActiveView("Main Menu"); // replace with actual view name
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        viewManagerModel.setActiveView("Main Menu"); // replace with actual view name
        viewManagerModel.firePropertyChanged();

    }
}
