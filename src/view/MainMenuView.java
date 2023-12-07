package view;

import api.Calendar;
import interface_adapter.Sync.SyncController;
import interface_adapter.ViewManagerModel;
import use_case.sync.SyncDataAccessInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainMenuView extends JPanel {
    private final ViewManagerModel viewManagerModel;
    private final SyncController syncController;
    private final SyncDataAccessInterface syncDataAccessInterface;
    private final Calendar calendar;

    private final TaskView taskview;


    public final String viewName = "Main Menu";


    public MainMenuView(ViewManagerModel viewManagerModel, SyncController syncController, SyncDataAccessInterface syncDataAccessInterface, Calendar calendar) {
        this.viewManagerModel = viewManagerModel;
        this.syncController = syncController;
        this.syncDataAccessInterface = syncDataAccessInterface;
        this.calendar = calendar;
        this.taskview = taskView;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout()); // Use BorderLayout for overall panel layout

        // Title label
        JLabel titleLabel = new JLabel("Main Menu");
        titleLabel.setHorizontalAlignment(JLabel.CENTER); // Center the title text
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set the title font

        // Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center the buttons in the panel
        JButton taskButton = new JButton("Task");
        JButton eventsButton = new JButton("Events");
        JButton foldersButton = new JButton("Folders");
        JButton syncButton = new JButton("Sync");

        // Add buttons to the buttons panel
        buttonsPanel.add(taskButton);
        buttonsPanel.add(eventsButton);
        buttonsPanel.add(foldersButton);
        buttonsPanel.add(syncButton);

        // Add title label and buttons panel to the MainMenuView
        add(titleLabel, BorderLayout.NORTH); // Add the title label to the top (North)
        add(buttonsPanel, BorderLayout.CENTER);



        // Task button action
        taskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskview.refreshTaskList();
                taskview.updateTaskListUI();
                viewManagerModel.setActiveView("Task Viewer");
                viewManagerModel.firePropertyChanged();

            }
        });

        // Events button action
        eventsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskview.refreshTaskList();
                taskview.updateTaskListUI();
                viewManagerModel.setActiveView("Create Event View");
                viewManagerModel.firePropertyChanged();

            }
        });

        // Folders button action
        foldersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setActiveView("FoldersViewName"); // replace with the name used in your CardLayout for the folders view
                viewManagerModel.firePropertyChanged();

            }
        });
        syncButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(syncButton)){
                    try {
                        String calendarID = calendar.getCalendarID();
                        syncController.execute(syncDataAccessInterface.getLocalItems(),
                                syncDataAccessInterface.getLocalDescriptions(),
                                syncDataAccessInterface.getRemoteItems(calendar, calendarID), calendar, calendarID);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

    }
}