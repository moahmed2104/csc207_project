package use_case.CreateNewEvent;

import entity.Item;

import java.time.LocalDateTime;

public class CreateEventInputData {
    final private LocalDateTime start;
    final private LocalDateTime end;
    final private Item parent; //Figure out if this is allowed and if not how to accomplish this.

    // Description Specific items:
    final private String name;
    final private String description;


    public CreateEventInputData(LocalDateTime start, LocalDateTime end, Item parent, String name, String description) {
        this.start = start;
        this.end = end;
        this.parent = parent;
        this.name = name;
        this.description = description;
    }

    LocalDateTime getStart(){
        return this.start;
    }

    LocalDateTime getEnd(){
        return this.end;
    }

    Item getParent(){
        return this.parent;
    }

    String getName(){
        return this.name;
    }

    String getDescription(){
        return this.description;
    }



}
