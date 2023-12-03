package interface_adapter.tasks.create_tasks;

public class CreateTaskState {

    private String taskName = "";
    private String description = "";
    private String date = "";

    public CreateTaskState(CreateTaskState copy){
        taskName = copy.taskName;
        description = copy.description;
        date = copy.date;

    }
    public CreateTaskState(){

    }
    public String getCreateTaskName() {
        return taskName;
    }
    public String getCreateTaskDescription() {
        return description;
    }
    public String getCreateTaskDate() {
        return date;
    }
    public void setCreateTaskName(String taskName) {
        this.taskName = taskName;
    }
    public void setCreateTaskDescription(String description) {
        this.description = description;
    }
    public void setCreateTaskDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CreateTaskSate{" +
                "taskName='" + taskName + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                '}';
    }


}
