package view;

import data_access.TaskFileLoader;
import interface_adapter.ViewManagerModel;
import interface_adapter.tasks.create_tasks.CreateTaskController;
import interface_adapter.tasks.create_tasks.CreateTaskViewModel;
import interface_adapter.tasks.delete_tasks.DeleteTaskController;
import interface_adapter.tasks.edit_tasks.EditTaskController;
import interface_adapter.tasks.edit_tasks.EditTaskViewModel;
import interface_adapter.tasks.task.TaskViewModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Arrays;

public class TaskView extends JPanel implements ActionListener, PropertyChangeListener, ListSelectionListener {
    public final String viewName = "Task Viewer";

    private ViewManagerModel viewManagerModel;
    private final TaskViewModel taskViewModel;
    private final CreateTaskViewModel createTaskViewModel;
    private final EditTaskViewModel editTaskViewModel;

    private final JButton create;
    private final JButton delete;
    private final JButton edit;
    private final JButton back;
    private final JSplitPane splitPane;

    private final JList<String> taskList;
    private final DefaultListModel<String> taskListModel;
    private final JPanel taskDetailsPanel;
    private final JScrollPane taskDetailsScrollPane;

    private Map<String, String[]> dummyTaskDetails;
    private Map<String, boolean[]> taskCheckboxStates;
    private final String parentAddress;

    private CreateTaskController createTaskController;
    private DeleteTaskController deleteTaskController;

    private EditTaskController editTaskController;


    public TaskView(TaskViewModel taskViewModel, CreateTaskViewModel createTaskViewModel,
                    EditTaskViewModel editTaskViewModel, ViewManagerModel viewManagerModel,
                    String parentaddress) {
        this.taskViewModel = taskViewModel;
        this.createTaskViewModel = createTaskViewModel;
        this.taskViewModel.addPropertyChangeListener(this);
        this.editTaskViewModel = editTaskViewModel;
        this.viewManagerModel = viewManagerModel;

        this.parentAddress = parentaddress;
        this.setPreferredSize(new Dimension(600, 600));
        this.setLayout(new BorderLayout());

        // Title label
        JLabel title = new JLabel(TaskViewModel.TITLE_LABEL);
        title.setHorizontalAlignment(JLabel.CENTER);

        // Buttons panel
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        create = new JButton(TaskViewModel.CREATE_BUTTON_LABEL);
        delete = new JButton(TaskViewModel.DELETE_BUTTON_LABEL);
        edit = new JButton(TaskViewModel.EDIT_BUTTON_LABEL);
        back = new JButton(TaskViewModel.BACK_BUTTON_LABEL);

        buttons.add(create);
        buttons.add(delete);
        buttons.add(edit);
        buttons.add(back);
        taskCheckboxStates = new HashMap<>();

        // Top panel that includes the title and buttons
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(title);
        topPanel.add(buttons);

        // Task list model and list
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskList.addListSelectionListener(this);

        // Task details panel
        taskDetailsPanel = new JPanel();
        taskDetailsPanel.setLayout(new BoxLayout(taskDetailsPanel, BoxLayout.Y_AXIS));
        taskDetailsScrollPane = new JScrollPane(taskDetailsPanel);
        taskDetailsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        taskDetailsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Dummy task details initialization
        //initializeDummyTaskDetails();

        // Split pane
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(taskList), taskDetailsScrollPane);
        splitPane.setDividerLocation(250);

        // Add components to the panel
        this.add(topPanel, BorderLayout.NORTH);
        this.add(splitPane, BorderLayout.CENTER);

        // Set the dummy tasks
        String csvFilePath = "src/Tasks.csv";
        this.dummyTaskDetails = TaskFileLoader.loadTaskDetailsFromCSV(csvFilePath);
        setTaskList(new ArrayList<>(dummyTaskDetails.keySet()));

        // Register action listeners for buttons
        create.addActionListener(this);
        delete.addActionListener(this);
        edit.addActionListener(this);
        back.addActionListener(this);
        //refreshTaskDetailsPanel();

