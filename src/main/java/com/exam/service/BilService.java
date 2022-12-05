package com.exam.service;

import com.exam.model.entities.biler.Bil;
import com.exam.repository.BilRepo;
import com.exam.utilities.UdregnPrisPåUdlejet;

import java.util.List;

public class BilService {

    BilRepo bilRepo = new BilRepo();

    public List<Bil> seUdlejetBiler() {
        return bilRepo.viewUdlejetBiler();

    }

    public double viewSumPåUdlejetBiler() {
        return UdregnPrisPåUdlejet.calculateSumForUdlejet();
    }


}
