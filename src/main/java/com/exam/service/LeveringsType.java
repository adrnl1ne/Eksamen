package com.exam.service;

public enum LeveringsType {
    STANDARD(1),
    FDM(2);

        private int Id;

        private LeveringsType(int id) {
            Id = id;
        }
}
