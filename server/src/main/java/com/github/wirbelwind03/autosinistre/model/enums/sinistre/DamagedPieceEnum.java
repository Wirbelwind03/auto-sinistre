package com.github.wirbelwind03.autosinistre.model.enums.sinistre;

public enum DamagedPieceEnum {

    // Carrosserie
    AILE_AVANT_GAUCHE("Aile avant gauche"),
    AILE_AVANT_DROITE("Aile avant droite"),
    AILE_ARRIERE_GAUCHE("Aile arrière gauche"),
    AILE_ARRIERE_DROITE("Aile arrière droite"),
    PARE_CHOC_AVANT("Pare-choc avant"),
    PARE_CHOC_ARRIERE("Pare-choc arrière"),
    CAPOT("Capot"),
    COFFRE_HAYON("Coffre / Hayon"),
    PORTIERE_AVANT_GAUCHE("Portière avant gauche"),
    PORTIERE_AVANT_DROITE("Portière avant droite"),
    PORTIERE_ARRIERE_GAUCHE("Portière arrière gauche"),
    PORTIERE_ARRIERE_DROITE("Portière arrière droite"),
    BAS_DE_CAISSE_GAUCHE("Bas de caisse gauche"),
    BAS_DE_CAISSE_DROIT("Bas de caisse droit"),
    TOIT("Toit"),
    RETROVISEUR_GAUCHE("Rétroviseur gauche"),
    RETROVISEUR_DROIT("Rétroviseur droit"),

    // Vitrage
    PARE_BRISE_AVANT("Pare-brise avant"),
    LUNETTE_ARRIERE("Lunette arrière"),
    VITRE_AVANT_GAUCHE("Vitre avant gauche"),
    VITRE_AVANT_DROITE("Vitre avant droite"),
    VITRE_ARRIERE_GAUCHE("Vitre arrière gauche"),
    VITRE_ARRIERE_DROITE("Vitre arrière droite"),
    TOIT_OUVRANT("Toit ouvrant"),

    // Mécanique
    MOTEUR("Moteur"),
    BOITE_DE_VITESSES("Boîte de vitesses"),
    SUSPENSION_AVANT("Suspension avant"),
    SUSPENSION_ARRIERE("Suspension arrière"),
    FREINAGE("Système de freinage"),
    DIRECTION("Direction"),
    RADIATEUR("Radiateur / Refroidissement"),
    TRANSMISSION("Transmission"),

    // Roues
    JANTE_AVANT_GAUCHE("Jante avant gauche"),
    JANTE_AVANT_DROITE("Jante avant droite"),
    JANTE_ARRIERE_GAUCHE("Jante arrière gauche"),
    JANTE_ARRIERE_DROITE("Jante arrière droite"),
    PNEU_AVANT_GAUCHE("Pneu avant gauche"),
    PNEU_AVANT_DROITE("Pneu avant droite"),
    PNEU_ARRIERE_GAUCHE("Pneu arrière gauche"),
    PNEU_ARRIERE_DROITE("Pneu arrière droite"),

    // Intérieur
    TABLEAU_DE_BORD("Tableau de bord"),
    AIRBAGS("Airbags"),
    SELLERIE("Sellerie / Sièges"),
    SYSTEME_ELECTRIQUE("Système électrique");

    private final String label;

    DamagedPieceEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
