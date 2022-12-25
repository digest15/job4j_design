package ru.job4j.arhitecture.isp.example3;

/**
 * Нарушает принцип разделения интерфейсов
 * т.к. разные машины могут состоять из разных компонентов и уметь делать разные действия
 * в зависимости от комлектации.
 * Не каждой машине нужны перечисленные методы, а некоторые должны уметь делать и другие действия
 * т.е. интерфейс нужно будет постоянно расширять, изменяя при этом существующие, реализованные классы
 */
public interface Car {
    void openDoor();
    void closeDoor();

    void openHood();
    void closeHood();

    void switchOnHeater();
    void switchOffHeater();

    void switchOnConditioner();
    void switchOffConditioner();

    void playMusic();
    void stopMusic();

    void start();

    void stop();
}
