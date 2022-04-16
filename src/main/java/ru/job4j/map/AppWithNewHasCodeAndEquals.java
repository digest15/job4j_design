package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class AppWithNewHasCodeAndEquals {
    public static void main(String[] args) {
        User user1 = new User("Bob", 0, new GregorianCalendar(1995, Calendar.APRIL, 1));
        User user2 = new User("Bob", 0, new GregorianCalendar(1995, Calendar.APRIL, 1));

        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());

        Map<User, Object> users = new HashMap<>();
        users.put(user1, new Object());
        users.put(user2, new Object());

        System.out.println(users);
    }
}
