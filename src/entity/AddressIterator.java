package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class AddressIterator implements Iterator<String> {
    List<String> addresses;
    String nextString;
    int currIndex;
    public AddressIterator(String address){
        String[] descriptors = address.split("//"); // "//" is the regular expression for "/"
        this.addresses = Arrays.stream(descriptors).toList();
        this.currIndex = 0;
        this.nextString = addresses.get(0);
    }

    @Override
    public boolean hasNext() {
        return currIndex + 1 < addresses.size();
    }

    @Override
    public String next() {
        String toBeReturned = this.nextString;
        currIndex += 1;
        if (currIndex >= addresses.size()){
            throw IndexOutOfBoundsException;
        }
        this.nextString = nextString.concat("/" + addresses.get(currIndex));
        return toBeReturned;
    }
}
