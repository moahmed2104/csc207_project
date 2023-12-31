package interface_adapter.EditEvent;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EditEventViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Create an Event";
    public static final String NAME_LABEL = "Name:";
    public static final String DESCRIPTION_LABEL = "Description:";

    public static final String START_DATE_LABEL = "Starting date:";
    public static final String START_TIME_LABEL = "Starting time";
    public static final String END_DATE_LABEL = "Ending date:";
    public static final String END_TIME_LABEL = "Ending time:";
    public static final String CREATE_EVENT_LABEL = "Create";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    private EditEventState state = new EditEventState();

    public EditEventViewModel() {super("Create Event");}
    public void setState(EditEventState state) {this.state = state;}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public EditEventState getState(){
        return this.state;
    }

}
