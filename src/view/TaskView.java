package view;

import interface_adapter.tasks.create_tasks.CreateTaskViewModel;
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

public class TaskView extends JPanel implements ActionListener, PropertyChangeListener, ListSelectionListener {
    public final String viewName = "Task Viewer";
    private final TaskViewModel taskViewModel;
    private final CreateTaskViewModel createTaskViewModel;

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

    public TaskView(TaskViewModel taskViewModel, CreateTaskViewModel createTaskViewModel) {
        this.taskViewModel = taskViewModel;
        this.createTaskViewModel = createTaskViewModel;
        this.taskViewModel.addPropertyChangeListener(this);

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
        initializeDummyTaskDetails();

        // Split pane
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(taskList), taskDetailsScrollPane);
        splitPane.setDividerLocation(250);

        // Add components to the panel
        this.add(topPanel, BorderLayout.NORTH);
        this.add(splitPane, BorderLayout.CENTER);

        // Set the dummy tasks
        setTaskList(new ArrayList<>(dummyTaskDetails.keySet()));

        // Register action listeners for buttons
        create.addActionListener(this);
        delete.addActionListener(this);
        edit.addActionListener(this);
        back.addActionListener(this);

        // Add the button panel to the bottom of the taskDetailsPanel
        taskDetailsPanel.add(createButtonPanel());

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
            CreateTaskView test = new CreateTaskView(createTaskViewModel);
            test.show();}
        // Handle other button clicks here
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Implement actual property change logic here
    }



    private String[] getSubtasksForTask(String taskTitle) {
        // Replace with actual logic to fetch task details
        return dummyTaskDetails.getOrDefault(taskTitle, new String[]{});
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton deleteButton = new JButton("Delete");
        JButton editButton = new JButton("Edit");

        deleteButton.addActionListener(this);
        editButton.addActionListener(this);

        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);

        return buttonPanel;
    }

    private void initializeDummyTaskDetails() {
        dummyTaskDetails = new HashMap<>();
        dummyTaskDetails.put("Tie shoes (assigned to Paul, target completion date 07/11/2019, priority 3)",
                new String[]{"1. Retrieve shoes", "2. Undo laces on left shoe", "3. Undo laces on right shoe",
                        "4. Put left shoe on left foot", "5. Tie left shoe laces", "6. Put right shoe on right foot",
                        "7. Tie right shoe laces"});
        dummyTaskDetails.put("Water plants (assigned to Alice, target completion date 15/11/2019, priority 2)",
                new String[]{"1. Fill watering can", "2. Water all indoor plants", "3. Water garden plants",
                        "4. Check soil moisture levels"});
        dummyTaskDetails.put("Prepare report (assigned to John, due 20/11/2019, priority 1)",
                new String[]{"1. Collect latest sales data", "2. Analyze third quarter trends",
                        "3. Create report draft", "4. Review draft with team", "5. Finalize report and submit"});
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


}