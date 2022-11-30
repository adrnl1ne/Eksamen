package com.exam.model.entities.biler;

public enum EnergiType {

    DIESEL(1),
    ELEKTRISK(2),
    BENZIN(3);

    private int Id;

    private EnergiType(int id){
        Id = id;
    }

    public int getId() {
        return Id;
    }

    //Jakob
    public static EnergiType getEnum(int EnergiType_ID) {
        switch (EnergiType_ID) {
            case 1:
                return EnergiType.DIESEL;
            case 2:
                return EnergiType.ELEKTRISK;
            default:
                return EnergiType.BENZIN;
        }
    }
    public int getInt() {
        switch (this) {
            case DIESEL:
                return 1;
            case ELEKTRISK:
                return 2;
            default:
                return 3;
        }
    }
}


