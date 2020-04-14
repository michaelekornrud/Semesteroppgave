package ProductWindow;

import java.util.UUID;

public class UniqueIDGenerator {         //KildeR: https://www.youtube.com/watch?v=kFF5x4OpvW4
    public UUID uniqueId() {
        UUID uniqueId = UUID.randomUUID();
        System.out.println(uniqueId);
           return uniqueId;
        }

    }

