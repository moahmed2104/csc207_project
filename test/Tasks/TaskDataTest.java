package Tasks;


import data_access.TaskRepositoryAdapter;

import static org.junit.Assert.assertTrue;

public class TaskDataTest {

    @org.junit.Test
    public void canAddTasksTest(){
        TaskRepositoryAdapter taskRepositoryAdapter = new TaskRepositoryAdapter();
        taskRepositoryAdapter.appendTaskToCSV("src/Tasks","TestTaske","Tomorrow", "hmm");
        taskRepositoryAdapter.loadTaskDetailsFromCSV("src/Tasks").get("TestTaske");
        taskRepositoryAdapter.editTaskInCSV("src/Tasks","TestTaske","TestTaske1","Now","hmmmm");
        try {
            taskRepositoryAdapter.loadTaskDetailsFromCSV("src/Tasks").get("TestTaske");
        } catch (Exception e){
            assertTrue(true);
            assertTrue(false);
        }
        taskRepositoryAdapter.loadTaskDetailsFromCSV("src/Tasks").get("TestTaske1");
        taskRepositoryAdapter.deleteTaskById("src/Tasks","TestTaske1");
        try {
            taskRepositoryAdapter.loadTaskDetailsFromCSV("src/Tasks").get("TestTaske1");
        } catch (Exception e){
            assertTrue(true);
        }
    }
}
