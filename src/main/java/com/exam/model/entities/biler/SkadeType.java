package com.exam.model.entities.biler;

public enum SkadeType {
    STENSLAG(1, 1),
    FLERE_STENSLAG(2, 0),
    LAKFELT(3, 100),
    RIDSET_ALUFÆLG(4, 4),
    NY_FORRUDE(5, 1),
    MANGLER_NØGLE(6, 2);



    private int Id;
    private int timesTypeCanBeReported;

     private  SkadeType(int id, int timesTypeCanBeReported) {
        Id = id;
        this.timesTypeCanBeReported = timesTypeCanBeReported;
    }

    public int getId() {
        return Id;
    }

    public int getTimesTypeCanBeReported() {
         return timesTypeCanBeReported;
    }

    public static SkadeType getEnum(int Skadetype_ID) {
        switch (Skadetype_ID) {
            case 1:
                return SkadeType.STENSLAG;
            case 2:
                return SkadeType.FLERE_STENSLAG;
            case 3:
                return SkadeType.LAKFELT;
            case 4:
                return SkadeType.RIDSET_ALUFÆLG;
            case 5:
                return SkadeType.NY_FORRUDE;
            case 6:
                return SkadeType.MANGLER_NØGLE;
            default:
                System.err.println("Det var ikke muligt at udføre metoden getEnum for en SkadeType med ID'et: " + Skadetype_ID);
                throw new RuntimeException();
        }
    }
}

