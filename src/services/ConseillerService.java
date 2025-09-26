package services;

import dao.Implamentation.ConseillerDAO;
import models.Client;
import models.Conseiller;

import java.util.List;
import java.util.UUID;

public class ConseillerService {
    private final ConseillerDAO conseillerDAO;
    private final ClientService clientService;
    public ConseillerService(ConseillerDAO conseillerDAO, ClientService clientService){
        this.conseillerDAO = conseillerDAO;
        this.clientService = clientService;
    }
    public void create(Conseiller conseiller){
        conseillerDAO.addConseiller(conseiller);
    }
    public void delete(UUID id){
        conseillerDAO.deleteConseiller(id);
    }
    public Conseiller get(UUID id){
        return conseillerDAO.getConseillerById(id)
                .orElseThrow(()->new RuntimeException("Conseiller not found with this id: "+id));
    }
    public List<Client> getClients(UUID conseillerId){
        return clientService.getByConseillerId(conseillerId);
    }
}

