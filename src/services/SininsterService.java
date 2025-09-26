package services;

import dao.Implamentation.ContratDAO;
import dao.Implamentation.SinistreDAO;
import models.Sinistre;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SininsterService {
    private final SinistreDAO sinistreDAO;
    private final ContratDAO contratDAO;
    public SininsterService(SinistreDAO sinistreDAO, ContratDAO contratDAO){
        this.sinistreDAO = sinistreDAO;
        this.contratDAO = contratDAO;
    }
    public void create(Sinistre sinistre, UUID contratId){
        sinistreDAO.addSinistre(sinistre, contratId);
    }
    public void delete(UUID id){
        sinistreDAO.deleteSinistre(id);
    }
    public Sinistre get(UUID id){
        return sinistreDAO.getSinistreById(id)
                .orElseThrow(()->new RuntimeException("Client not found with this id: "+id));
    }
    public double coutTotal(UUID clientId){
        Stream<Sinistre> sinistres = contratDAO.getContratsByClientId(clientId).stream()
                .flatMap(c -> sinistreDAO.getSinistresByContratId(c.getId()).stream());
        return sinistres.mapToDouble(Sinistre::getCout).sum();
    }
    public List<Sinistre> getByContratId(UUID contratId){
        return sinistreDAO.getSinistresByContratId(contratId);
    }
    public List<Sinistre> sort(){
        return sinistreDAO.getAllSinistres().stream().sorted(Comparator.comparing(Sinistre::getCout).reversed()).collect(Collectors.toList());
    }
    public List<Sinistre> sinistresByClientId(UUID clientId){
        Stream<Sinistre> sinistres = contratDAO.getContratsByClientId(clientId).stream()
                .flatMap(c -> sinistreDAO.getSinistresByContratId(c.getId()).stream());
        return sinistres.collect(Collectors.toList());
    }
    public List<Sinistre> filterByDate(LocalDateTime date){
        Stream<Sinistre> sinistres = sinistreDAO.getAllSinistres().stream();
        return sinistres.filter(c->c.getDateTime().isBefore(date)).collect(Collectors.toList());
    }
    public List<Sinistre> filterByCout(double cout){
        Stream<Sinistre> sinistres = sinistreDAO.getAllSinistres().stream();
        return sinistres.filter(c->c.getCout() > cout).collect(Collectors.toList());
    }
}
