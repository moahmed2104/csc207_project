package entity;

import java.util.*;

public class AddressIterator implements Iterator<String> {
    List<String> addresses;
    String nextString;
    int currIndex;
    public AddressIterator(String address){
        String[] descriptors = address.split("/");
        this.addresses = Arrays.stream(descriptors).toList();
        this.currIndex = 0;
        this.nextString = addresses.get(0);
    }

    @Override
    public boolean hasNext() {
        return  currIndex < addresses.size();
    }

    @Override
    public String next() throws NoSuchElementException{
        if (currIndex >= addresses.size()){
            throw new NoSuchElementException();
        }
        String toBeReturned = this.nextString;
        currIndex += 1;
        if (currIndex < addresses.size()) {
            this.nextString = nextString.concat("/" + addresses.get(currIndex));
        }
        return toBeReturned;
    }
}
