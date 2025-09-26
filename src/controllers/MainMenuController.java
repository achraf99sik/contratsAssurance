package controllers;
import dao.Implamentation.ClientDAO;
import dao.Implamentation.ConseillerDAO;
import dao.Implamentation.ContratDAO;
import dao.Implamentation.SinistreDAO;
import services.ClientService;
import services.ConseillerService;
import services.ContratService;
import services.SininsterService;

import java.util.Scanner;
import java.util.UUID;

public class MainMenuController {
    private final Scanner scanner = new Scanner(System.in);

    private final ClientController clientController;
    private final ConseillerController conseillerController;
    private final ContratController contratController;
    private final SinistreController sinistreController;

    public MainMenuController() {
        // DAOs
        ClientDAO clientDAO = new ClientDAO();
        ConseillerDAO conseillerDAO = new ConseillerDAO();
        ContratDAO contratDAO = new ContratDAO();
        SinistreDAO sinistreDAO = new SinistreDAO();

        // Services
        ClientService clientService = new ClientService(clientDAO);
        ConseillerService conseillerService = new ConseillerService(conseillerDAO, clientService);
        ContratService contratService = new ContratService(contratDAO);
        SininsterService sininsterService = new SininsterService(sinistreDAO, contratDAO);

        // Controllers
        clientController = new ClientController(clientService);
        conseillerController = new ConseillerController(conseillerService);
        contratController = new ContratController(contratService);
        sinistreController = new SinistreController(sininsterService);
    }

    public void start() {
        while (true) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Gerer les Conseillers");
            System.out.println("2. Gerer les Clients");
            System.out.println("3. Gerer les Contrats");
            System.out.println("4. Gerer les Sinistres");
            System.out.println("0. Quitter");
            System.out.print("Votre choix: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    menuConseillers();
                    break;
                case 2:
                    menuClients();
                    break;
                case 3:
                    menuContrats();
                    break;
                case 4:
                    menuSinistres();
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    return;
                default:
                    System.out.println("Choix invalide !");
                    break;
            }
        }
    }

    private void menuConseillers() {
        System.out.println("\n--- GESTION DES CONSEILLERS ---");
        System.out.println("1. Ajouter un conseiller");
        System.out.println("2. Supprimer un conseiller");
        System.out.println("3. Rechercher un conseiller par ID");
        System.out.println("4. Afficher les clients d'un conseiller");
        System.out.print("Votre choix: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                conseillerController.addConseiller();
                break;
            case 2:
                conseillerController.deleteConseiller();
                break;
            case 3:
                conseillerController.findConseillerById();
                break;
            case 4:
                conseillerController.getClientsByConseillerId();
                break;
            default:
                System.out.println("Choix invalide !");
                break;
        }
    }

    private void menuClients() {
        System.out.println("\n--- GESTION DES CLIENTS ---");
        System.out.println("1. Ajouter un client");
        System.out.println("2. Supprimer un client");
        System.out.println("3. Rechercher un client par ID");
        System.out.println("4. Rechercher un client par nom");
        System.out.println("5. Trier les clients par nom");
        System.out.println("6. Afficher les clients d'un conseiller");
        System.out.print("Votre choix: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                System.out.print("ID conseiller: ");
                UUID conseillerId = UUID.fromString(scanner.nextLine());
                clientController.addClient(conseillerId);
                break;
            case 2:
                clientController.deleteClient();
                break;
            case 3:
                clientController.findClientById();
                break;
            case 4:
                clientController.searchClientsByLastName();
                break;
            case 5:
                clientController.sortClients();
                break;
            case 6:
                clientController.getClientsByConseillerId();
                break;
            default:
                System.out.println("Choix invalide !");
                break;
        }
    }

    private void menuContrats() {
        System.out.println("\n--- GESTION DES CONTRATS ---");
        System.out.println("1. Ajouter un contrat");
        System.out.println("2. Supprimer un contrat");
        System.out.println("3. Afficher un contrat par ID");
        System.out.println("4. Afficher les contrats d'un client");
        System.out.print("Votre choix: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                System.out.print("ID client: ");
                UUID clientId = UUID.fromString(scanner.nextLine());
                contratController.addContrat(clientId);
                break;
            case 2:
                contratController.deleteContrat();
                break;
            case 3:
                contratController.findContratById();
                break;
            case 4:
                contratController.getContratsByClientId();
                break;
            default:
                System.out.println("Choix invalide !");
                break;
        }
    }

    private void menuSinistres() {
        System.out.println("\n--- GESTION DES SINISTRES ---");
        System.out.println("1. Ajouter un sinistre");
        System.out.println("2. Supprimer un sinistre");
        System.out.println("3. Rechercher un sinistre par ID");
        System.out.println("4. Calculer cout total des sinistres d'un client");
        System.out.println("5. Afficher les sinistres d'un contrat");
        System.out.println("6. Afficher les sinistres d'un client");
        System.out.println("7. Trier les sinistres par cout decroissant");
        System.out.println("8. Filtrer les sinistres avant une date");
        System.out.println("9. Filtrer les sinistres par cout minimum");
        System.out.print("Votre choix: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                System.out.print("ID contrat: ");
                UUID contratId = UUID.fromString(scanner.nextLine());
                sinistreController.addSinistre(contratId);
                break;
            case 2:
                sinistreController.deleteSinistre();
                break;
            case 3:
                sinistreController.findSinistreById();
                break;
            case 4:
                sinistreController.coutTotalByClientId();
                break;
            case 5:
                sinistreController.getSinistresByContratId();
                break;
            case 6:
                sinistreController.getSinistresByClientId();
                break;
            case 7:
                sinistreController.sortSinistresByCout();
                break;
            case 8:
                sinistreController.filterSinistresByDate();
                break;
            case 9:
                sinistreController.filterSinistresByCout();
                break;
            default:
                System.out.println("Choix invalide !");
                break;
        }
    }
}