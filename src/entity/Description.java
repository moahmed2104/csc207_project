package entity;
/*
Class that holds all the information about how to identify and display an item in our task managing system

Reason to change: If we want to change something about the way information about the items being to stored

 */
public class Description {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    private String name;
    //todo consider making private? if so add getters and setters
    private String description;
    private String address;

    public Description(String name, String description, String address){
        this.description = description;
        this.name = name;
        this.address = address;

    }

    //todo add an "icon" for the item
}
