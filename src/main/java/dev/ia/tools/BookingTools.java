package dev.ia.tools;


import dev.ia.enuns.Category;
import dev.ia.records.Booking;
import dev.ia.services.BookingService;
import dev.langchain4j.agent.tool.Tool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;


/**
 * Agora, vamos criar a classe que conterá os métodos que o agente poderá chamar.
 */
@ApplicationScoped
public class BookingTools {

    @Inject
    private BookingService bookingService;

    @Tool("Cancela uma reserva existente com base no seu ID (bookingId). O usuário deve estar autenticado.")
    public String cancelaBooking(long  bookingId) {
        return bookingService.cancelBooking(bookingId)
                .map(b -> "Reserva com ID " + b.id() + " cancelada com sucesso.")
                .orElse("Não foi possível cancelar a reserva. Verifique se o ID está correto e se você tem permissão.");
    }

    @Tool("Obtém os detalhes completos de uma reserva com base em seu número de identificação (bookingId).")
    public String getBookingDetails(long bookingId) {
        return bookingService.getBookingDetails(bookingId)
                .map(Booking::toString)
                .orElse("Reserva com ID " + bookingId + " não encontrada.");
    }

    public String listPackagesByCategory(Category category) {
        List<Booking> packages = bookingService.findPackagesByCategory(category);
        if (packages.isEmpty()) {
            return "Nenhum pacote encontrado para a categoria: " + category;
        }
        return "Pacotes encontrados para a categoria '"+ category +"':\n" + packages.stream()
                .map(Booking::destination)
                .toList().toString();
    }
}
