package controllers;

import models.Client;
import services.ClientService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ClientController {
    private final ClientService clientService;
    private final Scanner scanner = new Scanner(System.in);

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    public void addClient(UUID conseillerId) {
        System.out.print("Enter client's first name: ");
        String prenom = scanner.nextLine();
        System.out.print("Enter client's last name: ");
        String nom = scanner.nextLine();
        System.out.print("Enter client's email: ");
        String email = scanner.nextLine();

        Client client = new Client();
        client.setId(UUID.randomUUID());
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setEmail(email);
        clientService.create(client, conseillerId);
        System.out.println("Client added successfully!");
    }

    public void deleteClient() {
        System.out.print("Enter client ID: ");
        UUID id = UUID.fromString(scanner.nextLine());
        clientService.delete(id);
        System.out.println("Client deleted successfully!");
    }

    public void findClientById() {
        System.out.print("Enter client ID: ");
        UUID id = UUID.fromString(scanner.nextLine());
        Client client = clientService.get(id);
        System.out.println("Found client: " + client);
    }

    public void searchClientsByLastName() {
        System.out.print("Enter last name prefix: ");
        String lastName = scanner.nextLine();
        List<Client> clients = clientService.find(lastName);
        clients.forEach(System.out::println);
    }

    public void sortClients() {
        List<Client> clients = clientService.sort();
        clients.forEach(System.out::println);
    }

    public void getClientsByConseillerId() {
        System.out.print("Enter conseiller ID: ");
        UUID conseillerId = UUID.fromString(scanner.nextLine());
        List<Client> clients = clientService.getByConseillerId(conseillerId);
        clients.forEach(System.out::println);
    }
}
