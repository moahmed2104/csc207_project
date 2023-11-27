package entity;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class AddressIterator implements Iterable<String> {
    public String address;

    public AddressIterator(String address){
        this.address = address;

    }



    @NotNull
    @Override
    public Iterator<String> iterator() {
        return new iter(address);
    }

    private class iter implements Iterator<String>{
        String nextString;
        int currIndex;
        List<String> addresses;
        public iter(String address){
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
}
