package view;

import javax.swing.*;

import interface_adapter.tasks.create_tasks.CreateTaskViewModel;
import interface_adapter.tasks.edit_tasks.EditTaskController;
import interface_adapter.tasks.edit_tasks.EditTaskViewModel;
import interface_adapter.tasks.task.TaskViewModel;
import view.LabelTextPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.PropertyResourceBundle;

public class EditTaskView extends JFrame implements ActionListener, PropertyChangeListener {

    public final String viewName = "Edit Task Viewer";
    //private final EditTaskViewModel editTaskViewModel;
    private final JTextField nameOfTask = new JTextField(20);

    private final  JTextField dateOfTask = new JTextField(20);

    private final JTextPane descriptionPane = new JTextPane();

    private final JLabel title1 = new JLabel(EditTaskViewModel.TITLE_LABEL);

    private final JLabel date1 = new JLabel(EditTaskViewModel.DATE);

    private final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private  final JLabel task_name = new JLabel(EditTaskViewModel.TASK_NAME);

    private final JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private final JLabel subTasksLabel = new JLabel(EditTaskViewModel.DESCRIPTION);

    private JButton edit_button  = new JButton(EditTaskViewModel.EDIT_BUTTON);


    private JTextField dateField;
    private JTextArea descriptionArea;
    private JButton submitButton;
    private final EditTaskController controller;
    private final String originalTitle;

    public EditTaskView(String originalTitle, String date, String description, EditTaskController controller ){
        //this.editTaskViewModel = editTaskViewModel;
        this.controller = controller;
        this.originalTitle = originalTitle;

        setTitle(EditTaskViewModel.TAB_TITLE);
        setSize(500, 400);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        title1.setFont(new Font("Serif", Font.BOLD, 20));
        //gbc.anchor = GridBagConstraints.CENTER;
        //gbc.weightx = 1.0;
        //gbc.weighty = 0; // Give no weight
        title1.setHorizontalAlignment(SwingConstants.CENTER);
        add(title1, gbc);
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;


        titlePanel.add(task_name);
        titlePanel.add(nameOfTask);
        add(titlePanel, gbc);

        datePanel.add(date1);
        datePanel.add(dateOfTask);
        add(datePanel, gbc);



        subTasksLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(subTasksLabel, gbc);


        descriptionPane.setFont(new Font("Monospaced", Font.PLAIN, 12));
        descriptionPane.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(descriptionPane);
        scrollPane.setPreferredSize(new Dimension(400, 150));
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.5;
        add(scrollPane, gbc);

        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.PAGE_END;
        add(edit_button, gbc);

        setLocationRelativeTo(null);
        setVisible(true);
        nameOfTask.setText(originalTitle);
        dateOfTask.setText(date);
        descriptionPane.setText(description);
        edit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.editTask(originalTitle, nameOfTask.getText(), dateOfTask.getText(), descriptionPane.getText());

                EditTaskView.this.dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}