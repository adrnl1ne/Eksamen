package com.exam.service;

import com.exam.model.entities.biler.LejeAftale;
import com.exam.repository.LejeaftaleRepo;
import com.exam.utilities.BilAbonnement;
import com.exam.utilities.NoCarReadyToRentOutException;
import com.exam.utilities.RentingOutNoneReadyCarException;


public class LejeaftaleService {

    LejeaftaleRepo lejeaftaleRepo = new LejeaftaleRepo();

    public void createLejeaftale(LejeAftale lejeAftale) throws RentingOutNoneReadyCarException {
        lejeaftaleRepo.createLejeaftale(lejeAftale);
    }



}
