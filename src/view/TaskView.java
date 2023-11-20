package view;
import interface_adapter.tasks.create_tasks.CreateTaskState;
import interface_adapter.tasks.task.TaskState;
import interface_adapter.tasks.task.TaskViewModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import interface_adapter.tasks.create_tasks.CreateTaskViewModel;

public class TaskView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Task Viewer";
    private final TaskViewModel taskViewModel;

    private final CreateTaskViewModel createTaskViewModel;

    //private final TaskController taskController;
    //private final CreateTaskController taskController;
    //private final DeleteTaskController taskController;
    //private final EditTaskController taskController;
    private final JButton create;
    private final JButton delete;
    private final JButton edit;

    private final JButton back;
    private final JPanel task;
    private final JScrollPane task_area;


    public TaskView(TaskViewModel taskViewModel, CreateTaskViewModel createTaskViewModel) {
        this.taskViewModel = taskViewModel;
        taskViewModel.addPropertyChangeListener(this);

        this.createTaskViewModel = createTaskViewModel;

        this.setSize(600, 600);
        JLabel title = new JLabel(TaskViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        create = new JButton(TaskViewModel.CREATE_BUTTON_LABEL);
        buttons.add(create);
        delete = new JButton(TaskViewModel.DELETE_BUTTON_LABEL);
        buttons.add(delete);
        edit = new JButton(TaskViewModel.EDIT_BUTTON_LABEL);
        buttons.add(edit);
        back = new JButton(TaskViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);
        task = new JPanel();

        task_area = new JScrollPane(task);

        task_area.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        task_area.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));




        create.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(create)) {
                            //JOptionPane.showMessageDialog(null,"TEST");
                              CreateTaskView test = new CreateTaskView(createTaskViewModel);
                              test.show();

//                            CreateTaskState currentstate = TaskView.this.createTaskViewModel.getState();
//                            CreateTaskViewModel createTaskViewModel = new CreateTaskViewModel();
//                            CreateTaskView createTask = new CreateTaskView(createTaskViewModel);
//                            createTaskViewModel.setState(currentstate);


                        }

                    }
                }
        );
        delete.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                }
        );
        edit.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                }
        );
        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                }
        );


        this.add(title);
        this.add(buttons);
        this.add(task);
        this.add(task_area);














    }



    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
