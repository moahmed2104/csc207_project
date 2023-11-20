package interface_adapter.tasks.create_tasks;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class CreateTaskViewModel extends ViewModel {

    public static final String ADD_BUTTON_LABEL= "Add";
    public static final String TITLE_LABEL = "Creating Task";
    public static final String TASK_NAME = "Task Title";
    public static final String DATE = "Due Date";
    public static final String DESCRIPTION = "Enter a Description";

    public static final String OK_BUTTON = "OK";

    private CreateTaskState state = new CreateTaskState();

    public CreateTaskViewModel() {
        super("create task");
    }

    public void setState(CreateTaskState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CreateTaskState getState() {
        return state;
    }


}
