package ProductWindow;

import java.util.HashSet;
import java.util.Random;

public class RandomUniqueID {         //KildeR: https://www.youtube.com/watch?v=kFF5x4OpvW4
    public void uniqueId() {
        Random r = new Random();
        HashSet<Integer> set = new HashSet<>();
        while (set.size() < 1) {
            int random = r.nextInt(99) + 10;
            set.add(random);
        }
        for (int randomNumbers : set) {
            System.out.println(randomNumbers);
        }
    }
}
