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

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getStart_dateError() {
        return start_dateError;
    }

    public void setStart_dateError(String start_dateError) {
        this.start_dateError = start_dateError;
    }

    public String getStart_timeError() {
        return start_timeError;
    }

    public void setStart_timeError(String start_timeError) {
        this.start_timeError = start_timeError;
    }

    public String getEnd_dateError() {
        return end_dateError;
    }

    public void setEnd_dateError(String end_dateError) {
        this.end_dateError = end_dateError;
    }

    public String getEnd_timeError() {
        return end_timeError;
    }

    public void setEnd_timeError(String end_timeError) {
        this.end_timeError = end_timeError;
    }

    private String start_date = "";
    private String end_date = "";
    private String start_time = "";
    private String end_time = "";

    private String start_dateError = null;

    private String start_timeError = null;

    private String end_dateError = null;
    private String end_timeError = null;

    public CreateEventState (CreateEventState copy) {
        this.name = copy.name;
        this.nameError = copy.nameError;
        this.description = copy.description;
        this.descriptionError = copy.descriptionError;
        this.start_date = copy.start_date;
        this.start_time = copy.start_time;
        this.end_date = copy.end_date;
        this.start_dateError = copy.start_dateError;
        this.start_timeError = copy.start_timeError;
        this.end_dateError = copy.end_dateError;
        this.end_timeError = copy.end_timeError;
    }
    public CreateEventState() {}
}
