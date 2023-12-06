package data_access;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TaskFileLoader {


    public static Map<String, String[]> loadTaskDetailsFromCSV(String filePath) {
        Map<String, String[]> taskDetails = new HashMap<>();
        String line;
        String cvsSplitBy = ","; // Assuming CSV values are separated by commas

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] taskDetail = line.split(cvsSplitBy);
                String taskTitle = taskDetail[0];
                String[] subtasks = new String[taskDetail.length - 1];
                System.arraycopy(taskDetail, 1, subtasks, 0, taskDetail.length - 1);
                taskDetails.put(taskTitle, subtasks);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        return taskDetails;
    }
    public static boolean appendTaskToCSV(String filePath, String title, String date, String description) {
        try (FileWriter fw = new FileWriter(filePath, true); // 'true' to append
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(); // Ensure starting on a new line
            out.print(title + " " + "(Date " + date + ")" + "," + description);
            return true; // Return true if write was successful
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Return false if an exception occurred
        }
    }
    public static boolean deleteTaskById(String filePath, String taskTitleToDelete) {
        File inputFile = new File(filePath);
        File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // Trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (trimmedLine.startsWith(taskTitleToDelete + ",")) {
                    // If it's the task to delete, skip this iteration
                    continue;
                }
                // Write the current line if it's not the one to delete
                writer.write(currentLine + System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Delete temp file if something goes wrong
            tempFile.delete();
            return false;
        }

        // Delete the original file
        if (!inputFile.delete()) {
            tempFile.delete();
            return false;
        }

        // Rename the new file to the filename the original file had
        if (!tempFile.renameTo(inputFile)) {
            return false;
        }

        return true;
    }
}

