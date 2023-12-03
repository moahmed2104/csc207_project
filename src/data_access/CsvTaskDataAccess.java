/*
package data_access;

import use_case.tasks.create_tasks.CreateTaskInputData;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import use_case.CreateNewEvent.CreateEventDataAccessInterface;
import use_case.tasks.create_tasks.CreateTaskDataAccessInterace;
import use_case.tasks.create_tasks.CreateTaskInputData;

public class CsvTaskDataAccess implements CreateTaskDataAccessInterace{


    private final String csvFilePath;

    public CsvTaskDataAccess(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    @Override
    public void saveTask(CreateTaskInputData taskData) throws IOException {
        // Convert taskData to CSV format
        String taskCsv = convertTaskDataToCsv(taskData);
        // Append the new task to the CSV file
        try (FileWriter fw = new FileWriter(csvFilePath, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(taskCsv);
        }
    }

    @Override
    public List<String> readAllTasks() throws IOException {
        List<String> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                tasks.add(line);
            }
        }
        return tasks;
    }

    private String convertTaskDataToCsv(CreateTaskInputData taskData) {
        // This method should convert your taskData into a CSV formatted string
        // For example:
        return taskData.getTitle() + "," +
                taskData.getDate() + "," +
                taskData.getDescription() + ",";
                //taskData.getParent(); // Make sure to handle commas and newlines in the actual data
    }
}*/
