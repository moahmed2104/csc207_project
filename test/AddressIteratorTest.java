import entity.AddressIterator;
import org.junit.Test;
import org.junit.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class AddressIteratorTest {

    @Test
    public void emptyAddress(){
        String address = "";
        AddressIterator iterable = new AddressIterator(address);
        Iterator iter = iterable.iterator();
        assertEquals(iter.next(), "");
        assertEquals(iter.hasNext(), false);
        try {
            iter.next();
            fail("Expected NoSuchElementException but did not get one.");
        } catch(NoSuchElementException e) {
            assertTrue(true);
        }
    }

    @Test
    public void basicAddress(){
        String address = "First/Second/Last";
        AddressIterator iterable = new AddressIterator(address);
        Iterator iter = iterable.iterator();
        String output = "";
        for (int i = 0; i < 3; i++){
            assertEquals(iter.hasNext(), true);
            output = output.concat(i + ":" + iter.next());

        }
        assertEquals(output, "0:First1:First/Second2:First/Second/Last");

        assertEquals(iter.hasNext(), false);

        try {
            iter.next();
            fail("Expected NoSuchElementException but did not get one.");
        } catch(NoSuchElementException e) {
            assertTrue(true);
        }
    }
}
