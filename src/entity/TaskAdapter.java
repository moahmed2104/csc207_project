package entity;

public class TaskAdapter extends Tasks{
    public TaskAdapter(Event event){
        super(event.getDescription(), String.valueOf(event.getStartTime().toLocalDate()), event.getParentItem());

    }


}
