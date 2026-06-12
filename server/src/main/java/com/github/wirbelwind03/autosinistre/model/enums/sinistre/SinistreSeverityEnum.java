package com.github.wirbelwind03.autosinistre.model.enums.sinistre;

public enum SinistreSeverityEnum {
    FAIBLE("Faible"),
    MOYENNE("Moyenne"),
    ELEVEE("Élevée");

    private final String label;
    SinistreSeverityEnum(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

