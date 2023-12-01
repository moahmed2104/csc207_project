package view;

import javax.swing.*;

import interface_adapter.tasks.create_tasks.CreateTaskViewModel;
import interface_adapter.tasks.task.TaskViewModel;
import view.LabelTextPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.PropertyResourceBundle;

public class CreateTaskView extends JFrame implements ActionListener, PropertyChangeListener {

    public final String viewName = "Create Task Viewer";
    private final CreateTaskViewModel createTaskViewModel;
    private final JTextField titleField = new JTextField(20);

    private final  JTextField dateField = new JTextField(20);

    private final JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private final JTextPane descriptionPane = new JTextPane();

    private final JLabel titleLabel = new JLabel(CreateTaskViewModel.TITLE_LABEL);
    private final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private JButton createButton = new JButton(CreateTaskViewModel.TASK_BUTTON);

    public CreateTaskView(CreateTaskViewModel createTaskViewModel) {
        this.createTaskViewModel = createTaskViewModel;

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}