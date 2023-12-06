package use_case.tasks.edit_tasks;

public class EditTaskInputData {
    private final String originalTaskId;
    private final String newTitle;
    private final String newDate;
    private final String newDescription;

    public EditTaskInputData(String originalTaskId, String newTitle, String newDate, String newDescription) {
        this.originalTaskId = originalTaskId;
        this.newTitle = newTitle;
        this.newDate = newDate;
        this.newDescription = newDescription;
    }

    // Getters
    public String getOriginalTaskId() {
        return originalTaskId;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public String getNewDate() {
        return newDate;
    }

    public String getNewDescription() {
        return newDescription;
    }
}
