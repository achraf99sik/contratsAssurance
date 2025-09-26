package dao.Interfaces;

import models.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientDAOInterface {
    void addClient(Client client, UUID conseiller_id);
    void deleteClient(UUID id);
    Optional<Client> getClientById(UUID id);
    List<Client> getAllClients();
    List<Client> getClients();
}
