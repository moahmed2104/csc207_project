package use_case.tasks.edit_tasks;

public class EditTaskOutputData {
    private final boolean success;
    private final String message;

    public EditTaskOutputData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Getters
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}