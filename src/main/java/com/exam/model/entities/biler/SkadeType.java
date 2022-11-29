package com.exam.model.entities.biler;

public enum SkadeType {
    STENSLAG(1),
    FLERE_STENSLAG(2),
    MANGLE_NØGLE(3),
    LAKTFELT(4),
    RIDSET_ALUFÆLGE(5),
    NY_FORRUDE(5);



    private int Id;

     private  SkadeType(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }
}

