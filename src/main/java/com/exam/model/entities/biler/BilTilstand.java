package com.exam.model.entities.biler;

public enum BilTilstand {
    KLAR(1),
    UDLEJET(2),
    CHECKUP(3),
    SKADET(4);
    private int Id;

    private BilTilstand(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public static BilTilstand getEnum(int Tilstands_ID) {
        switch (Tilstands_ID) {
            case 1:
                return BilTilstand.KLAR;
            case 2:
                return BilTilstand.UDLEJET;
            case 3:
                return BilTilstand.CHECKUP;
            default:
                return BilTilstand.SKADET;
        }
    }

}
