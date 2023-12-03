package data_access;

import java.util.Map;

public interface TaskRepository {
    Map<String, String[]> loadTaskDetailsFromCSV(String filePath);
    boolean appendTaskToCSV(String filePath, String title, String date, String description);
    boolean deleteTaskById(String filePath, String taskTitleToDelete);
}