        // Add the button panel to the bottom of the taskDetailsPanel
        //taskDetailsPanel.add(createButtonPanel());

    }

    public void setCreateTaskController(CreateTaskController controller) {
        createTaskController = controller;
    }
    public void setDeleteTaskController(DeleteTaskController controller) {
        deleteTaskController = controller;
    }

    public void setEditTaskController(EditTaskController controller) {
        this.editTaskController = controller;
    }

    public void setTaskList(java.util.List<String> tasks) {
        taskListModel.clear();
        for (String task : tasks) {
            taskListModel.addElement(task);
        }
    }

    public void setTaskDetailsText(String[] subtasks) {
        taskDetailsPanel.removeAll(); // Clear the existing subtasks
        for (String subtask : subtasks) {
            JCheckBox checkBox = new JCheckBox(subtask);
            taskDetailsPanel.add(checkBox);
        }
        taskDetailsPanel.revalidate();
        taskDetailsPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        if (source == delete) {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                String selectedTask = taskList.getSelectedValue();
                // Now we trigger the delete operation using the delete controller
                if (deleteTaskController != null) {
                    deleteTaskController.deleteTask(selectedTask);
                }
            }

        }
        if (evt.getSource().equals(create)) {
            //JOptionPane.showMessageDialog(null,"TEST");
            CreateTaskView test = new CreateTaskView(createTaskViewModel, createTaskController, parentAddress);


        }
        if (evt.getSource().equals(edit)) {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                String selectedTaskTitle = taskList.getSelectedValue();
                if (selectedTaskTitle != null && editTaskController != null) {
                    // Extract title and date using regex
                    Pattern pattern = Pattern.compile("(.*) \\(Date (\\d{2}/\\d{2}/\\d{4})\\)");
                    Matcher matcher = pattern.matcher(selectedTaskTitle);
                    if (matcher.find()) {
                        String title = matcher.group(1);
                        String date = matcher.group(2);
                        String[] details = dummyTaskDetails.get(selectedTaskTitle);
                        // Combine the subtasks into a single description string
                        String description = String.join(", ", Arrays.copyOfRange(details, 0, details.length));
                        // Open the EditTaskView with the selected task's details
                        EditTaskView editView = new EditTaskView(title, date, description, editTaskController);
                        editView.setVisible(true);
                    }
                }
            }
        }
        if (evt.getSource().equals(back)) {
            //JOptionPane.showMessageDialog(null,"TEST");

            viewManagerModel.setActiveView("Main Menu"); // replace with actual view name
            viewManagerModel.firePropertyChanged();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Implement actual property change logic here
    }



    private String[] getSubtasksForTask(String taskTitle) {
        // Replace with actual logic to fetch task details
        return dummyTaskDetails.getOrDefault(taskTitle, new String[]{});
    }




    /*public void refreshTaskDetailsPanel(String taskTitle) {
        // Get the updated task details
        String[] subtasks = dummyTaskDetails.get(taskTitle);

        // Clear the existing subtasks
        taskDetailsPanel.removeAll();

        // Add new checkboxes for each subtask
        for (String subtask : subtasks) {
            taskDetailsPanel.add(new JCheckBox(subtask));
        }

        // Update the UI
        taskDetailsPanel.revalidate();
        taskDetailsPanel.repaint();
    }*/

    public void setTaskDetailsText(String task) {
        // Get the current state array, or create a new one if none exists
        boolean[] checkboxStates = taskCheckboxStates.computeIfAbsent(task, k -> {
            String[] subtasks = dummyTaskDetails.get(task);
            return new boolean[subtasks.length]; // Default to all false
        });

        // Remove any existing checkboxes
        taskDetailsPanel.removeAll();

        // Add checkboxes for the task
        String[] subtasks = dummyTaskDetails.get(task);
        for (int i = 0; i < subtasks.length; i++) {
            JCheckBox checkBox = new JCheckBox(subtasks[i], checkboxStates[i]);
            int index = i;
            checkBox.addActionListener(e -> {
                checkboxStates[index] = checkBox.isSelected(); // Update the state when the checkbox is toggled
            });
            taskDetailsPanel.add(checkBox);
        }

        taskDetailsPanel.revalidate();
        taskDetailsPanel.repaint();
    }



    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            String selectedTask = taskList.getSelectedValue();
            if (selectedTask != null) {
                setTaskDetailsText(dummyTaskDetails.get(selectedTask)); // Update to use task name
            }
        }
    }

    public void refreshTaskList() {
        String csvFilePath = "src/Tasks.csv"; // Ensure this is the correct path to your CSV file
        Map<String, String[]> updatedTaskDetails = TaskFileLoader.loadTaskDetailsFromCSV(csvFilePath);
        dummyTaskDetails.clear();
        dummyTaskDetails.putAll(updatedTaskDetails);
        setTaskList(new ArrayList<>(updatedTaskDetails.keySet()));
    }
    public void refreshTaskDetailsPanel(String taskTitle) {
        String[] subtasks = dummyTaskDetails.get(taskTitle);
        setTaskDetailsText(subtasks);
    }




    public JList<String> getTaskList() {
        return this.taskList;
    }

    public void displaySuccess(String message) {
        System.out.println(message);
    }

    public void displayError(String message) {
        System.out.println(message);
    }
    public void updateTaskListUI() {
        // Read the updated task list from the CSV
        refreshTaskList();

        // Now update the UI based on the new data
        // For example, if you're updating or adding a task, you might need to
        // refresh the entire list or add a new element to the list model
        taskListModel.clear();
        for (String task : dummyTaskDetails.keySet()) {
            taskListModel.addElement(task);
        }
    }


}