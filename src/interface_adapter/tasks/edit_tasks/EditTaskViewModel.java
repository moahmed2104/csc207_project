package interface_adapter.tasks.edit_tasks;
import interface_adapter.ViewModel;
import interface_adapter.tasks.create_tasks.CreateTaskState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class EditTaskViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Editing a Task";
    public static final String TASK_NAME = "Task Title:";
    public static final String DATE = "Date (dd/mm/yyyy):";
    public static final String DESCRIPTION = "Task Description";

    public static final String EDIT_BUTTON = "Edit Task";

    public static final String TAB_TITLE = "Task Editor";

    private EditTaskState state = new EditTaskState();

    public EditTaskViewModel() {
        super("create task");
    }

    public void setState(EditTaskState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public EditTaskState getState() {
        return state;
    }


}
