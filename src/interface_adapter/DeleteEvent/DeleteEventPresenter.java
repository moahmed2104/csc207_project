package interface_adapter.DeleteEvent;

import interface_adapter.CreateNewEvent.CreateEventViewModel;
import interface_adapter.ViewManagerModel;
import use_case.CreateNewEvent.CreateEventOutputBoundary;
import use_case.CreateNewEvent.CreateEventOutputData;
import use_case.DeleteEvent.DeleteEventInputBoundary;
import use_case.DeleteEvent.DeleteEventOutputBoundary;

public class DeleteEventPresenter implements DeleteEventOutputBoundary {

    @Override
    public void prepareSucessView() {

    }

    @Override
    public void prepareFailView(String error) {
        System.out.println("Failure");

    }
}
