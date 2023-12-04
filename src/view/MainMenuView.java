package view;

import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuView extends JPanel {
    private final ViewManagerModel viewManagerModel;

    public final String viewName = "Main Menu";

    public MainMenuView(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
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

        // Add buttons to the buttons panel
        buttonsPanel.add(taskButton);
        buttonsPanel.add(eventsButton);
        buttonsPanel.add(foldersButton);

        // Add title label and buttons panel to the MainMenuView
        add(titleLabel, BorderLayout.NORTH); // Add the title label to the top (North)
        add(buttonsPanel, BorderLayout.CENTER);



        // Task button action
        taskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setActiveView("Task Viewer");
                viewManagerModel.firePropertyChanged();
            }
        });

        // Events button action
        eventsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setActiveView("Test Create Event View");
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

    }
}