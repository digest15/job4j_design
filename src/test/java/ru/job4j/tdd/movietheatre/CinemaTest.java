package ru.job4j.tdd.movietheatre;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class CinemaTest {
    @Ignore
    @Test
    public void whenBuyThenGetTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Ignore
    @Test
    public void whenAddSessionThenItExistsBetweenAllSessions() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(s -> true);
        assertThat(sessions).contains(session);
    }

    @Ignore
    @Test
    public void whenNoSessionThenMustGetNull() {
        Cinema cinema = new Cinema3D();
        List<Session> sessions = cinema.find(s -> true);
        assertThat(sessions).isNull();
    }

    @Ignore
    @Test
    public void whenBuyOnInvalidRowThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            cinema.buy(account, -1, 1, date);
        });
    }

    @Ignore
    @Test
    public void whenBuyOnInvalidColumnThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            cinema.buy(account, 1, -1, date);
        });
    }
}