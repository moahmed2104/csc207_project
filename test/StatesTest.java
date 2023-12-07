import interface_adapter.CreateNewEvent.CreateEventState;
import interface_adapter.EditEvent.EditEventState;
import interface_adapter.EditEvent.EditEventViewModel;
import interface_adapter.tasks.create_tasks.CreateTaskState;
import interface_adapter.tasks.task.TaskState;

import static org.junit.Assert.assertEquals;

public class StatesTest {
    @org.junit.Test
    public void testCreateEventStatesAreValid(){
        CreateEventState createEventState = new CreateEventState();
        createEventState.setName("Hello");
        createEventState.setDescription("Description");
        createEventState.setStart_date("Today");
        createEventState.setStart_time("now");
        createEventState.setEnd_date("Tomorrow");
        createEventState.setEnd_time("later");
        createEventState.setParentAddress("overthere");
        CreateEventState createEventState1 = new CreateEventState(createEventState);
        assertEquals(createEventState.getName(), createEventState1.getName());
        assertEquals(createEventState.getDescription(), createEventState1.getDescription());
        assertEquals(createEventState.getStart_date(), createEventState1.getStart_date());
        assertEquals(createEventState.getStart_time(), createEventState1.getStart_time());
        assertEquals(createEventState.getEnd_date(), createEventState1.getEnd_date());
        assertEquals(createEventState.getEnd_time(), createEventState1.getEnd_time());
        assertEquals(createEventState.getParentAddress(), createEventState1.getParentAddress());



    }
    @org.junit.Test
    public void testEditEventStatesAreValid(){
        EditEventViewModel editEventViewModel = new EditEventViewModel();
        EditEventState editEventViewModel1 = editEventViewModel.getState();
        editEventViewModel.setState(editEventViewModel1);
        editEventViewModel.firePropertyChanged();
        EditEventState createEventState = new EditEventState();
        createEventState.setName("Hello");
        createEventState.setDescription("Description");
        createEventState.setStart_date("Today");
        createEventState.setStart_time("now");
        createEventState.setEnd_date("Tomorrow");
        createEventState.setEnd_time("later");
        createEventState.setAddress("overthere");
        createEventState.setStart_dateError("hmm");
        createEventState.setEnd_dateError("hmm");
        createEventState.setStart_timeError("hmm");
        createEventState.setEnd_timeError("hmm");
        createEventState.setNameError("noo");
        createEventState.setDescriptionError("nooo");
        EditEventState createEventState1 = new EditEventState(createEventState);
        assertEquals(createEventState.getName(), createEventState1.getName());
        assertEquals(createEventState.getDescription(), createEventState1.getDescription());
        assertEquals(createEventState.getNameError(), createEventState1.getNameError());
        assertEquals(createEventState.getDescriptionError(), createEventState1.getDescriptionError());
        assertEquals(createEventState.getStart_date(), createEventState1.getStart_date());
        assertEquals(createEventState.getStart_time(), createEventState1.getStart_time());
        assertEquals(createEventState.getEnd_date(), createEventState1.getEnd_date());
        assertEquals(createEventState.getEnd_time(), createEventState1.getEnd_time());
        assertEquals(createEventState.getAddress(), createEventState1.getAddress());
        assertEquals(createEventState.getStart_timeError(), createEventState1.getStart_timeError());
        assertEquals(createEventState.getEnd_dateError(), createEventState1.getEnd_dateError());
        assertEquals(createEventState.getEnd_timeError(), createEventState1.getEnd_timeError());
        assertEquals(createEventState.getStart_dateError(), createEventState1.getStart_dateError());
    }
    @org.junit.Test
    public void testTaskStatesAreValid(){
        TaskState createEventState = new TaskState();
        createEventState.setPassword("Hello");
        createEventState.setPasswordError("Description");
        createEventState.setRepeatPassword("Today");
        createEventState.setRepeatPasswordError("now");
        createEventState.setUsername("Tomorrow");
        createEventState.setUsernameError("later");
        TaskState taskState = new TaskState(createEventState);
        assertEquals(createEventState.getPassword(), taskState.getPassword());
        assertEquals(createEventState.getPasswordError(), taskState.getPasswordError());
        assertEquals(createEventState.getRepeatPassword(), taskState.getRepeatPassword());
        assertEquals(createEventState.getRepeatPasswordError(), taskState.getRepeatPasswordError());
        assertEquals(createEventState.getUsername(), taskState.getUsername());
        assertEquals(createEventState.getUsernameError(), taskState.getUsernameError());
        assertEquals(taskState.toString(), "SignupState{username='Tomorrow\', password='Hello\', repeatPassword='Today\'}");

    }
    @org.junit.Test
    public void testCreateTaskStatesAreValid() {
        CreateTaskState createEventState = new CreateTaskState();
        createEventState.setCreateTaskDate("now");
        createEventState.setCreateTaskName("no way");
        createEventState.setCreateTaskDescription("wowo");
        CreateTaskState createTaskState1 = new CreateTaskState(createEventState);
        assertEquals(createEventState.getCreateTaskName(), createTaskState1.getCreateTaskName());
        assertEquals(createEventState.getCreateTaskDate(), createTaskState1.getCreateTaskDate());
        assertEquals(createEventState.getCreateTaskDescription(), createTaskState1.getCreateTaskDescription());
    }

}
