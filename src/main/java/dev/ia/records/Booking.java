package dev.ia.records;

import dev.ia.enuns.BookingStatus;
import dev.ia.enuns.Category;

import java.time.LocalDate;

/***
 * Criar o Modelo de Dados
 * @param id
 * @param customerName
 * @param destination
 * @param startDate
 * @param endDate
 * @param status
 */
public record Booking(
        Long id,
        String customerName,
        String destination,
        LocalDate startDate,
        LocalDate endDate,
        BookingStatus status,
        Category category
) {
}
