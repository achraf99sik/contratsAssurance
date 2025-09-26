package models;

import enums.TypeContrat;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class Contrat {
    private UUID id;
    private TypeContrat typeContrat;
    private Date dateDebut;
    private Date dateFin;
    private HashMap<Long, Sinistre> sinistres;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TypeContrat getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(TypeContrat typeContrat) {
        this.typeContrat = typeContrat;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public HashMap<Long, Sinistre> getSinistres() {
        return sinistres;
    }

    public void setSinistres(HashMap<Long, Sinistre> sinistres) {
        this.sinistres = sinistres;
    }
}
