package entity;

public class DescriptionFactory {

    public Description create(String name, String description, String address){
        return new Description(name, description, address);
    }
    public Description create(String name, String description, Item parent, String descriptor){
        String desc = descriptor;
        if (parent.hasChild(descriptor)) {
            // if the parent already has a child with this descriptor we need to add a number at the end
            int descSuffix = 0;
            while (parent.hasChild(desc + descSuffix)) {
                descSuffix += 1;
            }
            desc = desc + descSuffix;

        }
        String parentAddress = parent.getDescription().address;
        String descAddress = parentAddress + "/" + desc;
        return new Description(name, description, descAddress);
    }
    public Description create(String name, String description, Item parent) {
        return create(name, description, parent, name); // uses the name as a descriptor by default
    }

}
