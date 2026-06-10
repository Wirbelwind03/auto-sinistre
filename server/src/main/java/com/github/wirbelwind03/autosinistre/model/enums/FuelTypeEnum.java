package com.github.wirbelwind03.autosinistre.model.enums;

public enum FuelTypeEnum {
    ESSENCE("Essence"),
    DIESEL("Diesel"),
    ELECTRIQUE("Électrique"),
    HYBRIDE("Hybride"),
    GPL("GPL");

    private final String label;
    FuelTypeEnum(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
