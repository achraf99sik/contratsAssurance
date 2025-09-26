package dao.Interfaces;

import models.Client;
import models.Conseiller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConseillerDAOInterface {
    void addConseiller(Conseiller conseiller);
    void deleteConseiller(UUID id);
    Optional<Conseiller> getConseillerById(UUID id);
    List<Client> getClientsByConseillerId(UUID conseillerId);
}
