package ru.job4j.arhitecture.isp.example2;

/**
 * Нарушает принцип разделения интерфейсов
 * т.к. может быть множество провайдеров, предоставляющих только ограниченный набор услуг
 * и они будут вынуждены реализовывать/зависеть от неиспользуемых ими методов
 * либо создавать новый интерфейс/тип, что может быть неприемлемо
 */
public interface CloudProvider {
    void storeFile();
    void getFile();
    void listFiles();

    void getVersionFile();
    void createVersionFile();

    void createVpc();
    void startVpc();
    void stopVpc();
    void removeVpc();

    void createCdn();
    void dropCdn();
}
