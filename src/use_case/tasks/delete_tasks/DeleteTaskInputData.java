package use_case.tasks.delete_tasks;

public class DeleteTaskInputData {
    private final String taskId;

    public DeleteTaskInputData(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getCsvFilePath() {
        return "src/Tasks.csv";
    }
}
