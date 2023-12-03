package view;

import data_access.TaskFileLoader;
import interface_adapter.ViewManagerModel;
import interface_adapter.tasks.create_tasks.CreateTaskController;
import interface_adapter.tasks.create_tasks.CreateTaskViewModel;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        // Add the button panel to the bottom of the taskDetailsPanel
        //taskDetailsPanel.add(createButtonPanel());

    }

    public void setCreateTaskController(CreateTaskController controller) {
        createTaskController = controller;
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
                // Remove the selected task from the model
                taskListModel.remove(selectedIndex);
            }

        }
        if (evt.getSource().equals(create)) {
            //JOptionPane.showMessageDialog(null,"TEST");
            CreateTaskView test = new CreateTaskView(createTaskViewModel, createTaskController, parentAddress);


        }
        if (evt.getSource().equals(edit)) {
            //JOptionPane.showMessageDialog(null,"TEST");

            EditTaskView test = new EditTaskView(editTaskViewModel);
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
            int index = i; // Need a effectively final variable for the lambda
            checkBox.addActionListener(e -> {
                checkboxStates[index] = checkBox.isSelected(); // Update the state when the checkbox is toggled
            });
            taskDetailsPanel.add(checkBox);
        }

        taskDetailsPanel.revalidate();
        taskDetailsPanel.repaint();
    }

    // ... valueChanged method ...

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            String selectedTask = taskList.getSelectedValue();
            if (selectedTask != null) {
                setTaskDetailsText(selectedTask); // Update to use task name
            }
        }
    }

    public void refreshTaskList() {
        // Read the updated task list from the CSV
        String csvFilePath = "src/Tasks.csv"; // Ensure this is the correct path to your CSV file
        Map<String, String[]> updatedTaskDetails = TaskFileLoader.loadTaskDetailsFromCSV(csvFilePath);

        // Update the dummyTaskDetails with the new details
        dummyTaskDetails.clear();
        dummyTaskDetails.putAll(updatedTaskDetails);

        // Update the JList with the new tasks
        setTaskList(new ArrayList<>(updatedTaskDetails.keySet()));

    }

    public void displaySuccess(String message) {
        System.out.println(message);
    }

    public void displayError(String message) {
        System.out.println(message);
    }


}