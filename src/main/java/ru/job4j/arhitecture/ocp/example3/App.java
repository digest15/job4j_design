package ru.job4j.arhitecture.ocp.example3;

public class App {
    public static void main(String[] args) {
        Mailer mailer = new Mailer(new Logger());
        mailer.sendMessage("Hello world");
    }
}
