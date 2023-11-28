package interface_adapter.CreateNewEvent;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class CreateEventViewModel extends ViewModel {
    public final String TITLE_LABEL = "Create Event View";
    public final String NAME_LABEL = "Enter event name:";
    public final String DESCRIPTION_LABEL = "Enter a description:";
    public static final String CREATE_EVENT_LABEL = "Create";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    private CreateEventState state = new CreateEventState();

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    pu
}
