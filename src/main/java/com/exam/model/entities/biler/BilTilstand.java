package com.exam.model.entities.biler;

public enum BilTilstand {
    KLAR(1),
    UDLEJET(2),
    CHECKUP(3),
    SKADET(4);
        private int Id;
        private BilTilstand (int id){
            Id = id;
        }

}
