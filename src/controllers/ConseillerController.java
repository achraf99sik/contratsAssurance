package controllers;

import models.Conseiller;
import services.ConseillerService;

import java.util.Scanner;
import java.util.UUID;

public class ConseillerController {
    private final ConseillerService conseillerService;
    private final Scanner scanner = new Scanner(System.in);

    public ConseillerController(ConseillerService conseillerService) {
        this.conseillerService = conseillerService;
    }

    public void addConseiller() {
        System.out.print("Enter Conseiller's first name: ");
        String prenom = scanner.nextLine();
        System.out.print("Enter Conseiller's last name: ");
        String nom = scanner.nextLine();
        System.out.print("Enter Conseiller's email: ");
        String email = scanner.nextLine();
        Conseiller conseiller = new Conseiller();
        conseiller.setId(UUID.randomUUID());
        conseiller.setNom(nom);
        conseiller.setPrenom(prenom);
        conseiller.setEmail(email);
        conseillerService.create(conseiller);
        System.out.println("Conseiller added successfully!");
    }

    public void deleteConseiller() {
        System.out.print("Enter conseiller ID: ");
        UUID id = UUID.fromString(scanner.nextLine());
        conseillerService.delete(id);
        System.out.println("Conseiller deleted successfully!");
    }

    public void findConseillerById() {
        System.out.print("Enter conseiller ID: ");
        UUID id = UUID.fromString(scanner.nextLine());
        Conseiller conseiller = conseillerService.get(id);
        System.out.println("Found conseiller: " + conseiller);
    }

    public void getClientsByConseillerId() {
        System.out.print("Enter conseiller ID: ");
        UUID id = UUID.fromString(scanner.nextLine());
        conseillerService.getClients(id).forEach(System.out::println);
    }
}
