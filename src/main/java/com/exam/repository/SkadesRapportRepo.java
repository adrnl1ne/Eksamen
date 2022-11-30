package com.exam.repository;


import com.exam.model.entities.biler.SkadesRapport;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class SkadesRapportRepo {

  private final Connection DCM = com.exam.utilities.DCM.getConn();

  // Marcus
  public void createSkadesRapport(SkadesRapport skadesRapport) {

  }

  // Marcus
  public SkadesRapport viewSkadesRapport(int Skaderapport_ID) {

    return null;
  }

  // Marcus
  public List<SkadesRapport> viewAlleSkadesRapporter() {
    List<SkadesRapport> skadesRapporter = new ArrayList<>();

    return skadesRapporter;
  }

  // Marcus
  public void updateSkadesRapport(SkadesRapport skadesRapport) {

  }

  // Marcus
  public void deleteSkadesRapport(SkadesRapport skadesRapport) {

  }

}
