package data_access;


import java.util.Map;

    public class TaskRepositoryAdapter implements TaskRepository {

        @Override
        public Map<String, String[]> loadTaskDetailsFromCSV(String filePath) {
            return TaskFileLoader.loadTaskDetailsFromCSV(filePath);
        }

        @Override
        public boolean appendTaskToCSV(String filePath, String title, String date, String description) {
            return TaskFileLoader.appendTaskToCSV(filePath, title, date, description);
        }

        @Override
        public boolean deleteTaskById(String filePath, String taskTitleToDelete) {
            return TaskFileLoader.deleteTaskById(filePath, taskTitleToDelete);
        }
    }

