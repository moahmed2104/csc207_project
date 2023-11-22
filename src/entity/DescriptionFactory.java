package entity;

public class DescriptionFactory {

    public Description create(String name, String description){
        return new Description(name,description);
    }

}
