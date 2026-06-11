package com.github.wirbelwind03.autosinistre.model.enums.sinistre;

public enum SinistreTypeEnum {
    ACCIDENT(
            "Accident",
            "Collision, choc",
            "mdi-car-crash",
            "red-darken-1"
    ),
    VOL(
            "Vol",
            "Total ou partiel",
            "mdi-key-variant",
            "orange-darken-2"
    ),
    BRIS_DE_GLACE(
            "Bris de glace",
            "Pare-brise, vitres",
            "mdi-car-windshield",
            "blue-darken-1"
    ),
    INCENDIE(
            "Incendie",
            "Feu, explosion",
            "mdi-fire",
            "deep-orange-darken-2"
    ),
    CATASTROPHE_NATURELLE(
            "Catastrophe Naturelle",
            "Inondation, grêle",
            "mdi-weather-hurricane",
            "teal-darken-1"
    ),
    AUTRE(
            "Autre",
            "Vandalisme, etc.",
            "mdi-dots-horizontal-circle",
            "grey-darken-1"
    );

    private final String label;
    private final String description;
    private final String iconName;
    private final String color;
    SinistreTypeEnum(
            String label,
            String description,
            String iconName,
            String color
    ){
        this.label = label;
        this.description = description;
        this.iconName = iconName;
        this.color = color;
    }

    public String getLabel() {
        return label;
    }
    public String getDescription() { return description; }
    public String getIconName() { return iconName; }
    public String getColor() { return color; }
}
