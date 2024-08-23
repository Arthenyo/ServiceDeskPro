package com.ServiceDeskPro.api.entities.enums;

public enum PriorityCalled {
    LOW("Baixa"),
    AVERAGE("Media"),
    HIGH("Alta"),
    CRITICISM("Critica");

    private String description;

    PriorityCalled(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
