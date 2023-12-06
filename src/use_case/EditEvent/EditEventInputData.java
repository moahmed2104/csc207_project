package use_case.EditEvent;

import java.time.LocalDateTime;

public class EditEventInputData {
    final private LocalDateTime start;
    final private LocalDateTime end;
    final private String address; //Figure out if this is allowed and if not how to accomplish this.

    // Description Specific items:
    final private String name;
    final private String description;


    public EditEventInputData(LocalDateTime start, LocalDateTime end, String address, String name, String description) {
        this.start = start;
        this.end = end;
        this.address = address;
        this.name = name;
        this.description = description;
    }

    LocalDateTime getStart(){
        return this.start;
    }

    LocalDateTime getEnd(){
        return this.end;
    }


    String getName(){
        return this.name;
    }

    String getDescription(){
        return this.description;
    }


    public String getItem() {
        return this.address;
    }
}
