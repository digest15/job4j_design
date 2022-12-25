package ru.job4j.arhitecture.isp.example1;

/**
 * Нарушает принцип разделения интерфейсов
 * т.к. может быть множество устройств, со способностью делать только одно из приведенных действий
 * и они будут вынуждены реализовывать/зависеть от неиспользуемых ими методов
 * либо создавать новый интерфейс/тип, что может быть неприемлемо
 */
public interface Phone {
    void call();
    void takePhoto();
    void makeVideo();
    void connectWiFi();
    void connectLte();
    void connectNR();
}
