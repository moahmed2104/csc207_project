package entity;

public class Description {
    /*
    Class that holds all the information about how to identify and display an item in our task managing system

    Reason to change: If we want to change something about the way information about the items being to stored

     */
    public String name;
    //todo consider making private? if so add getters and setters
    public String description;
    public Description(String name, String description){
        this.description = description;
        this.name = name;

    }
    public void rename(String newName) {
        //todo implement
    }
    public void rewriteDescription(String newDescription) {
        //todo implement
    }

    //todo add an "icon" for the item
}
