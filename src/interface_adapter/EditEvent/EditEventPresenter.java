package interface_adapter.EditEvent;

import interface_adapter.ViewManagerModel;
import use_case.CreateNewEvent.CreateEventOutputData;
import use_case.EditEvent.EditEventOutputBoundary;
import use_case.EditEvent.EditEventOutputData;

public class EditEventPresenter implements EditEventOutputBoundary {
    private final EditEventViewModel createEventViewModel;
    private ViewManagerModel viewManagerModel;
    public EditEventPresenter(ViewManagerModel viewManagerModel, EditEventViewModel createEventViewModel){
        this.viewManagerModel = viewManagerModel;
        this.createEventViewModel = createEventViewModel;
    }
    @Override
    public void prepareSucessView(EditEventOutputData outputData) {
        System.out.println("Success");
    }

    @Override
    public void prepareFailView(String error) {
        System.out.println("Failure");

    }
}
