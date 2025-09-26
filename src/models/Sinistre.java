package models;

import enums.TypeSinistre;

import java.time.LocalDateTime;
import java.util.UUID;

public class Sinistre {
    private UUID id;
    private TypeSinistre typeSinistre;
    private LocalDateTime dateTime;
    private double cout;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCout() {
        return cout;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public TypeSinistre getTypeSinistre() {
        return typeSinistre;
    }

    public void setTypeSinistre(TypeSinistre typeSinistre) {
        this.typeSinistre = typeSinistre;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    private String description ;

}
