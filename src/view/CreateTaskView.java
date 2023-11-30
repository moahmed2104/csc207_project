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
    private final JTextField nameOfTask = new JTextField(15);

    private final  JTextField dateOfTask = new JTextField(15);

    private final JTextField descriptionOfTask = new JTextField(15);

    private final JLabel title = new JLabel(CreateTaskViewModel.TITLE_LABEL);

    private JButton okButton = new JButton(CreateTaskViewModel.OK_BUTTON);

    public CreateTaskView(CreateTaskViewModel createTaskViewModel) {
        this.createTaskViewModel = createTaskViewModel;
        // Frame title and settings
        setTitle("Task Creator");
        setSize(500, 400); // Set the size of the frame to 500x500
        setLayout(new GridBagLayout()); // Use GridBagLayout for better control

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Add some insets for padding

        // Title label with larger font and centered at the top
        JLabel titleLabel = new JLabel("Creating a Task");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20)); // Set the font size to 20
        //gbc.anchor = GridBagConstraints.CENTER; // Anchor to the top of the window
        //gbc.weightx = 1.0;
        //gbc.weighty = 0; // Give no weight
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, gbc);

        // Reset to default weight
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Task Title
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.add(new JLabel("Task Title:"));
        JTextField titleField = new JTextField(20);
        titlePanel.add(titleField);
        add(titlePanel, gbc);

        // Date
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.add(new JLabel("Date(dd/mm/yyyy):"));
        JTextField dateField = new JTextField(20);
        datePanel.add(dateField);
        add(datePanel, gbc);

        // Task Description
        JLabel subTasksLabel = new JLabel("Task Description:");
        subTasksLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(subTasksLabel, gbc);

        JTextPane descriptionPane = new JTextPane();
        descriptionPane.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Set a monospaced font
        descriptionPane.setMargin(new Insets(10, 10, 10, 10)); // Add padding inside the JTextPane
        JScrollPane scrollPane = new JScrollPane(descriptionPane);
        scrollPane.setPreferredSize(new Dimension(400, 150)); // Adjusted size for a smaller description box
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.5; // Give weight to expand vertically
        add(scrollPane, gbc);

        // Button
        JButton createButton = new JButton("Create Task");
        gbc.weighty = 0; // Reset weight so button doesn't take extra vertical space
        gbc.fill = GridBagConstraints.NONE; // Set fill to none so button doesn't stretch
        gbc.anchor = GridBagConstraints.PAGE_END; // Anchor to the bottom of the window
        add(createButton, gbc);

        // Display the frame
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}