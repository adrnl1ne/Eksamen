package com.exam.service;

import com.exam.model.entities.biler.Bil;
import com.exam.repository.BilRepo;

public class BilService {


    public void seUdlejetBiler(BilRepo bilRepo) {
        bilRepo.viewUdlejetBiler();
    }


}
