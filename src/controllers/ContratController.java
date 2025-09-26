package controllers;

import enums.TypeContrat;
import models.Contrat;
import services.ContratService;

import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class ContratController {
    private final ContratService contratService;
    private final Scanner scanner = new Scanner(System.in);

    public ContratController(ContratService contratService) {
        this.contratService = contratService;
    }

    public void addContrat(UUID clientId) {
        System.out.print("Enter contrat type(AUTOMOBILE, IMMOBILIER, MALADIE): ");
        TypeContrat type = TypeContrat.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Enter start date (yyyy-MM-dd): ");
        Date dateDebut = java.sql.Date.valueOf(scanner.nextLine());

        System.out.print("Enter end date (yyyy-MM-dd): ");
        Date dateFin = java.sql.Date.valueOf(scanner.nextLine());

        Contrat contrat = new Contrat();
        contrat.setId(UUID.randomUUID());
        contrat.setTypeContrat(type);
        contrat.setDateDebut(dateDebut);
        contrat.setDateFin(dateFin);

        contratService.create(contrat, clientId);
        System.out.println("Contrat added successfully!");
    }

    public void deleteContrat() {
        System.out.print("Enter contrat ID: ");
        UUID id = UUID.fromString(scanner.nextLine());
        contratService.delete(id);
        System.out.println("🗑Contrat deleted successfully!");
    }

    public void findContratById() {
        System.out.print("Enter contrat ID: ");
        UUID id = UUID.fromString(scanner.nextLine());
        Contrat contrat = contratService.get(id);
        System.out.println("Found contrat: " + contrat);
    }

    public void getContratsByClientId() {
        System.out.print("Enter client ID: ");
        UUID id = UUID.fromString(scanner.nextLine());
        contratService.getByClientId(id).forEach(System.out::println);
    }
}
