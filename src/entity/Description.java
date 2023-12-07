package entity;
/*
Class that holds all the information about how to identify and display an item in our task managing system

Reason to change: If we want to change something about the way information about the items being to stored

 */
public class Description {
    public String getName() {
        return name;
    }

    public String getID(){ return this.id; }

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
    private String id;
    private String description;
    private String address;

    public Description(String name, String id, String description, String address){
        this.description = description;
        this.id = id;
        this.name = name;
        this.address = address;

    }
    public String toString(){
        return "name:" + this.name + ", description:" + this.description + ", address:" + this.address;
    }
}
