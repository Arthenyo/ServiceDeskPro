package com.ServiceDeskPro.api.entities.enums;

public enum StatusCalled {
    OPEN("Aberto"),
    IN_PROGRESS("Em Progresso"),
    CLOSED("Fechado");

    private String description;

    StatusCalled(String description) {
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
