package dao.Interfaces;

import models.Sinistre;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SinistreDAOInterface {
    void addSinistre(Sinistre sinistre, UUID contratId);
    void deleteSinistre(UUID id);
    Optional<Sinistre> getSinistreById(UUID id);
    List<Sinistre> getSinistresByContratId(UUID contratId);
    List<Sinistre> getAllSinistres();
}
