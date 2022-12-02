package com.exam.utilities;

public class RentingOutNoneReadyCarException extends Exception {

  // Marcus
  public RentingOutNoneReadyCarException(String message) {
    super(message);
  }

  @Override
  public String toString() {
    return "Det er ikke tilladt at udleje en bil der ikke er i tilstanden KLAR! Der blev forsøgt at udleje Bilen: " + super.toString();
  }
}
