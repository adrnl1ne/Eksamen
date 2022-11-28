package com.exam.repository;

public enum EnergiType {

    DIESEL(1),
    ELEKTRISK(2),
    BENZIN(3);

    private int Id;

    private EnergiType(int id){
        Id = id;
    }

}
