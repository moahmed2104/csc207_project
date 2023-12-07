package entity;

import java.util.ArrayList;
import java.util.Random;

public class DescriptionFactory {

    private String generateID(){
        Random rand = new Random();
        ArrayList<String> idList = new ArrayList<String>();
        while (idList.size() < 7){
            int id = rand.nextInt(9);
            idList.add(Integer.toString(id));
        }
        return String.join(", ", idList);
    }

    public Description create(String name, String id, String description, String address){
        Description desc = new Description(name, id, description, address);
        return desc;
    }
    public Description create(String name, String id, String description, Item parent, String descriptor){
        String desc = descriptor;
        String parentAddress = parent.getDescription().getAddress();
        if (parent.hasSubItem(descriptor)) {
            // if the parent already has a child with this descriptor we need to add a number at the end
            int descSuffix = 0;
            while (parent.hasSubItem(parentAddress + "/" + desc + descSuffix)) {
                descSuffix += 1;
            }
            desc = desc + descSuffix;

        }

        String descAddress = parentAddress + "/" + desc;
        return create(name, id, description, descAddress);
    }
    public Description create(String name, String id, String description, Item parent) {
        return create(name, id, description, parent, name); // uses the name as a descriptor by default
    }
    public Description create(String name, String description, Item parent) {
        return create(name, generateID(), description, parent, name);
    }
    public Description create(String name, String description, Item parent, String descriptor){
        return create(name, generateID(), description, parent, descriptor);
    }
    public Description create(String name, String description, String address){
        return create(name, generateID(), description, address);
    }
}
