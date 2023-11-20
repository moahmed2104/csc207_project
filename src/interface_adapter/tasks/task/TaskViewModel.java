package interface_adapter.tasks.task;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
public class TaskViewModel extends ViewModel{
    public static final String CREATE_BUTTON_LABEL = "Create";
    public static final String DELETE_BUTTON_LABEL = "Delete";
    public static final String EDIT_BUTTON_LABEL = "Edit";
    public static final String BACK_BUTTON_LABEL = "Back";
    public static final String TITLE_LABEL = "Task View";


    private TaskState state = new TaskState();

    public  void setState(TaskState state) {
        this.state = state;
    }
    public TaskViewModel() {
        super("Tasks");
    }


    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);

    }
    public TaskState getState() {
        return state;
    }
}
