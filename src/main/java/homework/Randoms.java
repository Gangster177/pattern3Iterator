package homework;

import java.util.Iterator;
import java.util.Random;

public class Randoms implements Iterable<Integer> {
    protected Random random;
//    protected final int MIN;


    public Randoms(int min, int max) {
        this.random = new Random();
        //...
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }

    //...
}