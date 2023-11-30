package view;

import interface_adapter.CreateNewEvent.CreateEventController;
import interface_adapter.CreateNewEvent.CreateEventState;
import interface_adapter.CreateNewEvent.CreateEventViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TestCreateEventView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Test Create Event View";
    private final CreateEventViewModel createEventViewModel;
    private final JTextField nameInputField = new JTextField(15);

    private final String parentAddress;

    private final JTextField descriptionInputField = new JTextField(30); // todo make sure this looks nice
    private final CreateEventController createEventController;
    private final JButton create;
    private final JButton cancel;


    public TestCreateEventView(CreateEventViewModel createEventViewModel, CreateEventController createEventController, String parentAddress) {
        this.parentAddress = parentAddress;
        this.createEventViewModel = createEventViewModel;
        this.createEventController = createEventController;
        createEventViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel(CreateEventViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        LabelTextPanel nameInfo = new LabelTextPanel(
                new JLabel(CreateEventViewModel.NAME_LABEL), nameInputField);
        LabelTextPanel descInfo = new LabelTextPanel(
                new JLabel(CreateEventViewModel.DESCRIPTION_LABEL), descriptionInputField);
        JPanel buttons = new JPanel();
        create = new JButton(CreateEventViewModel.CREATE_EVENT_LABEL);
        buttons.add(create);
        cancel = new JButton(CreateEventViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        create.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if(evt.getSource().equals(create)) {
                            CreateEventState currentState = createEventViewModel.getState();
                            createEventController.execute(
                                    currentState.getName(),
                                    currentState.getDescription(),
                                    parentAddress
                            );
                        }
                    }
                }

        );

        cancel.addActionListener(this); //todo implement

        nameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                CreateEventState currentState = createEventViewModel.getState();
                currentState.setName(nameInputField.getText() + e.getKeyChar());
                createEventViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        descriptionInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                CreateEventState currentState = createEventViewModel.getState();
                currentState.setDescription(nameInputField.getText() + e.getKeyChar());
                createEventViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        this.add(title);
        this.add(nameInfo);
        this.add(descInfo);
        this.add(buttons);

    }




    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        CreateEventState state = (CreateEventState) evt.getNewValue();
        setFields(state);
    }
    private void setFields(CreateEventState state) {
        nameInputField.setText(state.getName());
    }
}
