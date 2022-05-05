package ru.job4j.question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, User> currMap = new HashMap<>();
        current.forEach(u -> currMap.put(u.getId(), u));
        int deleted = 0;
        int changed = 0;
        for (User u : previous) {
            boolean isPresent = currMap.containsKey(u.getId());
            deleted += (!isPresent ? 1 : 0);
            changed += (isPresent && !u.getName().equals(currMap.get(u.getId()).getName()) ? 1 : 0);
        }
        int added = current.size() + deleted - previous.size();
        return new Info(added, changed,  deleted);
    }
}
