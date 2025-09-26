package dao.Interfaces;

import models.Contrat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContratDAOInterface {
    void addContrat(Contrat contrat, UUID clientId);
    void deleteContrat(UUID id);
    Optional<Contrat> getContratById(UUID id);
    List<Contrat> getContratsByClientId(UUID clientId);
    List<Contrat> getAllContrats();
}
