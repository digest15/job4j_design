package ru.job4j.arhitecture.srp.example1;

public interface GroupWorker {
    public void register(Student student);

    public void calculateResults(Student student);

    public void sendEmail(Student student);
}
