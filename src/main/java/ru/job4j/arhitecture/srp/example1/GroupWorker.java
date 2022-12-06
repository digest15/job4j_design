package ru.job4j.arhitecture.srp.example1;

/**
 * Example 1
 * Этот интерфейс нарушает принцип Single Responsibility Principle
 * Он отвечает за регистрацию студента,
 * за расчет его результат
 * и за отправку ему емейла.
 * К тому же методы не предпалагают приема классов отвечающих
 * за регистрацию, оценку и рассылку.
 * Это значит что реализаторы этого интерфейса также будут зависеть от
 * конкретной реализации этих алгоритмов.
 */
public interface GroupWorker {
    public void register(Student student);

    public void calculateResults(Student student);

    public void sendEmail(Student student);
}
