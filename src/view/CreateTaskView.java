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

public class CreateTaskView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Create Task Viewer";
    public final JFrame create_task = new JFrame();
    private final CreateTaskViewModel createTaskViewModel;
    private final JTextField nameOfTask = new JTextField(15);

    private final  JTextField dateOfTask = new JTextField(15);

    private final JTextField descriptionOfTask = new JTextField(15);

    private final JLabel title = new JLabel(CreateTaskViewModel.TITLE_LABEL);

    private JButton okButton = new JButton(CreateTaskViewModel.OK_BUTTON);

    public CreateTaskView(CreateTaskViewModel createTaskViewModel) {
        this.createTaskViewModel = createTaskViewModel;
        create_task.setSize(600, 600);
        create_task.setVisible(true);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.TOP);
        title.setFont(new Font("Sans-serif", Font.BOLD, 20));
        

        createTaskViewModel.addPropertyChangeListener(this);

        //okButton.setVisible(true);
        okButton.setBounds(260,500,60,40);
        okButton.setFocusable(false);
        okButton.addActionListener(this);

        create_task.add(okButton);
        create_task.add(title);



    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
