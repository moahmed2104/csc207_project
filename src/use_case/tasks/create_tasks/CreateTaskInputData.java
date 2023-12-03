package use_case.tasks.create_tasks;

public class CreateTaskInputData {
    private final String title;
    private final String date;
    private final String description;
    private final String csvFilePath;

    public CreateTaskInputData(String title, String date, String description, String csvFilePath) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.csvFilePath = csvFilePath;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getCsvFilePath() {
        return csvFilePath;
    }
}

