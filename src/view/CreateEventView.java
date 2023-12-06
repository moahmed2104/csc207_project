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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.regex.Pattern;

public class CreateEventView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Test Create Event View";
    private final CreateEventViewModel createEventViewModel;
    private final JTextField nameInputField = new JTextField(15);

    private final JTextField startDateInputField = new JTextField(15);
    private final JLabel startDateErrorField = new JLabel();
    private final JTextField startTimeInputField = new JTextField(15);
    private final JLabel startTimeErrorField = new JLabel();
    private final JTextField endDateInputField = new JTextField(15);
    private final JLabel endDateErrorField = new JLabel();
    private final JTextField endTimeInputField = new JTextField(15);
    private final JLabel endTimeErrorField = new JLabel();
    public String parentAddress;

    private final JTextField descriptionInputField = new JTextField(15);
    private final CreateEventController createEventController;
    private final JButton create;
    private final JButton cancel;


    public CreateEventView(CreateEventViewModel createEventViewModel, CreateEventController createEventController, String parentAddress) {
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
        LabelTextPanel startDateInfo = new LabelTextPanel(
                new JLabel(CreateEventViewModel.START_DATE_LABEL), startDateInputField);
        LabelTextPanel startTimeInfo = new LabelTextPanel(
                new JLabel(CreateEventViewModel.START_TIME_LABEL), startTimeInputField);
        LabelTextPanel endDateInfo = new LabelTextPanel(
                new JLabel(CreateEventViewModel.END_DATE_LABEL), endDateInputField);
        LabelTextPanel endTimeInfo = new LabelTextPanel(
                new JLabel(CreateEventViewModel.END_TIME_LABEL), endTimeInputField);
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
                            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                            LocalDateTime startDateTime = LocalDateTime.parse(currentState.getStart_date() + " " + currentState.getStart_time(), dateTimeFormatter);
                            LocalDateTime endDateTime = LocalDateTime.parse(currentState.getEnd_date() + " " + currentState.getEnd_time(), dateTimeFormatter);

                            createEventController.execute(
                                    currentState.getName(),
                                    currentState.getDescription(),
                                    parentAddress,
                                    startDateTime,
                                    endDateTime
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
                CreateEventState currentState = createEventViewModel.getState();
                currentState.setName(nameInputField.getText());
                createEventViewModel.setState(currentState);


            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        descriptionInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                CreateEventState currentState = createEventViewModel.getState();
                currentState.setDescription(descriptionInputField.getText() + e.getKeyChar());
                createEventViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                CreateEventState currentState = createEventViewModel.getState();
                currentState.setDescription(descriptionInputField.getText());
                createEventViewModel.setState(currentState);
            }
        });
        startDateInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                CreateEventState currentState = createEventViewModel.getState();
                currentState.setStart_date(startDateInputField.getText() + e.getKeyChar());
                createEventViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                CreateEventState currentState = createEventViewModel.getState();
                currentState.setStart_date(startDateInputField.getText());
                createEventViewModel.setState(currentState);

                Pattern datePattern = Pattern.compile("^\\d{2}-\\d{2}-\\d{4}$");

            }
        });
        startTimeInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                CreateEventState currentState = createEventViewModel.getState();
                currentState.setStart_time(startTimeInputField.getText() + e.getKeyChar());
                createEventViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                CreateEventState currentState = createEventViewModel.getState();
                currentState.setStart_time(startTimeInputField.getText());
                createEventViewModel.setState(currentState);
            }
        });
        endDateInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                CreateEventState currentState = createEventViewModel.getState();
                currentState.setEnd_date(endDateInputField.getText() + e.getKeyChar());
                createEventViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                CreateEventState currentState = createEventViewModel.getState();
                currentState.setEnd_date(endDateInputField.getText());
                createEventViewModel.setState(currentState);
            }
        });
        endTimeInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                CreateEventState currentState = createEventViewModel.getState();
                currentState.setEnd_time(endTimeInputField.getText() + e.getKeyChar());
                createEventViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                CreateEventState currentState = createEventViewModel.getState();
                currentState.setEnd_time(endTimeInputField.getText());
                createEventViewModel.setState(currentState);
            }
        });


        this.add(title);
        this.add(nameInfo);
        this.add(descInfo);
        this.add(startDateInfo);
        this.add(startDateErrorField);
        this.add(startTimeInfo);
        this.add(startTimeErrorField);
        this.add(endDateInfo);
        this.add(endDateErrorField);
        this.add(endTimeInfo);
        this.add(endTimeErrorField);
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
