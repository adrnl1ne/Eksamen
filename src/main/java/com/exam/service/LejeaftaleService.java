package com.exam.service;

import com.exam.utilities.BilAbonnement;
import com.exam.utilities.NoCarReadyToRentOutException;


public class LejeaftaleService {



    public void createLejeaftale() throws NoCarReadyToRentOutException {

        BilAbonnement.simulateLejeAftale();

    }


}
