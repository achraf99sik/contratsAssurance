package services;

import dao.Implamentation.ClientDAO;
import models.Client;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ClientService {
    private final ClientDAO clientDAO;
    public ClientService(ClientDAO clientDAO){
        this.clientDAO = clientDAO;
    }
    public void create(Client client, UUID conseillerId){
        clientDAO.addClient(client, conseillerId);
    }
    public void delete(UUID id){
        clientDAO.deleteClient(id);
    }
    public Client get(UUID id){
        return clientDAO.getClientById(id)
                .orElseThrow(()->new RuntimeException("Client not found with this id: "+id));
    }
    public List<Client> find(String lastName){
        List<Client> found = clientDAO.getAllClients()
                .stream()
                .filter(s->s.getNom().startsWith(lastName))
                .collect(Collectors.toList());

        return found;
    }
    public List<Client> sort(){
        List<Client> sorted = clientDAO.getAllClients()
                .stream()
                .sorted(Comparator.comparing(s->s.getNom()))
                .collect(Collectors.toList());

        return sorted;
    }

    public List<Client> getByConseillerId(UUID conseiller_id){
        List<Client> clients = clientDAO.getAllClients()
                .stream()
                .filter(s->s.getNom().equals(conseiller_id.toString()))
                .collect(Collectors.toList());
        return clients;
    }
}
