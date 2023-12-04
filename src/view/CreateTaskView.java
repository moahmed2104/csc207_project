package view;

import javax.swing.*;

import interface_adapter.CreateNewEvent.CreateEventState;
import interface_adapter.tasks.create_tasks.CreateTaskController;
import interface_adapter.tasks.create_tasks.CreateTaskState;
import interface_adapter.tasks.create_tasks.CreateTaskViewModel;
import interface_adapter.tasks.task.TaskViewModel;
import view.LabelTextPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.PropertyResourceBundle;

public class CreateTaskView extends JFrame implements ActionListener, PropertyChangeListener {

    public final String viewName = "Create Task Viewer";

    private final String parentAddress;

    private final CreateTaskController createTaskController;
    private final CreateTaskViewModel createTaskViewModel;
    private final JTextField titleField = new JTextField(20);

    private final  JTextField dateField = new JTextField(20);

    private final JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private final JTextPane descriptionPane = new JTextPane();

    private final JLabel titleLabel = new JLabel(CreateTaskViewModel.TITLE_LABEL);
    private final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private JButton createButton = new JButton(CreateTaskViewModel.TASK_BUTTON);

    public CreateTaskView(CreateTaskViewModel createTaskViewModel, CreateTaskController createTaskController, String parentAddress) {
        this.createTaskViewModel = createTaskViewModel;
        this.createTaskController = createTaskController;
        this.parentAddress = parentAddress;

        setTitle(CreateTaskViewModel.LABEL);
        setSize(500, 400);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        //gbc.anchor = GridBagConstraints.CENTER;
        //gbc.weightx = 1.0;
        //gbc.weighty = 0;
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, gbc);
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;


        titlePanel.add(new JLabel(CreateTaskViewModel.TASK_NAME));
        titlePanel.add(titleField);
        add(titlePanel, gbc);

        datePanel.add(new JLabel(CreateTaskViewModel.DATE));
        datePanel.add(dateField);
        add(datePanel, gbc);


        JLabel subTasksLabel = new JLabel(CreateTaskViewModel.DESCRIPTION);
        subTasksLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(subTasksLabel, gbc);


        descriptionPane.setFont(new Font("Monospaced", Font.PLAIN, 12));
        descriptionPane.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(descriptionPane);
        scrollPane.setPreferredSize(new Dimension(400, 150));
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.5;
        add(scrollPane, gbc);

        // Button

        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.PAGE_END;
        add(createButton, gbc);


        setLocationRelativeTo(null);
        setVisible(true);

        createButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                       /* if(evt.getSource().equals(createButton)) {
                            CreateTaskState currentState = createTaskViewModel.getState();
                            createTaskController.execute(
                                    currentState.getCreateTaskName(),
                                    currentState.getCreateTaskDate(),
                                    currentState.getCreateTaskDescription(),
                                    parentAddress);



                        }
                    }
                }*/
                        if(evt.getSource().equals(createButton)) {
                            String title = titleField.getText();
                            String date = dateField.getText();
                            String description = descriptionPane.getText();
                            createTaskController.createTask(title, date, description, "src/Tasks.csv");
                            CreateTaskView.this.dispose();
                        }
                    }}

        );

        titleField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                CreateTaskState currentState = createTaskViewModel.getState();
                currentState.setCreateTaskName(titleField.getText() + e.getKeyChar());
                createTaskViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        descriptionPane.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                CreateTaskState currentState = createTaskViewModel.getState();
                currentState.setCreateTaskDescription(descriptionPane.getText() + e.getKeyChar());
                createTaskViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }


        });
        dateField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                CreateTaskState currentState = createTaskViewModel.getState();
                currentState.setCreateTaskDate(descriptionPane.getText() + e.getKeyChar());
                createTaskViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        CreateTaskState state = (CreateTaskState) evt.getNewValue();
        setFields(state);
    }
    private void setFields(CreateTaskState state) {
        titleField.setText(state.getCreateTaskName());
    }
}