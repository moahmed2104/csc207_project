package ItemTest;

import entity.*;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ItemTests {
    @org.junit.Test
    public void DescriptionTest(){
        Description description = new Description("name", "kj", "none","all");
        description.setAddress("all/test");
        assertEquals(description.getName(),"name");
        assertEquals(description.getDescription(),"none");
        assertEquals(description.getID(),"kj");
        assertEquals(description.getAddress(),"all/test");
        description.setAddress("all");
        DescriptionFactory descriptionFactory = new DescriptionFactory();
        HeadItem item = new HeadItem(descriptionFactory);
        Description desc = descriptionFactory.create("supertest", "hm", item);
        item.addSubItem(new Event(desc, LocalDateTime.now(), LocalDateTime.now(), item));
        Description desc2 = descriptionFactory.create("supertest", "hm", item);
        assertEquals(desc2.getAddress(),"all/supertest");

    }

    @org.junit.Test
    public void EventTest() {
        Description description = new Description("name", "ii", "desc", "all");
        HeadItem item = new HeadItem(new DescriptionFactory());
        Event event = new Event(description, LocalDateTime.now(), LocalDateTime.now(), item);
        assertEquals(event.getSubItem().size(), 0);
        event.addSubItem(new Tasks(description,"now", event));
        assertTrue(event.hasSubItem("all"));
        event.findSubItem("all");
        event.getEndTime();
        event.getStartTime();
        try{
            event.navigate("all/test");
        } catch (NoSuchElementException e){
            assertTrue(true);
        }


    }
    @org.junit.Test
    public void TasksTest() {
        Description description = new Description("name", "ii", "desc", "all");
        HeadItem item = new HeadItem(new DescriptionFactory());
        Tasks event = new Tasks(description, "now", item);
        assertEquals(event.getSubItem().size(), 0);
        event.addSubItem(new Tasks(description,"now", event));
        event.hasSubItem("all");
        event.findSubItem("all");
        event.getDate();
        event.getSubItem();
        event.getParentItem();
        event.navigate("all/test");
        event.addSubItem(item);
        event.hasSubItem("all");
        event.findSubItem("all");
        event.navigate("all");
    }
    @org.junit.Test
    public void FolderTest(){
        Description description = new Description("name", "ii", "desc", "all");
        HeadItem item = new HeadItem(new DescriptionFactory());
        Folder event = new Folder();
        event.getSubItem();
        event.addSubItem(new Tasks(description,"now", event));
        event.hasSubItem("all");
        event.findSubItem("all");
        event.getSubItem();
        event.getParentItem();
        event.navigate("all/test");
        event.addSubItem(item);
        event.hasSubItem("all");
        event.findSubItem("all");
        event.navigate("all");
    }

}
