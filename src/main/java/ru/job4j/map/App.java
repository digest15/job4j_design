package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        User user1 = new User("Bob", 0, new GregorianCalendar(1995, Calendar.APRIL, 1));
        User user2 = new User("Dad Vasiliy", 7, new GregorianCalendar(1972, Calendar.OCTOBER, 15));
        Map<User, Object> users = new HashMap<>();
        users.put(user1, new Object());
        users.put(user2, new Object());

        System.out.println(users);
    }
}
