package com.exam.model.entities.biler;

public enum LeveringsType {
    STANDARD(1),
    FDM(2);

        private int Id;

        private LeveringsType(int id) {
            Id = id;
        }

    public int getId() {
        return Id;
    }
}
