package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import static org.junit.Assert.*;

public class UserModelTest {
    @Test
    public void hasCodeMustBeEquals() {
        User user1 = new User("Bob", 0, new GregorianCalendar(1995, Calendar.APRIL, 1));
        User user2 = new User("Bob", 0, new GregorianCalendar(1995, Calendar.APRIL, 1));
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    public void equalsMustBeTrue() {
        User user1 = new User("Bob", 0, new GregorianCalendar(1995, Calendar.APRIL, 1));
        User user2 = new User("Bob", 0, new GregorianCalendar(1995, Calendar.APRIL, 1));
        assertTrue(user1.equals(user2));
    }
}