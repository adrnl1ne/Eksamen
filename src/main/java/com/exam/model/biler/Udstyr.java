package com.exam.model.biler;

import javax.sound.midi.VoiceStatus;

public enum Udstyr {
    BAKKAMERA(1),
    VSTIK (2);

        private int Id;

        private Udstyr(int id) {
            Id = id;
        }
}
