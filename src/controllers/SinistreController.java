package controllers;

import models.Sinistre;
import services.SininsterService;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

public class SinistreController {
    private final SininsterService sininsterService;
    private final Scanner scanner = new Scanner(System.in);

    public SinistreController(SininsterService sininsterService) {
        this.sininsterService = sininsterService;
    }

    public void addSinistre(UUID contratId) {
        System.out.print("Enter sinistre description: ");
        String description = scanner.nextLine();
        System.out.print("Enter cost: ");
        double cost = Double.parseDouble(scanner.nextLine());

        Sinistre sinistre = new Sinistre();
        // **FIX**: Set the description and cost from user input
        sinistre.setDescription(description);
        sinistre.setCout(cost);
        // Assuming the service layer will handle setting the ID and DateTime

        sininsterService.create(sinistre, contratId);
        System.out.println("Sinistre added successfully!");
    }

    public void deleteSinistre() {
        System.out.print("Enter sinistre ID: ");
        UUID id = UUID.fromString(scanner.nextLine());
        sininsterService.delete(id);
        System.out.println("Sinistre deleted successfully!");
    }

    public void findSinistreById() {
        System.out.print("Enter sinistre ID: ");
        UUID id = UUID.fromString(scanner.nextLine());
        Sinistre sinistre = sininsterService.get(id);
        System.out.println("Found sinistre: " + sinistre);
    }

    public void coutTotalByClientId() {
        System.out.print("Enter client ID: ");
        UUID clientId = UUID.fromString(scanner.nextLine());
        double total = sininsterService.coutTotal(clientId);
        System.out.println("Total sinistres cost: " + total);
    }

    public void getSinistresByContratId() {
        System.out.print("Enter contrat ID: ");
        UUID contratId = UUID.fromString(scanner.nextLine());
        sininsterService.getByContratId(contratId).forEach(System.out::println);
    }

    public void getSinistresByClientId() {
        System.out.print("Enter client ID: ");
        UUID clientId = UUID.fromString(scanner.nextLine());
        sininsterService.sinistresByClientId(clientId).forEach(System.out::println);
    }

    public void sortSinistresByCout() {
        sininsterService.sort().forEach(System.out::println);
    }

    public void filterSinistresByDate() {
        System.out.print("Enter year-month-day (e.g., 2025-01-01): ");
        LocalDateTime date = LocalDateTime.parse(scanner.nextLine() + "T00:00:00");
        sininsterService.filterByDate(date).forEach(System.out::println);
    }

    public void filterSinistresByCout() {
        System.out.print("Enter minimum cost: ");
        double cost = Double.parseDouble(scanner.nextLine());
        sininsterService.filterByCout(cost).forEach(System.out::println);
    }
}