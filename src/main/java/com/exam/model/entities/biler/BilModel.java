package com.exam.model.entities.biler;

import java.util.ArrayList;
import java.util.List;

public class BilModel {

        private AbonnementsPriser Abopris;
        private String Mærke;
        private String Model;
        private int Model_ID;
        private double CO2_Udslip;
        private double Stålpris;
        private double KmPrX;
        private boolean isGearManuel;
        private EnergiType Energitype;
        private List<String> udstyr;


        //getters and setters and toStrings


        public BilModel(int model_ID) {
            Model_ID = model_ID;
        }

        public AbonnementsPriser getAbopris() {
            return Abopris;
        }

        public void setAbopris(AbonnementsPriser abopris) {
            Abopris = abopris;
        }

        public String getMærke() {
            return Mærke;
        }

        public void setMærke(String mærke) {
            this.Mærke = mærke;
        }

        public String getModel() {
            return Model;
        }

        public void setModel(String model) {
            this.Model = model;
        }

        public int getModel_ID() {
            return Model_ID;
        }

        public void setModel_ID(int model_ID) {
            this.Model_ID = model_ID;
        }

        public double getCO2_Udslip() {
            return CO2_Udslip;
        }

        public void setCO2_Udslip(double CO2_Udslip) {
            this.CO2_Udslip = CO2_Udslip;
        }

        public double getStålpris() {
            return Stålpris;
        }

        public void setStålpris(double stålpris) {
            this.Stålpris = stålpris;
        }

        public double getKmPrX() {
            return KmPrX;
        }

        public void setKmPrX(double kmPrX) {
            this.KmPrX = kmPrX;
        }

        public boolean isGearManuel() {
            return isGearManuel;
        }

        public void setGearManuel(boolean gearManuel) {
            isGearManuel = gearManuel;
        }

        public EnergiType getEnergitype() {
            return Energitype;
        }

        public void setEnergitype(EnergiType energitype) {
            this.Energitype = energitype;
        }

  public List<String> getUdstyr() {
    return udstyr;
  }

  public void setUdstyr(List<String> udstyr) {
    this.udstyr = udstyr;
  }

  public void setUdstyr(String udstyr) {
          if (udstyr == null) {
            this.setUdstyr(new ArrayList<>());
          }
          this.udstyr.add(udstyr);
  }

  @Override
        public String toString() {
            return "BilModel{" +
                    "Abopris=" + Abopris +
                    ", brand='" + Mærke + '\'' +
                    ", model='" + Model + '\'' +
                    ", model_ID=" + Model_ID +
                    ", c02PerKm=" + CO2_Udslip +
                    ", steelPrice=" + Stålpris +
                    ", kmprx=" + KmPrX +
                    ", isGearManuel=" + isGearManuel +
                    ", energiType=" + Energitype +
                    '}';
        }
    }

