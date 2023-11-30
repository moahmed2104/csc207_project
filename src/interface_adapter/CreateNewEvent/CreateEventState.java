package interface_adapter.CreateNewEvent;

public class CreateEventState {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String name = "";
    private String nameError = null;
    private String description = "";
    private String descriptionError = null;

    public CreateEventState (CreateEventState copy) {
        this.name = copy.name;
        this.nameError = copy.nameError;
        this.description = copy.description;
        this.descriptionError = copy.descriptionError;
    }
    public CreateEventState() {}
}
