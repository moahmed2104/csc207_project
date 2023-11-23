package entity;

public class Description {
    /*
    Class that holds all the information about how to identify and display an item in our task managing system

    Reason to change: If we want to change something about the way information about the items being to stored

     */
    public String name;
    //todo consider making private? if so add getters and setters
    public String description;
    public String address;

    public Description(String name, String description, String address){
        this.description = description;
        this.name = name;
        this.address = address;

    }
    public void rename(String newName) {
        this.name = newName;
    }
    public void rewriteDescription(String newDescription) {
        this.description = newDescription;
    }

    //todo add an "icon" for the item
}
