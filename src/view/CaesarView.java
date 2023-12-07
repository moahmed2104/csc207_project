package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interface_adapter.ViewManagerModel;

public class CaesarView extends JPanel {
    public static final String VIEW_NAME = "Caesar View";
    private JButton startButton;

    public CaesarView(ViewManagerModel viewManagerModel) { // Pass the ViewManagerModel to the constructor
        // Set the layout manager to align items in the center
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Create a welcome label and center it horizontally and vertically
        JLabel welcomeLabel = new JLabel("Welcome to Caesar", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.insets = new Insets(10, 0, 0, 0); // top padding
        gbc.weighty = 1; // Assign extra vertical space to the label area
        add(welcomeLabel, gbc);

        // Create a start button
        startButton = new JButton("Start");
        gbc.weighty = 0; // Do not assign extra vertical space to the button area
        gbc.insets = new Insets(10, 0, 10, 0); // top and bottom padding
        add(startButton, gbc);

        // Add an action listener to the start button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Change to the main menu view using the ViewManagerModel
                viewManagerModel.setActiveView("Main Menu"); // Use the correct view name for your main menu
                viewManagerModel.firePropertyChanged(); // Notify the change to update the view
            }
        });
    }

    // If needed, a method to get the view name
    public static String getViewName() {
        return VIEW_NAME;
    }
}