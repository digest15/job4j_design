package ru.job4j.arhitecture.lsp.protuctstore.entity;

import java.time.LocalDate;

public interface Expiring {
    double calculateExpiration(LocalDate forDate);
}
