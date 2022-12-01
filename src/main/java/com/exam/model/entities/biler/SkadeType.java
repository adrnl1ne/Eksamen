package com.exam.model.entities.biler;

public enum SkadeType {
    STENSLAG(1),
    FLERE_STENSLAG(2),
    MANGLE_NØGLE(3),
    LAKTFELT(4),
    RIDSET_ALUFÆLGE(5),
    NY_FORRUDE(6);



    private int Id;

     private  SkadeType(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public static SkadeType getEnum(int Skadetype_ID) {
        switch (Skadetype_ID) {
            case 1:
                return SkadeType.STENSLAG;
            case 2:
                return SkadeType.FLERE_STENSLAG;
            case 3:
                return SkadeType.MANGLE_NØGLE;
            case 4:
                return SkadeType.LAKTFELT;
            case 5:
                return SkadeType.RIDSET_ALUFÆLGE;
            default:
                return SkadeType.NY_FORRUDE;
        }
    }
}

