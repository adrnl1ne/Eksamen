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

    public int getInt() {
        switch (this) {
            case KLAR:
                return 1;
            case UDLEJET:
                return 2;
            case CHECKUP:
                return 3;
            default:
                return 4;
        }
    }

    //Jakob
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
