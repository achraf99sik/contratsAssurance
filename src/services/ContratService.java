package services;

import dao.Implamentation.ContratDAO;
import models.Contrat;

import java.util.List;
import java.util.UUID;

public class ContratService {
    private final ContratDAO contratDAO;
    public ContratService(ContratDAO contratDAO){
        this.contratDAO = contratDAO;
    }
    public void create(Contrat contrat, UUID clientId){
        contratDAO.addContrat(contrat, clientId);
    }
    public void delete(UUID id){
        contratDAO.deleteContrat(id);
    }
    public Contrat get(UUID id){
        return contratDAO.getContratById(id)
                .orElseThrow(()-> new RuntimeException("Contrat not found with this id: "+id));
    }
    public List<Contrat> getByClientId(UUID id){
        return contratDAO.getContratsByClientId(id);
    }
}
